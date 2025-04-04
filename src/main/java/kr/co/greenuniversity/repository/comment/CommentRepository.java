package kr.co.greenuniversity.repository.comment;

import kr.co.greenuniversity.entity.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
이름: 김소현
내용: commentRepository
 */

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {


    public List<Comment> findByParent(int parent);
}