package kr.co.greenuniversity.service.community;

import com.querydsl.core.Tuple;
import kr.co.greenuniversity.dto.page.PageRequestDTO;
import kr.co.greenuniversity.dto.page.PageResponseDTO;
import kr.co.greenuniversity.dto.community.Community2DTO;
import kr.co.greenuniversity.entity.community.Community2;
import kr.co.greenuniversity.repository.community.Community2Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/*
이름: 김소현
내용: 커뮤니티2 테이블 목록, 검색
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class Community2Service {

    private final Community2Repository communityRepository2;
    private final ModelMapper modelMapper;

    private boolean checkIfExpired(String deadline) {
        try {
            if (deadline == null || deadline.isBlank()) return false;
            LocalDate dl = LocalDate.parse(deadline);
            return dl.isBefore(LocalDate.now());
        } catch (Exception e) {
            return false;
        }
    }


    // 목록
    public PageResponseDTO findAll(PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<Tuple> pageCommunity = communityRepository2.selectAllForList(pageRequestDTO, pageable);

        List<Community2DTO> communityDTOList = pageCommunity.getContent().stream().map(tuple -> {
            Community2 community = tuple.get(0, Community2.class);
            String name = tuple.get(1, String.class);
            Community2DTO communityDTO = modelMapper.map(community, Community2DTO.class);
            communityDTO.setExpired(checkIfExpired(community.getDeadline()));
            communityDTO.setName(name);
            return communityDTO;
        }).toList();

        int total = (int) pageCommunity.getTotalElements();

        return PageResponseDTO.<Community2DTO>builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(communityDTOList)
                .total(total)
                .build();
    }

    // 검색
    public PageResponseDTO searchAll(PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<Tuple> pageCommunity = communityRepository2.selectAllForSearch(pageRequestDTO, pageable);

        List<Community2DTO> communityDTOList = pageCommunity.getContent().stream().map(tuple -> {
            Community2 community = tuple.get(0, Community2.class);
            String name = tuple.get(1, String.class);
            Community2DTO communityDTO = modelMapper.map(community, Community2DTO.class);
            communityDTO.setName(name);
            return communityDTO;
        }).toList();

        log.info(communityDTOList.toString());

        int total = (int) pageCommunity.getTotalElements();

        return PageResponseDTO.<Community2DTO>builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(communityDTOList)
                .total(total)
                .build();
    }


}