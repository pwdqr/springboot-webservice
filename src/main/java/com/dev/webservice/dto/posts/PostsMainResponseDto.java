package com.dev.webservice.dto.posts;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.dev.webservice.domain.posts.Posts;

import lombok.Getter;

/*
 * Entity가 DTO를 의존(toDto와 같은 메소드로 dto를 반환)하고 있으면 
 * 그때부터 View/외부요청(수정사항)에 따라 DTO뿐만 아니라 Entity까지 변경이 필요하게 되며
 * 프로젝트 규모가 커져 프로젝트를 분리해야할때도 Entity가 DTO를 의존하고 있으면 분리하기가
 * 굉장히 어렵기 때문에 DTO가 Entity에 의존하도록 작성한다.
 */
@Getter
public class PostsMainResponseDto {
	private Long id;
	private String title;
	private String author;
	private String modifiedDate; // View 영역에서는 LocalDateTime 타입을 모르므로 인식할 수 있도록 toStringDateTime을 통해 문자열로 날짜형식을 변경해서 등록.
	
	public PostsMainResponseDto(Posts entity) {
		id = entity.getId();
		title = entity.getTitle();
		author = entity.getAuthor();
		modifiedDate = toStringDateTime(entity.getModifiedDate());
	}
	
	/**
	 * Java 7 버전
	 */
	private String toStringDateTime7(LocalDateTime localDateTime) {
		if(localDateTime == null) {
			return "";
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return formatter.format(localDateTime);
	}
	
	/**
	 * Java 8 버전
	 */
	private String toStringDateTime(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return Optional.ofNullable(localDateTime)
				.map(formatter::format)
				.orElse("");
				
	}
	
}
