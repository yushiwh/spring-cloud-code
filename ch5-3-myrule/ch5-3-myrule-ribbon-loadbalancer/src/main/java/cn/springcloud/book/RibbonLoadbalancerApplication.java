package cn.springcloud.book;

import cn.springcloud.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableDiscoveryClient
//在启动该微服务的时候就能去加载我们的自定义Ribbon配置类，从而使配置生效
//这里要写上调用的服务的名称CLIENT-A
@RibbonClient(name="CLIENT-A",configuration=MySelfRule.class)
public class RibbonLoadbalancerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonLoadbalancerApplication.class, args);
    }


}