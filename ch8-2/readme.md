### 说明
 - 依次启动工程：eureka-server,zuul-server,client-a,auth-server
 - http://localhost:5555/client/test  无授权，不通
 - http://localhost:5555  跳转到登录界面 admin admin 后，显示结果
 - client-a控制台打印
  - ----------------header----------------
    upgrade-insecure-requests: 1
    user-agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36
    dnt: 1
    accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8
    accept-language: zh-CN,zh;q=0.9
    authorization: bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NTAyNTY0MTUsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiV1JJR1RIX1dSSVRFIiwiV1JJR1RIX1JFQUQiXSwianRpIjoiNGIxYTQzNWUtMGZjOS00MGJkLTgyZDEtNTRkYjI3ODYyYjRlIiwiY2xpZW50X2lkIjoienV1bF9zZXJ2ZXIiLCJzY29wZSI6WyJXUklHVEgiLCJyZWFkIl19.OyejBQWbUTpSbYrWE-8X8UmjTRbNoW8qHzczDKrfWsk
    x-forwarded-host: localhost:5555
    x-forwarded-proto: http
    x-forwarded-prefix: /client
    x-forwarded-port: 5555
    x-forwarded-for: 0:0:0:0:0:0:0:1
    accept-encoding: gzip
    content-length: 0
    host: 10.2.152.20:7070
    connection: Keep-Alive
    
    其中authorization用base64解码后可以看到正确的信息jwt token