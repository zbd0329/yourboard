package com.example.yourboard.entity;

import com.example.yourboard.dto.BoardRequestDto;
import com.example.yourboard.dto.BoardResponseDto;
import com.example.yourboard.repository.BoardRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@NoArgsConstructor
public class Board extends TimeStamped {
    //요거 뭐가 문제냐

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title; //제목

    @Column(nullable = false)
    private String content; //비밀번호

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; //어떻게 저장이 되는가

//    @OneToMany(mappedBy = "board" ,cascade = CascadeType.REMOVE)// 글 하나가 삭제되면 맵핑되어있는 쪽 테이블이름!!! 글도 삭제되는 cascade 연속성 전이 속성
//    @OrderBy("createdAt desc")// 엔티티단에서 정렬
//    private List<Comment> commentList = new ArrayList<>();

    public Board(BoardRequestDto boardRequestDto, User user) {
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
        this.user = user;
    }

//시도를 하다보면 읽힌다. 에러가 어떤 것인지 파악하는게 중요하다.

    public void update(BoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
    }

    public void delete(BoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
    }

}

//초심 : 처음 마음
//에러 하나 하나 뜯어보는게
//왜 그랬을까 생각해보자.
//무작정 쓰는건 로또 당첨을 기다리는 것과 다름없다. 김선용 튜터 명언 1장
//로또당첨은 실력이 아니다. 운이다.
//논리적으로 생각하면서
//코드를 뜯어볼 시간이 없어요
//


