package com.example.demo.service;

import java.util.List;

import com.example.demo.DTO.CharacterDTO;
import com.example.demo.DTO.CharaterBasicDTO;


public interface CharacterService {

	CharacterDTO save(CharacterDTO dto);

	void delete(Long id);

	void update(Long id, CharacterDTO character);

	CharacterDTO getOne(Long id);

	List<CharaterBasicDTO> getDetailsByFilter(String name, Long age, List<Long> movies);

	

}
