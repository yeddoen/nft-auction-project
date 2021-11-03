package project.spring.nft.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.nft.domain.QnABoardVO;
import project.spring.nft.pageutil.PageCriteria;


@Repository
public class QnABoardDAOImple implements QnABoardDAO {
	private static final Logger logger =
			LoggerFactory.getLogger(QnABoardDAOImple.class);
	private static final String NAMESPACE =
			"project.nft.ex01.QnABoardMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(QnABoardVO vo) {
		logger.info("insert() 호출");
		System.out.println(vo);
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}
	@Override
	public List<QnABoardVO> select() {
		logger.info("select() 호출");
		return sqlSession.selectList(NAMESPACE + ".select_all");
	}
	@Override
	public QnABoardVO select(int bno) {
		logger.info("select() 호출 : bno = " + bno);
		return sqlSession.selectOne(NAMESPACE + ".select_by_bno", bno);
	}
	@Override
	public int update(QnABoardVO vo) {
		logger.info("update() 호출 : vo =" + vo.toString());
		return sqlSession.update(NAMESPACE + ".update", vo);
	}
	@Override
	public int delete(int bno) {
		logger.info("delete() 호출 : bno = " + bno);
		return sqlSession.delete(NAMESPACE + ".delete", bno);
	}

	@Override
	public int getTotalNumsOfRecords() {
		logger.info("getTotalNumsOfRecords() 호출");
		return sqlSession.selectOne(NAMESPACE + ".total_count");
	}
	@Override
	public List<QnABoardVO> select(PageCriteria c) {
		logger.info("select(PageCriteria) 호출");
		return sqlSession.selectList(NAMESPACE + ".paging", c);
	}
	@Override
	public int updateReplyCount(int amount, int bno) {
		logger.info("updateReplyCount 호출");
		Map<String, Integer> args = new HashMap<String, Integer>();
		args.put("amount", amount);
		args.put("bno", bno);
		return sqlSession.update(NAMESPACE + ".update_reply_count", args);
	}

}
