package kr.co.greenuniversity.repository.community;

import kr.co.greenuniversity.entity.community.Community2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
이름: 김소현
내용: community2Repository
 */

@Repository
public interface Community2Repository extends JpaRepository<Community2, Integer>, kr.co.greenuniversity.repository.custom.Community2RepositoryCustom {
}
