package cn.springcloud.book.service.impl;

import cn.springcloud.book.service.IUserService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserServiceFallbackFactory implements FallbackFactory<IUserService> {



    @Override
    public IUserService create(Throwable throwable) {
        return new IUserService() {

            /**
             * 出错则调用该方法返回友好错误
             * @param username
             * @return
             */
            @Override
            public String getUser(String username) {
                //默认日志级别为info
                log.info("进入熔断降级的方法");
                return "The user--->" + username + " does not exist in this system, please confirm username";
            }
        };
    }
}
