package com.dev.webservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.webservice.domain.posts.PostsRepository;
import com.dev.webservice.dto.posts.PostsMainResponseDto;
import com.dev.webservice.dto.posts.PostsSaveRequestDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PostsService {
	
	private PostsRepository postsRepository;
	
	@Transactional
	public Long save(PostsSaveRequestDto dto) {
		return postsRepository.save(dto.toEntity()).getId();
	}
	
	@Transactional(readOnly = true) // 옵션(readOnly = true)을 주면 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선.
	public List<PostsMainResponseDto> findAllDesc(){
		return postsRepository.findAllDesc()
				.map(PostsMainResponseDto::new) // = .map(posts -> new PostsMainResponseDto(posts))
				.collect(Collectors.toList());
	}

}
