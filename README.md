## 一、security的微服务授权认证

​		基于SpringCloud-alibaba的oauth2分布式项目，主要技术栈 nacos+gateway+spring security oauth2，认证服务器+资源服务器.也集成了logstash收集日志

### 1、启动项目环境准备

docker安装nacos，倒入nacos配置文件，nacos做注册中心和配置中心。
**项目服务说明**：

| 服务          | 作用           |
| ------------- | -------------- |
| auth-9999     | 认证服务器     |
| commons-api   | 公共类         |
| gateway-3344  | 网关           |
| provider-9001 | 资源服务器9001 |
| provider-9002 | 资源服务器9002 |

### 2、认证方式说明

1. 授权码存储模式为jdbc(mysql),之前基于内存。导入项目下的cloud.sql文件到数据库，数据库名称为cloud。
2. 客户端配置存储在使用jdbc(mysql),之前也是基于内存，可以阅读代码还原为内存模式。
3. 令牌生成策略为JWT形式，使用JWT方式可以使资源服务器不需要调用验证服务器验证，增强服务稳定性和可靠性。
4. 密码生成和验证方式为PasswordEncoder方式，有效避免密码安全问题。

### 3、认证相关接口

1. 获取token
   接口地址：http://127.0.0.1:3344/oauth/token
   使用postman：选择Authorization认证方式的Basic Auth,然后填入 Username:**c1**和Password:**secret**
   在表单中添加 grant_type:**password**、username:**zgm**、 password:**123456**、scope:**ROLE_ADMIN**
2. 验证token
   接口地址：http://127.0.0.1:3344/oauth/check_token
   使用postman：表单中添加 token:xxxxx
3. 使用token
   接口地址：http://127.0.0.1:3344/payment/get/6
   使用postman：选择Authorization认证方式Bearer Token,然后填入Token:xxxxx

## 二、分布式事务seata

​		点击了解的使用 [seata](doc/seata.md)
## 三、基于Kubernetes的注册、配置中心
   新增分支 for-k8s 支持k8s服务注册和配置中心，需要了解，请切到对应分支查看
