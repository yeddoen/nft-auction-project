package project.spring.nft.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.nft.domain.ArtReplyVO;


@Repository
public class ArtReplyDAOImple implements ArtReplyDAO {
	private static final Logger logger =
			LoggerFactory.getLogger(ArtReplyDAOImple.class);
	private static final String NAMESPACE =
			"project.spring.nft.ArtReplyMapper";
	
	@Autowired
	private SqlSession sqlsession;

	@Override
	public int insert(ArtReplyVO vo) {
		logger.info("insert() 호출 : vo = "+vo.toString());
		return sqlsession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<ArtReplyVO> select(int artNo) {
		logger.info("select() 호출");	
		return sqlsession.selectList(NAMESPACE + ".select_all_by_art_no", artNo);
	}
	
	@Override
	public List<ArtReplyVO> selectReply(int artNo, int artReplyParentNo) {
		logger.info("selectReply() 호출");
		Map<String, Integer> args = new HashMap<String, Integer>();
		args.put("artNo", artNo);
		args.put("artReplyParentNo", artReplyParentNo);
		return sqlsession.selectList(NAMESPACE + ".select_reply_by_parent_no", args);
	}

	@Override
	public int update(ArtReplyVO vo) {
		logger.info("update() 호출 : vo = "+vo.toString());
		return sqlsession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(int artReplyNo) {
		logger.info("delete() 호출");
		return sqlsession.delete(NAMESPACE + ".delete", artReplyNo);
	}	
	
	@Override
	public int deleteArtNo(int artNo) {
		logger.info("deleteArtNo() 호출 : artNo = "+artNo);
		return sqlsession.delete(NAMESPACE+".delete_art_no", artNo);
	}
	
	@Override
	public int deleteMemberId(String memberId) {
		logger.info("deleteMemberId() 호출");
		return sqlsession.delete(NAMESPACE+".delete_id", memberId);
	}
}
