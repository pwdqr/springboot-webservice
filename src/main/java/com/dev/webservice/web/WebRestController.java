package com.dev.webservice.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.webservice.dto.posts.PostsSaveRequestDto;
import com.dev.webservice.service.PostsService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
public class WebRestController {
	
	private PostsService postsService; // lombok의 @AllArgsConstructor를 이용해 @Autowired가 아닌 생성자로 주입.
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	@PostMapping("/posts")
//	public Long savePosts(@Valid @RequestBody PostsSaveRequestDto dto, BindingResult result) { // @RequestBody을 이용해 HTTP 요청을 자바 객체로 매핑.
	public Long savePosts(@Valid @RequestBody PostsSaveRequestDto dto) { // @RequestBody을 이용해 HTTP 요청을 자바 객체로 매핑.
		/*
		// 에러 출력
        List<ObjectError> list = result.getAllErrors();
		if(result.hasErrors()) {
			for(ObjectError e : list) {
//				log.error("ObjectError: {}", e);
				log.error(e.getDefaultMessage());
			}
		}
		*/
		return postsService.save(dto);
	}
}
