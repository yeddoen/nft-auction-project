package project.spring.nft.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			"project.spring.nft.QnAReplyMapper";
	
	@Autowired
	private SqlSession sqlsession;

	@Override
	public int insert(QnAReplyVO vo) {
		logger.info("insert() 호출");
		return sqlsession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<QnAReplyVO> select(int qnaboardNo) {
		logger.info("select() 호출");	
		return sqlsession.selectList(NAMESPACE + ".select_all_by_qnaboard_no", qnaboardNo);
	}
	
	@Override
	public List<QnAReplyVO> selectReply(int qnaboardNo, int replyParentNo) {
		logger.info("selectReply() 호출");
		Map<String, Integer> args = new HashMap<String, Integer>();
		args.put("qnaboardNo", qnaboardNo);
		args.put("replyParentNo", replyParentNo);
		return sqlsession.selectList(NAMESPACE + ".select_reply_by_parent_no", args);
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
