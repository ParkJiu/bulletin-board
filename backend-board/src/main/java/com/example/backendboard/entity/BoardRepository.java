package com.example.backendboard.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//pageable은 JPA에서 제공하는 객체
import org.springframework.data.jpa.repository.JpaRepository;

//Entity를 통해서 데이터를 DB에 저장
//Entity는 DB의 데이터와 매칭되는 것, 실제 DB에 데이터를 저장하는 건 Repository의 클래스를 통해 이루어짐, DAO
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    Page<BoardEntity> findAllByOrderByIdxDesc(Pageable pageable);
}
