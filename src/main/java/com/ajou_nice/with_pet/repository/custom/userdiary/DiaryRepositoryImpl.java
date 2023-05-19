package com.ajou_nice.with_pet.repository.custom.userdiary;

import static com.ajou_nice.with_pet.domain.entity.QDiary.diary;
import static com.ajou_nice.with_pet.domain.entity.QUserParty.userParty;

import com.ajou_nice.with_pet.domain.entity.Diary;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;


public class DiaryRepositoryImpl extends QuerydslRepositorySupport implements DiaryRepositoryCustom {

    @Autowired
    private JPAQueryFactory queryFactory;

    public DiaryRepositoryImpl() {
        super(Diary.class);
    }

    @Override
    public List<Diary> findByMonthDate(Long userId, Long dogId, Long categoryId,
            LocalDate month) {

        List<Diary> userDiaries = queryFactory.select(diary).from(diary, userParty)
                .where(diary.dog.party.eq(userParty.party).and(userParty.user.userId.eq(userId)
                                .and(diary.createdAt.between(month.withDayOfMonth(1),
                                        month.withDayOfMonth(month.lengthOfMonth())))),
                        containsDog(dogId), containsCategory(categoryId))
                .orderBy(diary.createdAt.desc())
                .fetch();
        return userDiaries;
    }

    @Override
    public List<Diary> findByDayDate(Long userId, Long dogId, Long categoryId,
            LocalDate day) {
        List<Diary> userDiaries = queryFactory.select(diary).from(diary, userParty)
                .where(diary.dog.party.eq(userParty.party).and(userParty.user.userId.eq(userId)
                                .and(diary.createdAt.eq(day))),
                        containsDog(dogId), containsCategory(categoryId))
                .orderBy(diary.createdAt.desc())
                .fetch();
        return userDiaries;
    }

    private BooleanExpression containsDog(Long dogId) {
        if (dogId == null) {
            return null;
        }
        return diary.dog.dogId.eq(dogId);
    }

    private BooleanExpression containsCategory(Long categoryId) {
        if (categoryId == null) {
            return null;
        }
        return diary.category.categoryId.eq(categoryId);
    }

}