package com.example.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.DTO.MovieBasicDTO;
import com.example.demo.DTO.MovieDTO;
import com.example.demo.entity.MovieEntity;
import com.example.demo.repository.CharacterRepository;
import com.example.demo.repository.GenreRepository;

@Component
public class MovieMapper {
	@Autowired
	CharacterMapper characterMapper;
	@Autowired
	CharacterRepository characterRepository;
	@Autowired
	GenreRepository genreRepository;
	
	public MovieEntity movieDTO2Entity(MovieDTO dto) {
        MovieEntity entity = new MovieEntity();
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setCreationDate(dto.getCreationDate());
        entity.setCalification(dto.getCalification());
        entity.setGenre(genreRepository.getById(dto.getGenreId()));
        entity.setGenreId(dto.getGenreId());
        entity.setCharacters(characterMapper.CharacterListDTO2Entity(dto.getCharacters())); // the associate characters will be create, if you want to associate 
        return entity;                                                                      // characters in data base you have to add with character method
    }
	
	public MovieDTO MovieEntity2DTO (MovieEntity entity,boolean loadCharacters) {
		MovieDTO dto = new MovieDTO();
		dto.setId(entity.getId());
		dto.setImage(entity.getImage());
		dto.setTitle(entity.getTitle());
		dto.setCreationDate(entity.getCreationDate());
		dto.setCalification(entity.getCalification());
		dto.setGenre(entity.getGenre());
		dto.setGenreId(entity.getGenreId());
		if(loadCharacters) {
			dto.setCharacters(characterMapper.CharacterEntitySet2DTOlist(entity.getCharacters(), false));
		}
        return dto;
	}

	public List<MovieDTO> MovieListEntity2DTO(List<MovieEntity> list, boolean b) {
		List<MovieDTO>dtos= new ArrayList<>();
		for(MovieEntity m :list) {
			dtos.add(this.MovieEntity2DTO(m, b));
		}
		return dtos;
	}
	/*
	public List<MovieEntity> MovieListDTO2Entity(List<MovieDTO> list) {
		List<MovieEntity>entities= new ArrayList<>();
		for(MovieDTO m :list) {
			entities.add(this.movieDTO2Entity(m));
		}
		return entities;
	}
	*/

	public MovieEntity update(MovieEntity entity, MovieDTO dto) {
		    entity.setImage(dto.getImage());
	        entity.setTitle(dto.getTitle());
	        entity.setCreationDate(dto.getCreationDate());
	        entity.setCalification(dto.getCalification());
	        entity.setGenre(genreRepository.getById(dto.getGenreId()));
	        entity.setGenreId(dto.getGenreId());
	        entity.setCharacters(characterMapper.CharacterListDTO2Entity(dto.getCharacters())); // the associate characters will be create, if you want to associate 
	        return entity;   
		
	}

	public MovieBasicDTO MovieEntity2BasicDTO(MovieEntity entity) {
		MovieBasicDTO basic = new MovieBasicDTO();
		basic.setCreationDate(entity.getCreationDate());
		basic.setImage(entity.getImage());
		basic.setTitle(entity.getTitle());
		return basic;
		
	}
	
	public List<MovieBasicDTO> MovieListEntity2BasicDTO(List<MovieEntity> listEntity) {
		List<MovieBasicDTO>list = new ArrayList<>();
		for(MovieEntity m : listEntity) {
			list.add(this.MovieEntity2BasicDTO(m));
		}
		return list;
	}

}
