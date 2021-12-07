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
			"project.spring.nft.QnABoardMapper";
	
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
	public QnABoardVO select(int qnaboardNo) {
		logger.info("select() 호출 : bno = " + qnaboardNo);
		return sqlSession.selectOne(NAMESPACE + ".select_by_qnaboard_no", qnaboardNo);
	}
	@Override
	public int update(QnABoardVO vo) {
		logger.info("update() 호출 : vo =" + vo.toString());
		return sqlSession.update(NAMESPACE + ".update", vo);
	}
	@Override
	public int delete(int qnaboardNo) {
		logger.info("delete() 호출 : bno = " + qnaboardNo);
		return sqlSession.delete(NAMESPACE + ".delete", qnaboardNo);
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
	public int updateReplyCount(int amount, int qnaboardNo) {
		logger.info("updateReplyCount 호출");
		Map<String, Integer> args = new HashMap<String, Integer>();
		args.put("amount", amount);
		args.put("qnaboardNo", qnaboardNo);
		return sqlSession.update(NAMESPACE + ".update_reply_count", args);
	}
	@Override
	public int selectByMemberId(String memberId) {
		logger.info("selectByMemberId() 호출");
		return sqlSession.selectOne(NAMESPACE + ".select_by_member_id", memberId);
	}
	@Override
	public List<QnABoardVO> selectListByMemberId(PageCriteria c, String memberId) {
		logger.info("selectListByMemberId() 호출");
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("start", c.getStart());
		args.put("end", c.getEnd());
		args.put("memberId", memberId);
		return sqlSession.selectList(NAMESPACE + ".select_list_by_member_id", args);
	}
	
	@Override
	public int deleteMemberId(String memberId) {
		logger.info("deleteMemberId 호출");
		return sqlSession.delete(NAMESPACE+".delete_id", memberId);
	}
	
}
