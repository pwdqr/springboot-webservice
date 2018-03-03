package com.dev.webservice.domain.posts;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// JpaRepository<Entity클래스, PK타입>
public interface PostsRepository extends JpaRepository<Posts, Long> {

	@Query("SELECT p FROM Posts p ORDER BY p.id DESC")
	Stream<Posts> findAllDesc();
}
