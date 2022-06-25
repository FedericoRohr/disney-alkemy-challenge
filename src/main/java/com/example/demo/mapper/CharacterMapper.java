package com.example.demo.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.DTO.CharacterDTO;
import com.example.demo.DTO.CharaterBasicDTO;
import com.example.demo.entity.CharacterEntity;


@Component
public class CharacterMapper {
	@Autowired
	MovieMapper movieMapper;

	public CharacterEntity CharacterDTO2Entity(CharacterDTO dto) {
		CharacterEntity entity = new CharacterEntity();
		entity.setAge(dto.getAge());
		entity.setName(dto.getName());
		entity.setHistory(dto.getHistory());
		entity.setImage(dto.getImage());
		entity.setId(dto.getId());
	    entity.setWeight(dto.getWeight());  
		return entity;
	}

	public CharacterDTO CharacterEntity2DTO(CharacterEntity entity, boolean loadMovies ) {
		CharacterDTO dto = new CharacterDTO();
		dto.setName(entity.getName());
		dto.setAge(entity.getAge());
		dto.setHistory(entity.getHistory());
		dto.setId(entity.getId());
	    dto.setWeight(entity.getWeight());  
	    if(loadMovies) {
	    	dto.setMovies(movieMapper.MovieListEntity2DTO(entity.getMovies(),false));
	    }
		return dto;
	}

	public List<CharacterDTO> CharacterEntitySet2DTOlist(List<CharacterEntity> characters, boolean loadPaises) {
		List<CharacterDTO>dtos=new ArrayList<>();
		for(CharacterEntity c: characters ) {
			dtos.add(this.CharacterEntity2DTO(c, loadPaises));
		}
		return dtos;
	}

	public List<CharacterEntity> CharacterListDTO2Entity(List<CharacterDTO> characters) {
		List<CharacterEntity>entities=new ArrayList<>();
		for(CharacterDTO c: characters) {
			entities.add(this.CharacterDTO2Entity(c));
		}
		return entities;
	}

	public CharacterEntity update(CharacterEntity entity, CharacterDTO dto) {
		entity.setAge(dto.getAge());
		entity.setName(dto.getName());
		entity.setHistory(dto.getHistory());
		entity.setImage(dto.getImage());
	    entity.setWeight(dto.getWeight());  
		return entity;
	}
	
	

	public CharaterBasicDTO characterBasicEntity2DTO(CharacterEntity entity) {
		CharaterBasicDTO basic= new CharaterBasicDTO();
		basic.setImage(entity.getImage());
		basic.setName(entity.getName());
		
		return basic;
	}

	public List<CharaterBasicDTO> characterBasicEntity2DTOList(List<CharacterEntity> charactersEntity) {
		List<CharaterBasicDTO>list= new ArrayList<>();
		for(CharacterEntity c : charactersEntity) {
			list.add(this.characterBasicEntity2DTO(c));
		}
		return list;
	}
	
	
}
