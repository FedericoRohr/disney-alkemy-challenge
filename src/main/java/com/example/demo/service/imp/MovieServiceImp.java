package com.example.demo.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.MovieBasicDTO;
import com.example.demo.DTO.MovieDTO;
import com.example.demo.DTO.MovieFilterDTO;
import com.example.demo.entity.CharacterEntity;
import com.example.demo.entity.MovieEntity;
import com.example.demo.exeption.ParamNotFound;
import com.example.demo.mapper.MovieMapper;
import com.example.demo.repository.CharacterRepository;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.specification.MovieSpecification;
import com.example.demo.service.MovieService;

@Service
public class MovieServiceImp implements MovieService {
	@Autowired
	MovieMapper movieMapper;
	@Autowired
	MovieRepository movieRepository;

	@Autowired
	CharacterRepository characterRepository;
	
	@Autowired
	MovieSpecification movieSpecification;

	@Override
	public MovieDTO save(MovieDTO dto) {
		MovieEntity entity = movieMapper.movieDTO2Entity(dto);
		entity = movieRepository.save(entity);
		MovieDTO response = movieMapper.MovieEntity2DTO(entity, true);
		return response;
	}

	@Override
	public void delete(Long id) {
		movieRepository.deleteById(id);
	}

	@Override
	public void update(Long id, MovieDTO dto) {
		Optional<MovieEntity> entity = movieRepository.findById(id);
		if (!entity.isPresent()) {
			throw new ParamNotFound("no movie with id: " + id);
		}
		MovieEntity exist = entity.get();
		exist = movieMapper.update(exist, dto);
		movieRepository.save(exist);
	}

	@Override
	public MovieDTO getOne(Long id) {
		Optional<MovieEntity> entity = movieRepository.findById(id);
		if (!entity.isPresent()) {
			throw new ParamNotFound("no movie with id: " + id);
		}
		MovieEntity exist = entity.get();
		return movieMapper.MovieEntity2DTO(exist, true);
	}

	@Override
	public void addCharater(Long id, Long id2) {
		Optional<MovieEntity> entity = movieRepository.findById(id);
		if (!entity.isPresent()) {
			throw new ParamNotFound("no movie with id: " + id);
		}
		Optional<CharacterEntity> entityCharacter = characterRepository.findById(id2);
		if (!entityCharacter.isPresent()) {
			throw new ParamNotFound("no character with id: " + id2);
		}
		MovieEntity movie = entity.get();
		CharacterEntity characer = entityCharacter.get();
		movie.addCharacter(characer);
		movieRepository.save(movie);
	}

	@Override
	public void removeCharacter(Long id, Long id2) {
		Optional<MovieEntity> entity = movieRepository.findById(id);
		if (!entity.isPresent()) {
			throw new ParamNotFound("no movie with id: " + id);
		}
		Optional<CharacterEntity> entityCharacter = characterRepository.findById(id2);
		if (!entityCharacter.isPresent()) {
			throw new ParamNotFound("no character with id: " + id2);
		}
		MovieEntity movie = entity.get();
		CharacterEntity characer = entityCharacter.get();
		movie.removeCharacter(characer);
		movieRepository.save(movie);
		
	}

	@Override
	public List<MovieBasicDTO> getDetailsByFilter(String name, Long genreId, String order) {
		MovieFilterDTO filter = new MovieFilterDTO(name, genreId, order);
		List<MovieEntity>listEntity=movieRepository.findAll(movieSpecification.getByFilters(filter));
		List<MovieBasicDTO>response= movieMapper.MovieListEntity2BasicDTO(listEntity);
		return response;
	}
}
