package com.dev.webservice.service;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dev.webservice.domain.posts.Posts;
import com.dev.webservice.domain.posts.PostsRepository;
import com.dev.webservice.dto.posts.PostsMainResponseDto;
import com.dev.webservice.dto.posts.PostsSaveRequestDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsServiceTest {

	@Autowired
	private PostsService postsService;
	
	@Autowired
	private PostsRepository postsRepositoty;
	
	@After
	public void cleanup() {
		postsRepositoty.deleteAll();
	}
	
	@Test
	public void 목록이_역순으로_정렬되서_조회된다() {
		// when
		List<PostsMainResponseDto> postsList = postsService.findAllDesc();
		
		// then
		assertThat(postsList.size()).isEqualTo(2);
		assertThat(postsList.get(0).getId()).isEqualTo(2);
		assertThat(postsList.get(1).getId()).isEqualTo(1);
	}
	
	@Test
	public void Dto데이터가_posts테이블에_저장된다() {
		// given
		PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
				.author("test - author")
				.content("test - content")
				.title("test - title")
				.build();
		
		// when
		postsService.save(dto);
		
		int size = postsRepositoty.findAll().size();
		assertThat(size).isEqualTo(1);
		
		// then
		Posts posts = postsRepositoty.findAll().get(0);
		assertThat(posts.getAuthor()).isEqualTo(dto.getAuthor());
		assertThat(posts.getContent()).isEqualTo(dto.getContent());
		assertThat(posts.getTitle()).isEqualTo(dto.getTitle());
		
	}

}
