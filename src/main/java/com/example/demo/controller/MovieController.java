package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.DTO.CharaterBasicDTO;
import com.example.demo.DTO.MovieBasicDTO;
import com.example.demo.DTO.MovieDTO;
import com.example.demo.service.MovieService;

@Controller
@RequestMapping("movies")
public class MovieController {
@Autowired
MovieService movieService;

@PostMapping
public ResponseEntity<MovieDTO>save(@RequestBody MovieDTO dto){
	MovieDTO movie= movieService.save(dto);
	return ResponseEntity.status(HttpStatus.CREATED).body(movie);
	
}

@DeleteMapping("/{id}")
public ResponseEntity<Void>delete(@PathVariable Long id){
	movieService.delete(id);
	return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	
}

@PutMapping("/{id}")
public ResponseEntity<Void>update(@PathVariable Long id , @RequestBody MovieDTO movie){
movieService.update(id, movie);
return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
}
  

@GetMapping("/{id}")
public ResponseEntity<MovieDTO>get(@PathVariable Long id){
	MovieDTO response= movieService.getOne(id);
return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
	
}

@PostMapping("/{id}/characters/{id2}")
public ResponseEntity<Void>addCharacter(@PathVariable Long id , @PathVariable Long id2){
	movieService.addCharater(id,id2);
	return ResponseEntity.status(HttpStatus.CREATED).build();
}

@DeleteMapping("/{id}/characters/{id2}")
public ResponseEntity<Void>removeCharacter(@PathVariable Long id , @PathVariable Long id2){
	movieService.removeCharacter(id,id2);
	return ResponseEntity.status(HttpStatus.CREATED).build();
}


@GetMapping
public ResponseEntity<List<MovieBasicDTO>>getDetailsByFilter(
		@RequestParam(required = false) String name,
        @RequestParam(required = false) Long genre,
        @RequestParam(required=false, defaultValue="ASC") String order
		){
	List<MovieBasicDTO>lista=movieService.getDetailsByFilter(name,genre,order);
	
	return ResponseEntity.status(HttpStatus.OK).body(lista);
}


}
