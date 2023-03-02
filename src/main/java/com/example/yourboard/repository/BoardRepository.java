package com.example.yourboard.repository;

import com.example.yourboard.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

// List<Board> findAll();









 //저기에 들어가는 함수를 모르겠음 findAllByOrderByModifiedAtDesc() 왜 이렇게하면 에러가 안나지?



}
