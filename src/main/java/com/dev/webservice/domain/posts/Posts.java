package com.dev.webservice.domain.posts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.dev.webservice.domain.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access=AccessLevel.PROTECTED) // from Lombok(기본 생성자 추가, 기본 생성자의 접근 권한을 protected로 제한하여 코드상에서 생성은 막되, JPA에서 Entity 클래스 생성은 허용.)
@Getter // from Lombok
@Entity // from JPA
public class Posts extends BaseTimeEntity {

	@Id // pk
	@GeneratedValue // pk 생성규칙. 기본은 auto이며, MySQL의 auto_increment와 같이 자동증가하는 정수형 값이 된다.
	private Long id; // MySQL 기준 bigint 타입이 된다.
	
	@Column(length = 500, nullable = false) // 문자열의 경우 VARCHAR(255) 기본값의 사이즈를 늘리기 위해 사용.
	private String title;
	
	@Column(columnDefinition = "TEXT", nullable = false) // 타입을 TEXT로 변경하기 위해 사용.
	private String content;
	
	private String author;
	
	@Builder // 해당 클래스의 빌더패턴 클래스 생성. 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함.
	public Posts(String title, String content, String author) {
		this.title = title;
		this.content = content;
		this.author = author;
	}
	
}
