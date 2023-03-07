package com.example.yourboard.dto;

import com.example.yourboard.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BoardRequestDto {
    private Long id;
    private String title;
    private String comment;
    private String username;
    private LocalDateTime ModifiedAt;
    private LocalDateTime createdAt;

    public BoardRequestDto(BoardRequestDto boardRequesDto) {
        this.id = boardRequesDto.id;
        this.title = boardRequesDto.title;
        this.comment = boardRequesDto.comment;
        this.username = boardRequesDto.username;
        ModifiedAt = boardRequesDto.ModifiedAt;
        this.createdAt = boardRequesDto.createdAt;
    }
}
