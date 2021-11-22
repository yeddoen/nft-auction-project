package project.spring.nft;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import project.spring.nft.domain.MemberVO;
import project.spring.nft.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
@WebAppConfiguration
public class MemberDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOTest.class);

	@Autowired
	private MemberDAO dao;

	@Test
	public void testDAO() {
		// testInsert();
		// login();
		// testSelectByMemberId();
		// testUpdatePassword();
		// testUpdateById();
		// testDeleteById();
	}

	private void login() {
		MemberVO vo = dao.selectLogin("admin");
		// MemberVO vo=dao.selectByMemberNo(1);
		logger.info("login test : " + vo.toString());
	} // 성공

	private void testInsert() {
		MemberVO vo = new MemberVO(0, "admin", "admin1234", "관리자", "관리자", "010-1234-5678", "test@test.com",
				"member_test");
		int result = dao.insertMember(vo);
		if (result == 1) {
			logger.info("insert 성공");
		} else {
			logger.info("insert 실패");
		}
	} // 성공

	private void testSelectByMemberId() {
		MemberVO vo = dao.selectByMemberId("test");
		logger.info("회원 정보 : " + vo.toString());
	} // 성공

	private void testUpdatePassword() {
		int result = dao.updateMemberPassword("chatestt", "passwordchanged");
		if (result == 1) {
			logger.info("updatePassword 성공");
		} else {
			logger.info("updatePassword 성공");
		}
	} // 성공

	private void testUpdateById() {
		MemberVO vo = new MemberVO(0, "admin", "", "", "hey", "010-5435-5421", "test@testt.com", "");
		int result = dao.updateMemberInfo(vo);
		if (result == 1) {
			logger.info("update 성공");
		} else {
			logger.info(result + "update 실패");
		}
	} // 성공

	private void testDeleteById() {
		MemberVO vo = new MemberVO();
		int result = dao.deleteMember("chatest", "test");
		if (result == 1) {
			logger.info("delete 성공");
		} else {
			logger.info("delete 실패");
		}
	} // 성공

} // end class
