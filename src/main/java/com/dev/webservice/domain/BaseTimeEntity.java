package com.dev.webservice.domain;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

/*
 * 현재 SpringDataJpa 버전에선 LocalDate와 LocalDateTime이 Database 저장시 제대로 전환이 안되는 이슈 존재.
 * 이 문제를 SpringDataJpa의 코어 모듈인 Hibernate core 5.2.10부터는 해결되었어서 이 부분을 교체함.
 * 
 * build.gradle에 아래 2부분 추가(SpringDataJpa가 사용하는 Hibernate의 버전만 5.2.11로 변경해서 사용하겠다는 의존성 변경 코드)
 * - classpath "io.spring.gradle:dependency-management-plugin:1.0.4.RELEASE"  // 추가
 * - ext['hibernate.version'] = '5.2.11.Final' //추가
 * 이렇게 하면 LocalDate/LocalDateTime과 Database간 문제는 해결된다.
 */


/*
 * 모든 Entity들의 상위 클래스가 되어 Entity들의 createdDate, modifiedDate를 자동으로 관리하는 역할.
 */
@Getter
@MappedSuperclass // JPA Entity 클래스들이 BaseTimeEntity을 상속할 경우 필드들(createdDate, modifiedDate)도 컬럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class) // BaseTimeEntity클래스에 Auditing 기능을 포함시킨다.
public abstract class BaseTimeEntity {
	
	@CreatedDate // Entity가 생성되어 저장될 때 시간이 자동 저장된다.
	private LocalDateTime createdDate;
	
	@LastModifiedDate // 조회한 Entity의 값을 변경할 때 시간이 자동 저장
	private LocalDateTime modifiedDate;

}
