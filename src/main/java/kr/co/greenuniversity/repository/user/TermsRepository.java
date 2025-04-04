package kr.co.greenuniversity.repository.user;

import kr.co.greenuniversity.entity.user.Terms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermsRepository extends JpaRepository<Terms, Integer> {
}
