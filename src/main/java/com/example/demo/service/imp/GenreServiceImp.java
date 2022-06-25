package com.example.demo.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.GenreDTO;
import com.example.demo.entity.GenreEntity;
import com.example.demo.mapper.GenreMapper;
import com.example.demo.repository.GenreRepository;
import com.example.demo.service.GenreService;
@Service
public class GenreServiceImp implements GenreService {
    @Autowired
	GenreMapper genreMapper;
    @Autowired
	GenreRepository genreRepository;
	
	@Override
	public GenreDTO save(GenreDTO dto) {
		GenreEntity entity = genreMapper.GenreDTO2Entity(dto);
		entity= genreRepository.save(entity);
		GenreDTO response = genreMapper.GenreEntity2DTO(entity);
		return response;
	}

}
