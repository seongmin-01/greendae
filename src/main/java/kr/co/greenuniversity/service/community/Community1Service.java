package kr.co.greenuniversity.service.community;

import com.querydsl.core.Tuple;
import kr.co.greenuniversity.dto.page.PageRequestDTO;
import kr.co.greenuniversity.dto.page.PageResponseDTO;
import kr.co.greenuniversity.dto.community.Community1DTO;
import kr.co.greenuniversity.entity.community.Community1;
import kr.co.greenuniversity.repository.community.Community1Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/*
이름: 김소현
내용: 커뮤니티1 테이블 목록, 검색
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class Community1Service {

    private final Community1Repository communityRepository1;
    private final ModelMapper modelMapper;

    // 목록
    public PageResponseDTO findAll(PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<Tuple> pageCommunity = communityRepository1.selectAllForList(pageRequestDTO, pageable);

        List<Community1DTO> communityDTOList = pageCommunity.getContent().stream().map(tuple -> {
            Community1 community = tuple.get(0, Community1.class);
            String name = tuple.get(1, String.class);
            Community1DTO communityDTO = modelMapper.map(community, Community1DTO.class);
            communityDTO.setName(name);
            return communityDTO;
        }).toList();

        int total = (int) pageCommunity.getTotalElements();

        return PageResponseDTO.<Community1DTO>builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(communityDTOList)
                .total(total)
                .build();
    }

    // 검색
    public PageResponseDTO searchAll(PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<Tuple> pageCommunity = communityRepository1.selectAllForSearch(pageRequestDTO, pageable);

        List<Community1DTO> communityDTOList = pageCommunity.getContent().stream().map(tuple -> {
            Community1 community = tuple.get(0, Community1.class);
            String name = tuple.get(1, String.class);
            Community1DTO communityDTO = modelMapper.map(community, Community1DTO.class);
            communityDTO.setName(name);
            return communityDTO;
        }).toList();

        int total = (int) pageCommunity.getTotalElements();

        return PageResponseDTO.<Community1DTO>builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(communityDTOList)
                .total(total)
                .build();
    }


}