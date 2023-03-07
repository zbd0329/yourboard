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

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.username = board.getUsername();;
        this.title = board.getTitle();
        this.comment = board.getComment();
        this.ModifiedAt = board.getModifiedAt();
        this.createdAt = board.getCreatedAt();
    }

}
