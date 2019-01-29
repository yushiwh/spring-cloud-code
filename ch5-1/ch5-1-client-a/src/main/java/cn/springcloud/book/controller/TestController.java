package cn.springcloud.book.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务的提供者
 */
@RestController
public class TestController {

	@GetMapping("/add")
	public String add(Integer a, Integer b, HttpServletRequest request){
		return " From Port: "+ request.getServerPort() + ", Result: " + (a + b);
	}
}
