package com.example.demo.DTO;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MovieBasicDTO {
	    private String image;
	    private String title;
	    private LocalDate creationDate;
}
