- 分别启动eureka-server、zuul-server、client-a
- 访问
  - http://localhost:5555/client/mul?a=100&b=200   根据注入到eureka的路径
  - http://localhost:5555/client-a/mul?a=100&b=200 根据注入的eureka的实体
  
  - http://localhost:5555/baidu  根据数据库配置的路由的url进行跳转