###一、nacos+mysql的seata daocker方式安装启动
####1、在安装seata前，确保nacos和mysql已经安装
####2、在docker环境下先拉去安装seata
```
docker run -d --name seata -p 8091:8091 seataio/seata-server:1.4.0
```
####3、将seata容器内配置文件复制到宿主机上的/opt/seata-config目录下
```
docker cp seata:/seata-server/resources  /opt/seata-config
```
####4、删除容器并修改刚才复制文件夹下的registry.conf文件内容（主要修改注册中心和配置中心）,参考以下
```
registry {
  type = "nacos"
  loadBalance = "RandomLoadBalance"
  loadBalanceVirtualNodes = 10
  nacos {
    application = "seata-server"
    # 写自己的ip:port
    serverAddr = "localhost:8848"
    group = "SEATA_GROUP"
    # 这里替换自己的namespace ID
    namespace = "seata"
    username = "nacos"
    password = "nacos"
  }
}
config {
  type = "nacos"
  nacos {
    serverAddr = "localhost:8848"
    namespace = "seata"
    group = "SEATA_GROUP"
    username = "nacos"
    password = "nacos"
  }
}
```
####5、将seata配置中心配置导入
```
#1.clone下面地址的项目
https://github.com/seata/seata
#2、修改/script/config-center下config.txt文件，需要修改如下内容

#这里值随意，但需与客户端保持一致
service.vgroupMapping.product-server-group=default
# 这里替换自己的mysql地址
store.db.url=jdbc:mysql://localhost:3306/seata?characterEncoding=utf8&useSSL=false&serverTimezone=UTC
store.db.user=root
store.db.password=root

#3、然后执行该路径nacos文件夹下的nacos-config.sh(注意自己修改nacos地址和账号密码)
bash nacos-config.sh -h localhost -p 8848 -g SEATA_GROUP -t seata -u nacos -w nacos
#4、进入nacos查看配置是否已经存在，如果不存在，可以先在nacos创建对应命名空间，然后重复上面命令
```
####6、创建seata的mysql数据库及表
https://github.com/seata/seata/blob/develop/script/server/db/mysql.sql 下载对应的数据sql文件执行，或者直接复制命令执行。

####7、重新docker将seata运行，需要将之前的配置挂载，并指定运行时需要执行的配置文件
```
# 这里替换自己的ip
docker run  -d --name seata -p 8091:8091  -e SEATA_IP=127.0.0.1 -e SEATA_CONFIG_NAME=file:/seata-server/resources/registry  -v /opt/seata-config:/seata-server/resources seataio/seata-server:1.4.0
```
####8、如果一切ok，那就单机版seata就安装完毕

###二、项目中使用
相对来说，seata对代码侵入性较低，在springcloud分布式项目中，引入对应版本的seata依赖就可以
####1、引入依赖
因为我们使用的spring-cloud-alibaba的版本是2.1.0.RELEASE,他默认的seata版本是1.1.0的，所以我们在使用的项目中需要先排除，再引入1.4.0
```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-seata</artifactId>
    <exclusions>
        <exclusion>
            <groupId>io.seata</groupId>
            <artifactId>seata-spring-boot-starter</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>io.seata</groupId>
    <artifactId>seata-spring-boot-starter</artifactId>
    <version>1.4.0</version>
</dependency>
```
####2、使用
在需要使用到分布式事务的项目中都需要引入上面依赖
在项目中加入对应配置
```yaml
seata:
  enabled: true
  application-id: NACOS-PROVIDER
  tx-service-group: product-server-group
  enable-auto-data-source-proxy: true
  config:
    type: nacos
    nacos:
      namespace: seata
      server-addr: 192.168.1.87:8848
      group: SEATA_GROUP
      username: nacos
      password: nacos
  registry:
    type: nacos
    nacos:
      application: seata-server
      server-addr: 192.168.1.87:8848
      namespace: seata
      username: nacos
      password: nacos
```
在主启动类中加入@EnableAutoDataSourceProxy
在需要分布式事务的主方法上加上@GlobalTransactional(name = "product-server-group", rollbackFor = Exception.class)

####2、如果启动报错，可能还需要一个数据源，则创建对应的数据源
```java
@Configuration
public class SeataProxyConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return new DruidDataSource();
    }
}
```

####3、如还存在问题请提issue或者加qq:291777408