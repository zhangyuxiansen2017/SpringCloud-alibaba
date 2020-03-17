基于SpringCloud-alibaba的oauth2分布式项目，
使用nacos+gateway+spring security oauth2，认证服务器+资源服务器.

Based on the oauth2 distributed project of springcloud-alibaba, 
using nacos+gateway+spring security oauth2, authentication server + resource server.

启动前安装好nacos，nacos只做注册中心，导入sql数据库。

auth-9999   ：认证服务器
commons-api ：公共类
gateway-3344：网关
provider-9001：资源服务器9001
provider-9002：资源服务器9002

授权码存储模式为jdbc(mysql),之前基于内存。导入项目下的cloud.sql文件到数据库，数据库名称为cloud.
客户端配置存储也使用jdbc(mysql),之前也是基于内存，可以阅读代码还原为内存模式。
令牌生成策略为JWT形式，使用JWT方式可以使资源服务器不需要调用验证服务器验证，增强服务稳定性和可靠性。
密码生成和验证方式为PasswordEncoder方式，有效避免密码安全问题。