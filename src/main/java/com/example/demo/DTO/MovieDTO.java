package com.example.demo.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.CharacterEntity;
import com.example.demo.entity.GenreEntity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MovieDTO {
	private Long id;

	private String image;

	private String title;
	
	private LocalDate creationDate;
	
	private int calification;

	private GenreEntity genre;
	
	private Long genreId;
	
	private List<CharacterDTO>characters = new ArrayList<>();
}
