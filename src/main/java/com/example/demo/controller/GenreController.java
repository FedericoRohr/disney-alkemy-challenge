package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.DTO.GenreDTO;
import com.example.demo.service.GenreService;

@Controller
@RequestMapping("genre")
public class GenreController {
	@Autowired
	GenreService genreService;
	
	@PostMapping
	public ResponseEntity<GenreDTO>save(@RequestBody GenreDTO dto){
		GenreDTO genre= genreService.save(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(genre);
		
	}
	
	

}
