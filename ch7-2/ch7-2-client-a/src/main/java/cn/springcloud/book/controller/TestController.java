package cn.springcloud.book.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证zuul的路由规则
 * 1、http://localhost:7070/add?a=1&b=100           直接访问
 * 2、http://localhost:5555/client/add?a=1&b=100    路由访问
 */

@RestController
public class TestController {

	@GetMapping("/add")
	public Integer add(Integer a, Integer b){
		return a + b;
	}
}
