package com.example.yourboard.service;

import com.example.yourboard.dto.BoardRequestDto;
import com.example.yourboard.dto.BoardResponseDto;
import com.example.yourboard.dto.StatusCodeDto;
import com.example.yourboard.entity.Board;
import com.example.yourboard.entity.User;
import com.example.yourboard.jwt.JwtUtil;
import com.example.yourboard.repository.BoardRepository;
import com.example.yourboard.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BoardService {


    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    //게시글 저장
    public BoardResponseDto writeBoard(BoardRequestDto boardDto, HttpServletRequest requestDto) {

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
            return new BoardResponseDto(board);

        } else {
            throw new IllegalArgumentException("Token Error");
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

    //게시글 수정
    @Transactional
    public BoardResponseDto updateBoard(Long id, @RequestBody BoardRequestDto boardDto, HttpServletRequest requestDto) {

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

            //이 게시글이 작성자가 맞는지 확인하는 검사.// 이 부분 코드 리뷰 필요
           if (!boardRepository.findByIdAndUserId(id, user.getId()).isPresent()) {
                throw new IllegalArgumentException("게시글 작성자가 아닙니다.");
            }


            // 요청받은 게시글 정보 확인하여 객체에 넣기.
            Board board = boardRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
            ); //예외처리는 의미 부여가 명확해야한다.

            //확인한 게시글 내용을 받아온 내용으로 수정
            board.update(boardDto);
            return new BoardResponseDto(board);


        } else {
            return null;
        }

    }


    public ResponseEntity<StatusCodeDto> deleteBoard(Long id,BoardRequestDto boardDto, HttpServletRequest requestDto) {
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

            //이 게시글이 작성자가 맞는지 확인하는 검사.// 이 부분 코드 리뷰 필요
            if (!boardRepository.findByIdAndUserId(id, user.getId()).isPresent()) {
                throw new IllegalArgumentException("게시글 작성자가 아닙니다.");
            }


            // 요청받은 게시글 정보 확인하여 객체에 넣기.
            Board board = boardRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
            ); //예외처리는 의미 부여가 명확해야한다.

            boardRepository.deleteById(board.getId());
            return ResponseEntity.ok(new StatusCodeDto(HttpStatus.OK.value(), "게시글 삭제 성공"));


        } else {
            return null;
        }
    }
}

