package com.example.demo.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MovieFilterDTO {
	private String name;
    private Long genre;
    String order;

    public MovieFilterDTO(String name, Long genre, String order) {
        this.name = name;
        this.genre = genre;
        this.order = order;
    }
    public boolean isAsc() {
		return order.compareToIgnoreCase("ASC")==0;
	}
	
}
