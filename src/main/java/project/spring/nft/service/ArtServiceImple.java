package project.spring.nft.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.nft.domain.ArtVO;
import project.spring.nft.persistence.ArtDAO;

@Service
public class ArtServiceImple implements ArtService {
	private static final Logger logger=
			LoggerFactory.getLogger(ArtServiceImple.class);
	
	@Autowired
	private ArtDAO dao;
	
	@Override
	public int createArt(ArtVO vo) {
		logger.info("createArt 호출 : vo = "+vo.toString());
		return dao.insertArt(vo);
	}
	
	@Override
	public int updateNickname(String memberId) {
		logger.info("updateNickname() 호출 : memberId = "+memberId);
		return dao.updateNickName(memberId);
	}

	@Override
	public List<ArtVO> readCurrentArt() {
		logger.info("readCurrentArt() 호출");
		return dao.selectCurrentArt();
	}

	@Override
	public List<ArtVO> readWishArt() {
		logger.info("readWishArt() 호출");
		return dao.selectWishArt();
	}

	@Override
	public List<ArtVO> readViewArt() {
		logger.info("readViewArt() 호출");
		return dao.selectViewArt();
	}

	@Override
	public List<ArtVO> readArtName(String keyword) {
		logger.info("readArtName() 호출 : keyword = "+keyword);
		return dao.selectArtName(keyword);
	}

	@Override
	public List<ArtVO> readMemberNo(String keyword) {
		logger.info("readMemberNo() : keyword = "+keyword);
		return dao.selectMemberNo(keyword);
	}

}
