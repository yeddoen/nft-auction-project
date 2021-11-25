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

import project.spring.nft.domain.QnABoardVO;
import project.spring.nft.persistence.QnABoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"}) 
@WebAppConfiguration
public class QnABoardDAOTest {
	private static final Logger logger=
			LoggerFactory.getLogger(QnABoardDAOTest.class);
	
	@Autowired
	private QnABoardDAO dao;
	
	@Test
	public void testDAO() {
		//testInsert();
		//testSelectAll();
		//testUpdate();
		testDelete();
	}

	private void testDelete() {
		int result = dao.delete(1);
		logger.info(result + "행 삭제");		
	}

	private void testUpdate() {
		QnABoardVO vo = new QnABoardVO(1, "변경", "변경", "변경", "변경", null);
		int result = dao.update(vo);
		if(result == 1) {
			logger.info("update 성공");
		} else {
			logger.info("update 실패");
		}
	}

	private void testSelectAll() {
		List<QnABoardVO> list = dao.select();
		for(QnABoardVO vo : list) {
			logger.info(vo.toString());
		}
	}

	private void testInsert() {
		QnABoardVO vo = new QnABoardVO(0, "test", "test", "test", "test", null);
		int result = dao.insert(vo);
		if(result == 1) {
			logger.info("insert 성공");
		} else {
			logger.info("insert 실패");
		}
	}
	
	

}
