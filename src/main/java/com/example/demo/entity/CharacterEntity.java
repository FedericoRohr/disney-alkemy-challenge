package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="characters")
@Getter @Setter
@SQLDelete(sql="UPDATE characters SET deleted = true WHERE id=?")
@Where(clause="deleted=false")
public class CharacterEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String image;

    private String name;

    private Long age;

    private Float weight;

    private String history;

    @ManyToMany(mappedBy = "characters", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<MovieEntity> movies = new ArrayList<>();

    private boolean deleted=Boolean.FALSE;  
	

}
