### springcloud的综合应用  脚手架
详情可见第10章

工程名             端  口       描    述
ch10-1             NA          父工程
sonfig-server      9090        配置中心
eureka-server      8761        注册中心
zuul-server        7777        API GateWay
hystrix-dashboard  9099        hystrix dashboard & Turbine
common             NA          公共基础包，方便后台服务引用
user-service       9091        用户服务，对用户数据的操作
data-server        8099        数据服务，提供基础的数据



- 按顺序启动 eureka 、zuul-server、data-service、user-service
- http://localhost:9091/getContextUserId   控制台会打印不合法——拦截器起作用
- http://localhost:7777/sc-user-service/getContextUserId  没有传用户的信息，网关做拦截，请求头没有x-customs-user。鉴权不通过
- 添加请求头x-customs-user=spring，访问http://localhost:7777/sc-user-service/getContextUserId  成功

