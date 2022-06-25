package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.DTO.GenreDTO;
import com.example.demo.entity.GenreEntity;

public interface GenreRepository extends JpaRepository<GenreEntity ,Long> {



}
