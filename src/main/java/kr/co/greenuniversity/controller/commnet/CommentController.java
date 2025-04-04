package kr.co.greenuniversity.controller.commnet;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.greenuniversity.dto.comment.CommentDTO;
import kr.co.greenuniversity.service.comment.CommentService;
import kr.co.greenuniversity.service.community.Community2Service;
import kr.co.greenuniversity.service.community.CommunityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/*
이름: 김소현
내용: 커뮤니티1,2 테이블 댓글
 */

@Slf4j
@RequiredArgsConstructor
@Controller
public class CommentController {

    private final CommunityService communityService;
    private final CommentService commentService;

    @ResponseBody
    @GetMapping("/comment/list")
    public List<CommentDTO> list(int parent){

        List<CommentDTO> commentDTOList = commentService.findByParent(parent);

        return commentDTOList;
    }

    @ResponseBody
    @PostMapping("/comment/write1")
    public CommentDTO write1(@RequestBody CommentDTO commentDTO, HttpServletRequest request){

        String regip = request.getRemoteAddr();
        commentDTO.setRegip(regip);

        CommentDTO savedCommentDTO = commentService.save(commentDTO);

        return savedCommentDTO;
    }

    @ResponseBody
    @PostMapping("/comment/write2")
    public CommentDTO write2(@RequestBody CommentDTO commentDTO, HttpServletRequest request){

        String regip = request.getRemoteAddr();
        commentDTO.setRegip(regip);

        CommentDTO savedCommentDTO = commentService.save(commentDTO);

        communityService.response(savedCommentDTO.getParent());

        return savedCommentDTO;
    }
}
