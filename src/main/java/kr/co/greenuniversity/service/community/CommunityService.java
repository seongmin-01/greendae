package kr.co.greenuniversity.service.community;

import kr.co.greenuniversity.dto.community.Community2DTO;
import kr.co.greenuniversity.dto.community.Community1DTO;
import kr.co.greenuniversity.entity.community.Community1;
import kr.co.greenuniversity.entity.community.Community2;
import kr.co.greenuniversity.entity.user.User;
import kr.co.greenuniversity.repository.comment.CommentRepository;
import kr.co.greenuniversity.repository.community.Community1Repository;
import kr.co.greenuniversity.repository.community.Community2Repository;
import kr.co.greenuniversity.repository.file.FileRepository;
import kr.co.greenuniversity.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/*
이름: 김소현
내용: 커뮤니티1,2 테이블 글 보기, 쓰기, 삭제, 수정, 비밀번호, 조회수
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class CommunityService {

    private final Community1Repository community1Repository;
    private final Community2Repository community2Repository;
    private final CommentRepository commentRepository;
    private final FileRepository fileRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    // 글보기
    public Community1DTO findById1(int no) {
        Optional<Community1> optCommunity = community1Repository.findById(no);

        if (optCommunity.isPresent()) {
            Community1 community = optCommunity.get();
            Community1DTO communityDTO = modelMapper.map(community, Community1DTO.class);
            return communityDTO;
        }
        return null;
    }

    public Community2DTO findById2(int no) {
        Optional<Community2> optCommunity = community2Repository.findById(no);

        if (optCommunity.isPresent()) {
            Community2 community = optCommunity.get();
            Community2DTO communityDTO = modelMapper.map(community, Community2DTO.class);
            return communityDTO;
        }
        return null;
    }


    // 글쓰기
    public int register1(Community1DTO communityDTO) {
        User user = User.builder()
                .id(communityDTO.getWriter())
                .build();

        Community1 community = modelMapper.map(communityDTO, Community1.class);
        community.setUser(user);

        Community1 savedCommunity = community1Repository.save(community);

        return savedCommunity.getNo();
    }

    public int register2(Community2DTO communityDTO) {
        User user = User.builder()
                .id(communityDTO.getWriter())
                .build();

        Community2 community = modelMapper.map(communityDTO, Community2.class);
        community.setUser(user);

        log.info("CommunityDTO: " + communityDTO);

        Community2 savedCommunity = community2Repository.save(community);

        log.info("saved community: " + savedCommunity);

        return savedCommunity.getNo();
    }

    @Transactional
    public void response(int parent) {
        Community2 community = community2Repository.findById(parent)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        community.setStatus("resp");
    }

    // 글 삭제
    public void delete1(int no) {
        community1Repository.deleteById(no);
        commentRepository.deleteById(no);
        fileRepository.deleteById(no);
    }

    public void delete2(int no) {
        community2Repository.deleteById(no);
        commentRepository.deleteById(no);
        fileRepository.deleteById(no);
    }


    // 글 수정
    public Community1DTO modify1(int no) {
        Community1 community = community1Repository.findById(no).get();
        return modelMapper.map(community, Community1DTO.class);
    }

    public Community2DTO modify2(int no) {
        Community2 community = community2Repository.findById(no).get();
        return modelMapper.map(community, Community2DTO.class);
    }

    @Transactional
    public void update1(Community1DTO communityDTO) {
        Community1 community1 = community1Repository.findById(communityDTO.getNo())
                .orElseThrow(() -> new RuntimeException("해당 게시글이 존재하지 않습니다."));

        String writerUid = communityDTO.getWriter();
        User user = userRepository.findById(writerUid)
                .orElseThrow(() -> new RuntimeException("작성자 정보를 찾을 수 없습니다."));

        modelMapper.map(communityDTO, community1);
        community1.setUser(user);
    }

    @Transactional
    public void update2(Community2DTO communityDTO) {
        Community2 community2 = community2Repository.findById(communityDTO.getNo())
                .orElseThrow(() -> new RuntimeException("해당 게시글이 존재하지 않습니다."));

        String writerUid = communityDTO.getWriter();
        User user = userRepository.findById(writerUid)
                .orElseThrow(() -> new RuntimeException("작성자 정보를 찾을 수 없습니다."));

        modelMapper.map(communityDTO, community2);
        community2.setUser(user);
    }


    // 글 비밀번호
    public boolean checkPassword(int no, String password) {
        // 글 번호로 글 엔티티를 조회
        Optional<Community2> optionalPost = community2Repository.findById(no);
        if (optionalPost.isEmpty()) return false;

        Community2 post = optionalPost.get();

        // 비밀번호 비교 (평문 저장이라면 단순 비교, 해시라면 BCrypt 등 사용)
        return password.equals(post.getPassword());
    }

    // 글 조회수
    @Transactional
    public void increaseHit1(int no) {
        Community1 community = community1Repository.findById(no)
                .orElseThrow(() -> new RuntimeException("해당 게시글이 존재하지 않습니다."));
        community.setHit(community.getHit() + 1);
    }

    @Transactional
    public void increaseHit2(int no) {
        Community2 community = community2Repository.findById(no)
                .orElseThrow(() -> new RuntimeException("해당 게시글이 존재하지 않습니다."));
        community.setHit(community.getHit() + 1);
    }





}