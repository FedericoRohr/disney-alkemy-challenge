package com.example.demo.DTO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class CharacterFilterDTO {
	 private String name;
     private Long age;
     private List<Long> movies;
     
     
     public CharacterFilterDTO(String name, Long age, List<Long> movies) {
         this.name = name;
         this.age = age;
         this.movies = movies;
         }

}
