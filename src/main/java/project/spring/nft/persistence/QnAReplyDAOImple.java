package project.spring.nft.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.nft.domain.QnAReplyVO;

@Repository
public class QnAReplyDAOImple implements QnAReplyDAO {
	private static final Logger logger =
			LoggerFactory.getLogger(QnAReplyDAOImple.class);
	private static final String NAMESPACE =
			"project.nft.ex01.QnAReplyMapper";
	
	@Autowired
	private SqlSession sqlsession;

	@Override
	public int insert(QnAReplyVO vo) {
		logger.info("insert() 호출");
		return sqlsession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<QnAReplyVO> select(int replybno) {
		logger.info("select() 호출");
		return sqlsession.selectList(NAMESPACE + ".select_all_by_reply_bno", replybno);
	}

	@Override
	public int update(QnAReplyVO vo) {
		logger.info("update() 호출");
		return sqlsession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(int replyNo) {
		logger.info("delete() 호출");
		return sqlsession.delete(NAMESPACE + ".delete", replyNo);
	}




}