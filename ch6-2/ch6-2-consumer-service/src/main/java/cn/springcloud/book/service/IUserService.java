package cn.springcloud.book.service;

import cn.springcloud.book.service.impl.UserServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.springcloud.book.service.impl.UserServiceFallback;


/**
 * 两种不同的方法进行降级处理
 * 1、指定fallback
 * 2、指定fallbackFactory
 */
//@FeignClient(name = "sc-provider-service", fallback = UserServiceFallback.class)
@FeignClient(name = "sc-provider-service", fallbackFactory = UserServiceFallbackFactory.class)
//@FeignClient(name = "sc-provider-service")
public interface IUserService {

	@RequestMapping(value = "/getUser",method = RequestMethod.GET)
    public String getUser(@RequestParam("username") String username);

}
