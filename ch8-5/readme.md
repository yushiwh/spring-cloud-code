 - 依次启动server，zuul-server，client-a（需要修改client-a的yml文件，为node1，node2,node3），启动三次工程（启动多个实例并且执行加载yml里面的不同的配置）
 - header不加上gray_mark，请求只会在7070，7071的服务上
 - heager加上gray_mark，并且值为enable,无论请求多少次都在7072上面
 
 1、http://localhost:5555/client/add?a=100&b=100
 2、http://localhost:5555/client/add?a=100&b=100
    设置header   key:gray_mark  value:enable
 
 