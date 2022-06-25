package com.example.demo.repository.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.example.demo.DTO.MovieFilterDTO;
import com.example.demo.entity.GenreEntity;
import com.example.demo.entity.MovieEntity;


@Component
public class MovieSpecification {

	public Specification<MovieEntity>getByFilters(MovieFilterDTO dto){
		return (root,query,criteriaBuilder) ->{
			List<Predicate> predicates = new ArrayList<>(); 
			
			if(StringUtils.hasLength(dto.getName())) {
				predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%"+dto.getName()+"%")); 
			}
			
			 if (dto.getGenre() != null) {
	                Join<GenreEntity, MovieEntity> join = root.join("genre", JoinType.INNER);
	                Expression<String> genreId = join.get("id");
	                predicates.add(genreId.in(dto.getGenre()));
	            }
			
			
			query.distinct(true);
			
			
			String orderByField="creationDate";
			query.orderBy(dto.isAsc()? criteriaBuilder.asc(root.get(orderByField)):criteriaBuilder.desc(root.get(orderByField)));
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			
	
		};
	}
	
}
