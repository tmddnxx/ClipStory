package dao;

import com.example.movie.model.dao.MemberDAO;
import com.example.movie.model.dto.MemberDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Member;

@Log4j2
public class memberDAOTest {
    private MemberDAO memberDAO;

    @BeforeEach
    public void ready(){
        memberDAO = new MemberDAO();
    }
    @Test
    public void getWithPasswordTest() throws Exception {
        String memberId = "test";
        String passwd = "1111";
        MemberDTO MemberDTO = memberDAO.getWithPassword(memberId, passwd);
        log.info(MemberDTO.toString());
        log.info("-----------------");
    }
    @Test
    public void addMemberTest() throws Exception {
        MemberDTO memberDTO = MemberDTO.builder()
                .memberId("test2")
                .passwd("1111")
                .name("testName")
                .nickName("testNickName")
                .build();
        memberDAO.addMember(memberDTO);
    }
    @Test
    public void modifyMemberTest() throws Exception {
        MemberDTO memberDTO = MemberDTO.builder()
                .memberId("test2")
                .passwd("1111")
                .name("testName-mod")
                .nickName("testNickName-mod")
                .build();
        memberDAO.modifyMember(memberDTO);
    }
}
