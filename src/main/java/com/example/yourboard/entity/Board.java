package com.example.yourboard.entity;

import com.example.yourboard.dto.BoardRequestDto;
import com.example.yourboard.dto.BoardResponseDto;
import com.example.yourboard.repository.BoardRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor
public class Board extends TimeStamped {
    //요거 뭐가 문제냐

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title; //이름

    @Column(nullable = false)
    private String content; //비밀번호

    @Column(nullable = false)
    private String author; //제목

    @Column(nullable = false)
    private String password; //내용

    public Board(BoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
        this.author = boardRequestDto.getAuthor();
        this.password = boardRequestDto.getPassword();
    }



    public void update(BoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
        this.author = boardRequestDto.getAuthor();
        this.password = boardRequestDto.getPassword();
    }

    public void delete(BoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
        this.author = boardRequestDto.getAuthor();
        this.password = boardRequestDto.getPassword();
    }


}


    //    public Board(BoardDto boardDto) {
////        this.name = boardDto.getName();
////        this.title = boardDto.getTitle();
////        this.contents = boardDto.getContents();
////        this.pw = boardDto.getPw();
//
//    }

