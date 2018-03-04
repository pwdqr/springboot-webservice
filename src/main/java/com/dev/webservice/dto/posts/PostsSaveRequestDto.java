package com.dev.webservice.dto.posts;

import org.hibernate.validator.constraints.NotEmpty;

import com.dev.webservice.domain.posts.Posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter // Controller에서 @RequestBody로 외부에서 데이터를 받는 경우엔 기본생성자 + set메소드를 통해서만 값이 할당되므로 이때만 setter를 허용시킨다.
@NoArgsConstructor
public class PostsSaveRequestDto {
	@NotEmpty(message="제목을 입력하세요")
	private String title;
	
	@NotEmpty(message="내용을 입력하세요")
	private String content;
	
	@NotEmpty(message="작성자를 입력하세요")
	private String author;
	
	@Builder
	public PostsSaveRequestDto(String title, String content, String author) {
		this.title = title;
		this.content = content;
		this.author = author;
	}
	
	public Posts toEntity() {
		return Posts.builder()
				.title(title)
				.content(content)
				.author(author)
				.build();
	}
}
