package com.example.yourboard.dto;

import lombok.Getter;

@Getter
public class BoardRequestDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String password;

}
