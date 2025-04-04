package kr.co.greenuniversity.service.comment;


import kr.co.greenuniversity.dto.comment.CommentDTO;
import kr.co.greenuniversity.entity.comment.Comment;
import kr.co.greenuniversity.entity.user.User;
import kr.co.greenuniversity.repository.comment.CommentRepository;
import kr.co.greenuniversity.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/*
이름: 김소현
내용: 커뮤니티1,2 테이블 댓글
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<CommentDTO> findByParent(int parent) {

        List<Comment> commentList = commentRepository.findByParent(parent);

        List<CommentDTO> commentDTOList = commentList.stream().map(entity -> {
            CommentDTO commentDTO = modelMapper.map(entity, CommentDTO.class);
            return commentDTO;
        }).toList();

        log.info("commentDTOList: {}", commentDTOList);

        return commentDTOList;
    }

    public CommentDTO save(CommentDTO commentDTO){

        User user = userRepository.findById(commentDTO.getWriter()).get();

        Comment comment = modelMapper.map(commentDTO, Comment.class);
        comment.setUser(user);

        Comment savedComment = commentRepository.save(comment);

        log.info("saved comment {}", savedComment);

        return modelMapper.map(savedComment, CommentDTO.class);
    }

}
