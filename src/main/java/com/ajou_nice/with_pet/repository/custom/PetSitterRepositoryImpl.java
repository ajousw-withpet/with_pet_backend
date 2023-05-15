package com.ajou_nice.with_pet.repository.custom;


import static com.ajou_nice.with_pet.domain.entity.QPetSitter.petSitter;

import com.ajou_nice.with_pet.domain.entity.PetSitter;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Slf4j
public class PetSitterRepositoryImpl extends QuerydslRepositorySupport implements PetSitterRespositoryCustom{

	@Autowired
	private JPAQueryFactory queryFactory;

	public PetSitterRepositoryImpl(){
		super(PetSitter.class);
	}

	@Override
	public Page<PetSitter> searchPage(Pageable pageable){
		List<PetSitter> petSitters = queryFactory.select(petSitter)
				.from(petSitter)
				.where(petSitter.valid.eq(true)).fetch();
		int start = (int) pageable.getOffset();
		int end = Math.min((start+ pageable.getPageSize()), petSitters.size());
		return new PageImpl<PetSitter>(petSitters.subList(start,end), pageable, petSitters.size());
	}

}