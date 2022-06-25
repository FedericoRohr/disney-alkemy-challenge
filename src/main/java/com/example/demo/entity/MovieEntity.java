package com.example.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "movie")
@Getter @Setter
@SQLDelete(sql="UPDATE movie SET deleted = true WHERE id=?")
@Where(clause="deleted=false")
public class MovieEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String image;

	private String title;

	@Column(name = "creation_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate creationDate;

    @Range(min=0 , max =5)
	private int calification;

	@ManyToOne(fetch = FetchType.EAGER,cascade= CascadeType.ALL)
	@JoinColumn(name = "genre_id", insertable = false, updatable = false)
	private GenreEntity genre;
	
	@Column(name = "genre_id", nullable = false)
	private Long genreId;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "movie_character", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "character_id"))
	private List<CharacterEntity>characters = new ArrayList<>();
	
	private boolean deleted = Boolean.FALSE;
	
	public void addCharacter(CharacterEntity character) {
		characters.add(character);
	}
	
	public void removeCharacter(CharacterEntity character) {
		characters.remove(character);
	}

}
