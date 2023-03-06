package com.example.yourboard.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.attoparser.dom.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;


    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    //JPA 관계 설정 //무슨 의미의 코드 인지 찾아볼 것
    @OneToMany
    List<Board> comments = new ArrayList<>();


    public User(String username, String password, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}

//pk와 fk 개념은 아는데 이게 어떻게 연결이 되는가
//이넘은 왜 쓰는가. 구분짓기 위해
//다른 타입의 유저를 서로 구분짓기 위해 쓴다.
//데이터 베이스 테이블 관계를 알아야 써진다.
//sql강의 보는게 좋다. 구조형 데이터베이스를 좀 알면 좋다. 딸깍좌.




