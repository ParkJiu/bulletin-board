package com.example.backendboard.services;

import com.example.backendboard.model.Header;
import com.example.backendboard.model.Pagination;
import com.example.backendboard.entity.BoardEntity;
import com.example.backendboard.entity.BoardRepository;
import com.example.backendboard.web.dtos.BoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 게시글 목록 가져오기
     */
    public Header<List<BoardDto>> getBoardList(Pageable pageable) {
        List<BoardDto> dtos = new ArrayList<>();

        Page<BoardEntity> boardEntities = boardRepository.findAllByOrderByIdxDesc(pageable);
        //Repository에서 새로 정의한 메소드, findAllByOrderByIdxDesc(Pageable pageable) ⇒ idx를 역순으로 모두 찾는
        for (BoardEntity entity : boardEntities) {
            BoardDto dto = BoardDto.builder()
                    .idx(entity.getIdx())
                    .author(entity.getAuthor())
                    .title(entity.getTitle())
                    .contents(entity.getContents())
                    .createdAt(entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                    .build();

            dtos.add(dto);
        }

        Pagination pagination = new Pagination(
                (int) boardEntities.getTotalElements()
                , pageable.getPageNumber() + 1
                , pageable.getPageSize()
                , 10
        );

        return Header.OK(dtos, pagination);
    }
    //만들어진 dto를 list로, header에 담아서 보내주는 ?

    /**
     * 게시글 가져오기
     */
    public BoardDto getBoard(Long id) {
        BoardEntity entity = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        return BoardDto.builder()
                .idx(entity.getIdx())
                .title(entity.getTitle())
                .contents(entity.getContents())
                .author(entity.getAuthor())
                .createdAt(entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                .build();
    }
    //Repository가 DB에서 entity를 찾고 해당 entity에 저장되어있던 값들을 Dto에 넘겨서 dto를 생성
    //dto는 controller와 service 등에서 소통하기 위해(?) 필요하고, entity는 Repository가 db랑 소통하기 위해서 필요

    /**
     * 게시글 등록
     */
    public BoardEntity create(BoardDto boardDto) {
        BoardEntity entity = BoardEntity.builder()
                .title(boardDto.getTitle())
                .contents(boardDto.getContents())
                .author(boardDto.getAuthor())
                .createdAt(LocalDateTime.now())
                .build();
        return boardRepository.save(entity);
    }
    //새로 만드니까 Repository가 DB랑 소통할 필요 없이 Entity를 새로 빌드하고 dto로 받아온 값들을 각각 할당해주고 Repository를 이용하여 DB에 entity를 저장

    /**
     * 게시글 수정
     */
    public BoardEntity update(BoardDto boardDto) {
        //orElseThrow 는 return 되는 value가 있으면 value를 주고 아니면 RuntimeException을 return
        BoardEntity entity = boardRepository.findById(boardDto.getIdx()).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        entity.setTitle(boardDto.getTitle());
        entity.setContents(boardDto.getContents());
        return boardRepository.save(entity);
    }
    //Repository가 DB랑 소통하니까, dto로 받은 id로 entity를 받고 그 entity에다가 dto로 받은 값들을 할당해서 Repository가 DB에 entity를 새로 저장하는 !

    /**
     * 게시글 삭제
     */
    public void delete(Long id) {
        BoardEntity entity = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        boardRepository.delete(entity);
    }
    //Repository가 entity를 찾고 DB에서 해당 entity를 삭제
}