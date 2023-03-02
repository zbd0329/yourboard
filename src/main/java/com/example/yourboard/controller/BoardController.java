package com.example.yourboard.controller;


import com.example.yourboard.dto.BoardRequestDto;
import com.example.yourboard.dto.BoardResponseDto;
import com.example.yourboard.entity.Board;
import com.example.yourboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService; //서비스 변수는 여기서 왜 필요한가?

    @PostMapping("/api/post") //게시글 저장
    public Board writeBoard(@RequestBody BoardRequestDto boardDto) {

        return boardService.writeBoard(boardDto);
    }


    @GetMapping("/api/posts") //게시글 전체 조회
    public List<Board> getBoard(){
        return boardService.getBoard();
    }

    @GetMapping("/api/find/{id}") //게시글 세부조회
    public BoardResponseDto findBoard(@PathVariable Long id) {
        return boardService.findBoard(id); //소대문자 구문잘해서 쓰자
    }

    @PutMapping("/api/post/{id}") //비밀번호 조회 후 게시글 수정
    public BoardResponseDto updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto)
    {return boardService.updateBoard(id,boardRequestDto);}

    @DeleteMapping("/api/delete/{id}")
    public Long deleteBoard(@PathVariable Long id) {
        return boardService.deleteBoard(id);
    }






    }

//Install the plugin
//ENTER POWER MODE in Preferences > Appearance > Power mode II
//CHANGE THE SLIDERS and options to y



//게시글 수정
//path로 id받기 어노테이션에 따라서 쓰는 코드가 달라짐
//팁 : 컨트롤러에서 id를 못찾아옴 콘솔로 sout 찍어보는게 좋음. 그럼 null이 뜸 . 체크하는 방법. 경우의 수를 나눠서 체크하기
//로그인하는 방법 공부하기



