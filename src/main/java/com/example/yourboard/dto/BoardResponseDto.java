package com.example.yourboard.dto;

import com.example.yourboard.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String username;
    private String content;
    private String title;




    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();

    }
}
