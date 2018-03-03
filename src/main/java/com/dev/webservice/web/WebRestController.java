package com.dev.webservice.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.webservice.dto.posts.PostsSaveRequestDto;
import com.dev.webservice.service.PostsService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class WebRestController {
	
	private PostsService postsService; // lombok의 @AllArgsConstructor를 이용해 @Autowired가 아닌 생성자로 주입.
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	@PostMapping("/posts")
	public Long savePosts(@RequestBody PostsSaveRequestDto dto) { // @RequestBody을 이용해 HTTP 요청을 자바 객체로 매핑.
		return postsService.save(dto);
		
	}
}
