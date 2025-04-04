package kr.co.greenuniversity.repository.impl;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.greenuniversity.dto.page.PageRequestDTO;

import kr.co.greenuniversity.entity.community.QCommunity1;
import kr.co.greenuniversity.entity.user.QUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
이름: 김소현
내용: community1RepositoryImpl
 */

@Slf4j
@RequiredArgsConstructor
@Repository
public class Community1RepositoryImpl implements kr.co.greenuniversity.repository.custom.Community1RepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private QCommunity1 qCommunity = QCommunity1.community1;
    private QUser qUser = QUser.user;

    @Override
    public Page<Tuple> selectAllForList(PageRequestDTO pageRequestDTO, Pageable pageable) {

        String cate = pageRequestDTO.getCate();

        BooleanExpression expression = qCommunity.cate.contains(cate);

        List<Tuple> tupleList = queryFactory
                .select(qCommunity, qUser.name)
                .from(qCommunity)
                .join(qUser)
                .on(qCommunity.user.id.eq(qUser.id))
                .where(expression)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qCommunity.no.desc())
                .fetch();

        long total = queryFactory
                .select(qCommunity.count())
                .from(qCommunity)
                .join(qUser)
                .on(qCommunity.user.id.eq(qUser.id))
                .where(expression)
                .fetchOne();

        return new PageImpl<>(tupleList, pageable, total);
    }

    @Override
    public Page<Tuple> selectAllForSearch(PageRequestDTO pageRequestDTO, Pageable pageable) {

        String cate = pageRequestDTO.getCate();
        String searchType = pageRequestDTO.getSearchType();
        String keyword = pageRequestDTO.getKeyword();

        BooleanExpression expression = qCommunity.cate.eq(cate);

        if (keyword != null && !keyword.isBlank()) {
            BooleanExpression searchExpr = null;

            if ("title".equals(searchType)) {
                searchExpr = qCommunity.title.contains(keyword);
            } else if ("content".equals(searchType)) {
                searchExpr = qCommunity.content.contains(keyword);
            } else if ("writer".equals(searchType)) {
                searchExpr = qCommunity.user.name.contains(keyword);
            }

            if (searchExpr != null) {
                expression = expression.and(searchExpr);
            }
        }

        List<Tuple> tupleList = queryFactory
                .select(qCommunity, qUser.name)
                .from(qCommunity)
                .join(qUser)
                .on(qCommunity.user.id.eq(qUser.id))
                .where(expression)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qCommunity.no.desc())
                .fetch();

        long total = queryFactory
                .select(qCommunity.count())
                .from(qCommunity)
                .join(qUser)
                .on(qCommunity.user.id.eq(qUser.id))
                .where(expression)
                .fetchOne();

        return new PageImpl<>(tupleList, pageable, total);
    }
}
