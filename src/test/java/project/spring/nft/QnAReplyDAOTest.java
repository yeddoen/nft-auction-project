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

import project.spring.nft.domain.QnAReplyVO;
import project.spring.nft.persistence.QnAReplyDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"}) 
@WebAppConfiguration
public class QnAReplyDAOTest {
	private static final Logger logger=
			LoggerFactory.getLogger(QnAReplyDAOTest.class);
	
	@Autowired
	private QnAReplyDAO dao;
	
	@Test
	public void testDAO() {
		//testInsert();
		//testSelectByQnABoardNo();
		//testUpdate();
		//testDelete();
	}
	
	private void testDelete() {
		int result = dao.delete(1);
		logger.info(result + "행 삭제");		
	}

	private void testUpdate() {
		QnAReplyVO vo = new QnAReplyVO(0, 1, 1, 1, "test", "test", "test", null);
		int result = dao.update(vo);
		if(result == 1) {
			logger.info("update 성공");
		} else {
			logger.info("update 실패");
		}
	}

	private void testSelectByQnABoardNo() {
		List<QnAReplyVO> list = dao.select(1);
		for(QnAReplyVO vo : list) {
			logger.info(vo.toString());
		}
	}

	private void testInsert() {
		QnAReplyVO vo = new QnAReplyVO(0, 1, 0, 0, "test", "test", "test", null);
		int result = dao.insert(vo);
		if(result == 1) {
			logger.info("insert 성공");
		} else {
			logger.info("insert 실패");
		}
	}
	

}
