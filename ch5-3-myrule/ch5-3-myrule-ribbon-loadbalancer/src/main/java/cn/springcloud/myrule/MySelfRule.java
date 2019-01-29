package cn.springcloud.myrule;

import com.netflix.loadbalancer.*;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


/**
 * 此处一个大坑
 * <p>
 * 特别注意
 * <p>
 * 自定义的配置类不能放在@ComponentScan所扫描的当前包以及子包下，
 * 否则我们自定义的这个配置类将会被所有的Ribbon客户端所共享，也就是说我们达不到特殊化定制目的
 */

@Configuration
public class MySelfRule {




    @Bean
    public IRule myRule()
    {
        //return new RandomRule();// Ribbon默认是轮询，我自定义为随机
        //return new RoundRobinRule();// Ribbon默认是轮询，我自定义为随机

        return new RandomRule_ZY();// 我自定义为每台机器5次
    }
}