package project.spring.nft.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.nft.domain.WishlistVO;

@Repository
public class WishlistDAOImple implements WishlistDAO {
	private static final Logger logger =
			LoggerFactory.getLogger(WishlistDAOImple.class);
	private static final String NAMESPACE =
			"project.spring.nft.WishlistMapper";

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertWishlist(WishlistVO vo) {
		logger.info("insert() 호출");
		System.out.println(vo);
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<WishlistVO> selectWishlist(String memberId) {
		logger.info("select() 호출");
		return sqlSession.selectList(NAMESPACE + ".select_all", memberId);
	}

	@Override
	public int deleteWishlist(int wishNo) {
		logger.info("delete() 호출 : bno = " + wishNo);
		return sqlSession.delete(NAMESPACE + ".delete", wishNo);
	}


} // end WishlistDAOImple class
