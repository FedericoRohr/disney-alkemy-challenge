package com.example.demo.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.CharacterDTO;
import com.example.demo.DTO.CharacterFilterDTO;
import com.example.demo.DTO.CharaterBasicDTO;
import com.example.demo.entity.CharacterEntity;
import com.example.demo.exeption.ParamNotFound;
import com.example.demo.mapper.CharacterMapper;
import com.example.demo.repository.CharacterRepository;
import com.example.demo.repository.specification.CharacterSpecification;
import com.example.demo.service.CharacterService;

@Service
public class CharacterServiceImp implements CharacterService {
    @Autowired
	CharacterMapper characterMapper;
    @Autowired
   	CharacterRepository characterRepository;
    @Autowired
    CharacterSpecification characterSpecification;
    
	
	
	@Override
	public CharacterDTO save(CharacterDTO dto) {
	CharacterEntity entity= characterMapper.CharacterDTO2Entity(dto);
	entity= characterRepository.save(entity);
	CharacterDTO response = characterMapper.CharacterEntity2DTO(entity,false);
		return response;
	}



	@Override
	public void delete(Long id) {
	characterRepository.deleteById(id);	
	}



	@Override
	public void update(Long id, CharacterDTO character) {
		Optional<CharacterEntity>entity=characterRepository.findById(id);
		if(!entity.isPresent()) {
			throw new ParamNotFound("no movie with id: "+ id);
		}
		CharacterEntity exist=entity.get();
		exist= characterMapper.update(exist,character);
		characterRepository.save(exist);
		
	}



	@Override
	public CharacterDTO getOne(Long id) {
		Optional<CharacterEntity>entity=characterRepository.findById(id);
		if(!entity.isPresent()) {
			throw new ParamNotFound("no movie with id: "+ id);
		}
		CharacterEntity exist=entity.get();
		return characterMapper.CharacterEntity2DTO(exist,true);
		
	}



	@Override
	public List<CharaterBasicDTO> getDetailsByFilter(String name, Long age, List<Long> movies) {
		CharacterFilterDTO filterDTO=new CharacterFilterDTO(name, age, movies);
		List<CharacterEntity>charactersEntity= characterRepository.findAll(characterSpecification.getByFilters(filterDTO));
		List<CharaterBasicDTO>basicDTOs=characterMapper.characterBasicEntity2DTOList(charactersEntity);
		return basicDTOs;
	}
	

}
