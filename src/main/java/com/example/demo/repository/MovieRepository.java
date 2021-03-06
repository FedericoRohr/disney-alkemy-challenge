package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.entity.MovieEntity;

public interface MovieRepository extends JpaRepository<MovieEntity ,Long> , JpaSpecificationExecutor<MovieEntity>{
	List<MovieEntity>findAll(Specification<MovieEntity>spect);
}
