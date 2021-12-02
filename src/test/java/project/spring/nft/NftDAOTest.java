package project.spring.nft;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import project.spring.nft.domain.NftVO;
import project.spring.nft.persistence.NftDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
@WebAppConfiguration
public class NftDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(NftDAOTest.class);

	@Autowired
	private NftDAO dao;

	@Test
	public void testDAO() throws ParseException {
		// testInsert(); // 성공
		// testSelect(); // 성공
		// testDelete(); // 성공
		// testUpdate();

	}

	private void testInsert() throws ParseException {
		Timestamp createdAt = new Timestamp(10100);
		Timestamp updatedAt = new Timestamp(100003);
		NftVO vo = new NftVO(0, "testTokenId", "testAlias", "t", "e", "s", "t", createdAt, updatedAt);

		int result = dao.insertNft(vo);
		if (result == 1) {
			logger.info("insert 성공");
		} else {
			logger.info("insert 실패");
		}
	}

	private void testSelect() {
		String nftContractAlias = "testAlias";
		String nftTokenId = "testTokenId";

		NftVO result = dao.selectNft(nftContractAlias, nftTokenId);
		System.out.println("select : " + result);

	}

	private void testDelete() {
		String nftContractAlias = "testAlias";
		String nftTokenId = "testTokenId";

		int result = dao.deleteNft(nftContractAlias, nftTokenId);
		if (result == 1) {
			logger.info("delete 성공");
		} else {
			logger.info("delete 실패");
		}

	}

} // end class
