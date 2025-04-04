package kr.co.greenuniversity.repository.custom;

import com.querydsl.core.Tuple;
import kr.co.greenuniversity.dto.page.PageRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/*
이름: 김소현
내용: community2RepositoryCustom
 */

public interface Community2RepositoryCustom {

    public Page<Tuple> selectAllForList(PageRequestDTO pageRequestDTO, Pageable pageable);

    public Page<Tuple> selectAllForSearch(PageRequestDTO pageRequestDTO, Pageable pageable);
}
