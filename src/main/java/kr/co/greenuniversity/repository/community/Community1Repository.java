package kr.co.greenuniversity.repository.community;

import kr.co.greenuniversity.entity.community.Community1;
import kr.co.greenuniversity.repository.custom.Community1RepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
이름: 김소현
내용: community1Repository
 */

@Repository
public interface Community1Repository extends JpaRepository<Community1, Integer>, Community1RepositoryCustom {

}