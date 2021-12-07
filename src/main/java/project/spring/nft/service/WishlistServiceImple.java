package project.spring.nft.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.nft.domain.WishlistVO;
import project.spring.nft.persistence.WishlistDAO;

@Service
public class WishlistServiceImple implements WishlistService {
	private static final Logger logger =
			LoggerFactory.getLogger(WishlistServiceImple.class);
	
	@Autowired
	private WishlistDAO dao;
	
	@Override
	public int create(WishlistVO vo) {
		logger.info("create() 호출 : vo = " + vo.toString());
		return dao.insertWishlist(vo);
	}

	@Override
	public List<WishlistVO> readByMemberId(String memberId) {
		logger.info("readByMemberId() 호출 : memberId = " + memberId);
		return dao.selectWishlist(memberId);
	}

	@Override
	public int delete(String memberId, String artName) {
		logger.info("delete() 호출 : memberId = " + memberId);
		return dao.deleteWishlist(memberId, artName);
	}

	@Override
	public int readCount(String memberId, String artName) {
		logger.info("readWishlist() 호출 : memberId = " + memberId + " artName = " + artName);
		return dao.selectCount(memberId, artName);
	}

} // end WishlistServiceImple class
