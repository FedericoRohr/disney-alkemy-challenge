package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.DTO.CharacterDTO;
import com.example.demo.DTO.CharaterBasicDTO;
import com.example.demo.service.CharacterService;

@Controller
@RequestMapping("characters")
public class CharacterController {
@Autowired
CharacterService characterService;

@PostMapping
public ResponseEntity<CharacterDTO>save(@RequestBody CharacterDTO dto){
	CharacterDTO character= characterService.save(dto);
	return ResponseEntity.status(HttpStatus.CREATED).body(character);
}
@DeleteMapping("/{id}")
public ResponseEntity<Void>delete(@PathVariable Long id){
	characterService.delete(id);
	return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	
}
	
@PutMapping("/{id}")
public ResponseEntity<Void>update(@PathVariable Long id , @RequestBody CharacterDTO character){
characterService.update(id, character);
return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
}


@GetMapping("/{id}")
public ResponseEntity<CharacterDTO>get(@PathVariable Long id){
	CharacterDTO response= characterService.getOne(id);
return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
	
}

@GetMapping
public ResponseEntity<List<CharaterBasicDTO>>getDetailsByFilter(
		@RequestParam(required = false) String name,
        @RequestParam(required = false) Long age,
		@RequestParam(required=false) List<Long> movies
		){
	List<CharaterBasicDTO>lista=characterService.getDetailsByFilter(name,age,movies);
	
	return ResponseEntity.status(HttpStatus.OK).body(lista);
}

}
