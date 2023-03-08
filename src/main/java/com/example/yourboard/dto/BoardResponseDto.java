package com.example.yourboard.dto;

import com.example.yourboard.entity.Board;
import com.example.yourboard.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String username;
    private String comment;
    private String title;
    private LocalDateTime ModifiedAt;
    private LocalDateTime createdAt;


    //서비스에서 리턴할때 객체값에 들어가기 때문에 작성해줘야한다. Get으로 내용을 받아옴. 함수처럼 쓰이고 있음.
    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.username = board.getUsername();;
        this.title = board.getTitle();
        this.comment = board.getComment();
        this.ModifiedAt = board.getModifiedAt();
        this.createdAt = board.getCreatedAt();
    }

}
