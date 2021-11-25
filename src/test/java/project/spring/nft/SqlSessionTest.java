package project.spring.nft;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import project.spring.nft.domain.QnABoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class SqlSessionTest {
	private static final Logger LOGGER = 
	         LoggerFactory.getLogger(SqlSessionTest.class);
	private static final String NAMESPACE = 
			"project.spring.nft.QnABoardMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void testInsert() {
		QnABoardVO vo = new QnABoardVO(0, "hello", "hello", "choi", "choi", null);
		int result = sqlSession.insert(NAMESPACE + ".insert", vo);
		// .insert : mapper.xml의 id값
		LOGGER.info(result + "행 삽입");
	}
}
