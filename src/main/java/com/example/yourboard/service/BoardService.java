package com.example.yourboard.service;

import com.example.yourboard.dto.BoardRequestDto;
import com.example.yourboard.dto.BoardResponseDto;
import com.example.yourboard.entity.Board;
import com.example.yourboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardService {




    private final BoardRepository boardRepository;


    //게시글 저장
    public Board writeBoard(BoardRequestDto boardDto) {
        Board board = new Board(boardDto); //생성자가 있어야 함
        boardRepository.save(board);
        return board;

    }


    //게시글 확인
    @Transactional(readOnly = true)
    public List<Board> getBoard()  {
        return boardRepository.findAllByIdAndModifiedAtDesc(); //findAll을 써도 되는가
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
    public BoardResponseDto updateBoard(Long id, BoardRequestDto boardRequestDto) {
        Board board = checkBoard(id);
        if (board.getPassword().equals(boardRequestDto.getPassword())) {
            board.update(boardRequestDto);
        } else {
            System.out.println("비밀번호가 일치하지 않는다옹"); //sout이 과연 보내질 것인가?
        }

        return new BoardResponseDto(board);
    }


    private Board checkBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(
                () -> new NullPointerException("일치하는 게시글 없또")
        );
    }

    @Transactional
    public Long deleteBoard(Long id) {
        boardRepository.deleteById(id);
        return id;
    }
}




//    findAllByOrderByModifiedAtDesc()
// 드디어 됐다 사실 뭐가 문제였는지 모르겠다. ㅠㅠ
// 첫번째 시도. 강의에서 쓰는 함수 (findById)에 계속 빨간줄이 뜬다.
// 두번째 시도. 패스워드를 불러서 비교하려고 했으나 praivate여서 실패
// 세번째 시도. 그럼 dto 상태는 호출할 수 있지 않나? 실패 request = BoardRequestDto boardRequestDto1;
// 네번째 시도. 강의에서 쓰는 함수 사용. 근데 다시 하니까 된다. 어째서?
// 같은 코드를 치는데 어떤건 연결이 되고 안괴고 한다. 그 이유는?
// 다섯번 째 시도 , 게터 사용 해서 비밀 번호 가져 오기 (게터로 어떻게 가져오는가)
//세터를 함부를 사용하지 말아라
//메서드를 통해서 접근해야한다. 그냥 게터와 .을 써서 가져오는 것을 차이는? 캡슐화. 개념을 아는게 중요하다.
// 람다식을 if문으로 하는 방법 //
//        equals(boardRequestDto.getPassword())
//        쓰면 안된다.예상하지 못한 에러가 나면?그것에 대한 대비가 안된다.널포인터리셉션. "String"
//        equls(board.getPasswqrd) null equels는 없다.null safe equals.





