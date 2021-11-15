package project.spring.nft.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.nft.domain.AuctionVO;

@Repository
public class AuctionDAOImple implements AuctionDAO {
	private static final Logger logger=
			LoggerFactory.getLogger(AuctionDAOImple.class);
	
	private static final String NAMESPACE="project.spring.nft.AuctionMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(AuctionVO vo) {
		logger.info("insert() 호출 : vo = "+vo.toString());
		return sqlSession.insert(NAMESPACE+".insert", vo);
	}

	@Override
	public int updateNickname(String memberNickname) {
		logger.info("updateNickname() 호출 : memberNickname = "+memberNickname);
		return sqlSession.update(NAMESPACE+".update_nickname",memberNickname);
	}

	@Override
	public List<AuctionVO> selectAll(int artNo) {
		logger.info("selectAll() 호출");
		return sqlSession.selectList(NAMESPACE+".select_all_by_art_no", artNo);
	}
	
	@Override
	public int selectMaxBid(int artNo) {
		logger.info("selectMaxBid() 호출");
		return sqlSession.selectOne(NAMESPACE+".select_max_money", artNo);
	}

	
	@Override
	public int deleteArtNo(int artNo) {
		logger.info("deleteArtNo() 호출 : artNo = "+artNo);
		return sqlSession.delete(NAMESPACE+".delete_art_no", artNo);
	}
}
