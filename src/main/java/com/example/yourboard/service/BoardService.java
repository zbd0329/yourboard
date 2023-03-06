package com.example.yourboard.service;

import com.example.yourboard.dto.BoardRequestDto;
import com.example.yourboard.dto.BoardResponseDto;
import com.example.yourboard.entity.Board;
import com.example.yourboard.entity.User;
import com.example.yourboard.jwt.JwtUtil;
import com.example.yourboard.repository.BoardRepository;
import com.example.yourboard.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardService {


    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    //게시글 저장
    public Board writeBoard(BoardRequestDto boardDto, HttpServletRequest requestDto) {

        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(requestDto);

        Claims claims;

        // 토큰이 있는 경우에만 게시글 저장 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            // 요청받은 DTO 로 DB에 저장할 객체 만들기
            Board board = new Board(boardDto,user);
            boardRepository.save(board);
            return board;

        } else {
            return null;
        }

    }

    //게시글 확인
    @Transactional(readOnly = true)
    public List<Board> getBoard() {
        List<BoardResponseDto> boardResponseDto;
        return boardRepository.findAllByOrderByCreatedAtDesc(); //findAll을 써도 되는가
    }


    //게시글 세부 조회
    @Transactional
    public BoardResponseDto findBoard(Long id) {
        Board boards = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        ); //odElseThrow가 있는 이유 : null 이슈가 생길 수 있어서
        return new BoardResponseDto(boards);
    }





}





//
//
//    //게시글 수정
//    @Transactional
//    public BoardResponseDto updateBoard(Long id, BoardRequestDto boardRequestDto) {
//        Board board = checkBoard(id);
//        if (board.getPassword().equals(boardRequestDto.getPassword())) {
//            board.update(boardRequestDto);
//        } else {
//            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
//
//        }
//
//        return new BoardResponseDto(board);
//    }
//
//    //비밀번호 확인
//    private Board checkBoard(Long id) {
//        return boardRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("일치하는 게시글 없또")
//        );
//    }
//
//
//    @Transactional
//    public void deleteBoard(Long id, BoardRequestDto boardRequestDto) {
//        Board board = checkBoard(id);
//        if (board.getPassword().equals(boardRequestDto.getPassword())) {
//            boardRepository.deleteById(id);
//        } else {
//            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
//        }
//
//    }

//        Board board = new Board(boardDto, user); //생성자가 있어야 함
//        boardRepository.save(board);
//        return board;

//쉬프트 에프6 한번에 바꾸










