package com.dev.webservice.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dev.webservice.domain.posts.Posts;
import com.dev.webservice.domain.posts.PostsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

	@Autowired
	PostsRepository postsRepository;
	
	@After
	public void cleanup() {
		/**
		 * 이후 테스트 코드에 영향을 끼치지 않기 위해
		 * 테스트 메소드가 끝날때마다 repository 전체를 비운다.
		 */
		postsRepository.deleteAll(); // Deletes all entities managed by the repository.
	}
	
	@Test
	public void 게시글저장_불러오기() {
		// given - 테스트 기반 환경 구축
		postsRepository.save(Posts.builder()
				.title("테스트 게시글")
				.content("테스트 본문")
				.author("test@test.com")
				.build());
		
		// when - 테스트 하고자 하는 행위 선언
		List<Posts> postsList = postsRepository.findAll();
		
		// then - 테스트 결과 검증
		Posts posts = postsList.get(0);
		assertThat(posts.getTitle(), is("테스트 게시글"));
		assertThat(posts.getContent(), is("테스트 본문"));
		assertThat(posts.getAuthor(), is("test@test.com"));
	}
	
	@Test
	public void BaseTimeEntity_등록() {
		// given
		LocalDateTime now = LocalDateTime.now();
		postsRepository.save(Posts.builder()
				.title("time 적용 게시글")
				.content("time 적용 본문")
				.author("time 적용 작성자")
				.build());
		
		// when
		List<Posts> postsList = postsRepository.findAll();
		
		// then
		Posts posts = postsList.get(0);
		assertTrue(posts.getCreatedDate().isAfter(now));
		assertTrue(posts.getModifiedDate().isAfter(now));
		
	}

}
