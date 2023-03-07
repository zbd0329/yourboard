package com.example.yourboard.entity;

import com.example.yourboard.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor
@ToString
public class Board extends TimeStamped {
    //요거 뭐가 문제냐

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title; //제목

    @Column(nullable = false)
    private String comment; //

    @Column(nullable = false)
    private String username;


    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;


    public Board(BoardRequestDto boardRequestDto, User user) {
        this.title = boardRequestDto.getTitle();
        this.comment = boardRequestDto.getComment();
        this.username = user.getUsername();
        this.user = user;
    }


    public void update(BoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
        this.comment = boardRequestDto.getComment();


    }

    public void delete(BoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
        this.comment = boardRequestDto.getComment();
    }

}
