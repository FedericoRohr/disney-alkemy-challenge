package com.example.demo.repository.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.demo.DTO.CharacterFilterDTO;
import com.example.demo.entity.CharacterEntity;
import com.example.demo.entity.MovieEntity;

@Component
public class CharacterSpecification {
	
	public Specification<CharacterEntity>getByFilters(CharacterFilterDTO dto){
		return (root,query,criteriaBuilder) ->{
			List<Predicate> predicates = new ArrayList<>(); 
			
			if(StringUtils.hasLength(dto.getName())) {/// consulta si tiene name 
				predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%"+dto.getName()+"%")); /// agrega el name al predicate
			}
			
			if (dto.getAge() != null) { 
                predicates.add(  
                        criteriaBuilder.equal(root.get("age"), dto.getAge())
                );

            }
			
			if(!CollectionUtils.isEmpty(dto.getMovies())) {
				Join<MovieEntity,CharacterEntity> join= root.join("movies",JoinType.INNER);
				Expression<String> citiesId=join.get("id");
				predicates.add(citiesId.in(dto.getMovies()));
			}
			
			/// remove duplicates
			query.distinct(true);
			
			/// order resolver
			
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			
	
		};
	}


}
