package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.DTO.GenreDTO;
import com.example.demo.entity.GenreEntity;
@Component
public class GenreMapper {


	public GenreDTO GenreEntity2DTO(GenreEntity entity) {
		 GenreDTO response = new GenreDTO();
		 response.setId(entity.getId());
		 response.setImage(entity.getImage());
		 response.setName(entity.getName());
		 return response;
		
	}

	public GenreEntity GenreDTO2Entity(GenreDTO dto) {
		GenreEntity response = new GenreEntity();
		 response.setId(dto.getId());
		 response.setImage(dto.getImage());
		 response.setName(dto.getName());
		 return response;
	}

}
