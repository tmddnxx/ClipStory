package dao;

import com.example.movie.model.dao.BoardDAO;
import com.example.movie.model.dto.BoardDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

@Log4j2
public class boardDAOtest {
    private BoardDAO boardDAO;
    @BeforeEach
    public void ready() { boardDAO = new BoardDAO();}

    @Test
    public void testInsertBoard() throws Exception{ // 게시물 추가
        BoardDTO boardDTO = new BoardDTO();
        for(int i=1; i<40; i++){
            boardDTO = BoardDTO.builder()
                    .title("TestTitle [" + i + "]")
                    .content("TestContent[" + i + "]")
                    .nickName("TestNickName[" + i + "]")
                    .memberId("TestAdmin[" + i + "]").build();
            boardDAO.insertBoard(boardDTO);
        }

    }

//    @Test
//    public void testSelectAll() throws Exception { // 전체목록
//        List<BoardDTO> boardList = boardDAO.selectAll();
//        for(BoardDTO boardDTO : boardList){
//            log.info(boardDTO);
//        }
//    }

    @Test
    public void testSelectOne() throws Exception { // 개별 선택
        log.info(boardDAO.selectOne(1));
    }

    @Test
    public void testDeleteBoard() throws Exception { // 게시물 삭제
        boardDAO.deleteBoard(2);
    }

    @Test
    public void testPlusHit() throws Exception{
        boardDAO.plusHit(4);
    }

    @Test
    public void testModifyBoard() throws Exception {
        BoardDTO boardDTO = BoardDTO.builder()
                .title("ModifyTitle[1]")
                .content("ModifyContent[1]")
                .contentNo(12)
                .build();
        boardDAO.modifyBoard(boardDTO);
    }
}
