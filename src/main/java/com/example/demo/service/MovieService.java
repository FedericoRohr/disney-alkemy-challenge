package com.example.demo.service;

import java.util.List;

import com.example.demo.DTO.MovieBasicDTO;
import com.example.demo.DTO.MovieDTO;

public interface MovieService {

	MovieDTO save(MovieDTO dto);

	void delete(Long id);

	void update(Long id, MovieDTO movie);

	MovieDTO getOne(Long id);

	void addCharater(Long id, Long id2);

	void removeCharacter(Long id, Long id2);

	List<MovieBasicDTO> getDetailsByFilter(String name, Long genreId, String order);

	
}
