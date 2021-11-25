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

import project.spring.nft.domain.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"}) 
@WebAppConfiguration
public class SqlSessionTest {
	private static final Logger logger=
			LoggerFactory.getLogger(SqlSessionTest.class);
	
	private static final String NAMESPACE="project.spring.nft.MemberMapper";
	
	@Autowired
	private SqlSession sqlSession;
	//DAOImple에서 사용하던 db쿼리 메소드를 축약해서 함수로 가지고있음.
	
	@Test
	public void testInsert() {
		MemberVO vo=new MemberVO(0, "idid", "1234", "테스터", "텟", "010-1111-2222", "test@test.ete", "ididiuid");
		//가고싶은 쿼리 태그를 namespace 뒤에 넣어 매칭해준다
		int result=sqlSession.insert(NAMESPACE+".insert", vo);
		//insert(key, value)
		//.insert : board-mapper.xml의 id값
		logger.info(result+"행 삽입");
	}
	
}
