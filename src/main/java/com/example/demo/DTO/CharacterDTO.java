package com.example.demo.DTO;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.MovieEntity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CharacterDTO {
	
	private Long id;
	
	private String image;

    private String name;

    private Long age;

    private Float weight;

    private String history;
    
    private List<MovieDTO> movies = new ArrayList<>();

}
