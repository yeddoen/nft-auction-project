package project.spring.nft;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import project.spring.nft.domain.WishlistVO;
import project.spring.nft.persistence.WishlistDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
@WebAppConfiguration
public class WishlistDAOTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(WishlistDAOTest.class);
	
	@Autowired
	private WishlistDAO dao;
	
	@Test
	public void testDAO() {
		// testInsert(); // 성공
		// testSelectAll(); // 성공
		// testDelete(); // 성공
		testSelectCount();
		
	}


	private void testInsert() {
		WishlistVO vo = new WishlistVO(0, "test", "testartName", 10000, "testfileName", 1);
		int result = dao.insertWishlist(vo);
		if (result == 1) {
			LOGGER.info("insert 성공");
		} else {
			LOGGER.info("insert 실패");
		}
		
	} // 성공

	private void testSelectAll() {
		String memberId = "test2";
		List<WishlistVO> list = dao.selectWishlist(memberId);
		for (WishlistVO vo : list) {
			LOGGER.info(vo.toString());
		}
	} // 성공

	private void testDelete() {
		WishlistVO vo = new WishlistVO();
		int wishNo = 1;
		int result = dao.deleteWishlist("test", "고양이");
		if (result == 1) {
			LOGGER.info("delete 성공");
		} else {
			LOGGER.info("delete 실패");
		}
		
	} // 성공
	

	private void testSelectCount() {
		String memberId = "test";
		String artName = "끼욧";
		
		int result = dao.selectCount(memberId, artName);
		if (result == 1) {
			LOGGER.info("SelectCount 성공");
		} else {
			LOGGER.info("SelectCount 실패");
		}
	} // 성공
	
	
} // end WishlistDAOTest
