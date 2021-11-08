package project.spring.nft.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.nft.domain.ArtVO;
import project.spring.nft.pageutil.PageCriteria;
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
	public List<ArtVO> readCurrentArt(PageCriteria criteria) {
		logger.info("readCurrentArt() 호출");
		return dao.selectCurrentArt(criteria);
	}

	@Override
	public List<ArtVO> readWishArt(PageCriteria criteria) {
		logger.info("readWishArt() 호출");
		return dao.selectWishArt(criteria);
	}

	@Override
	public List<ArtVO> readViewArt(PageCriteria criteria) {
		logger.info("readViewArt() 호출");
		return dao.selectViewArt(criteria);
	}
	
	@Override
	public int getTotalNumsOfRecords() {
		logger.info("getTotalNumsOfRecords() 호출");
		return dao.getTotalNumsOfRecords();
	}
	
	@Override
	public int getArtNameNumsOfRecords(String keyword) {
		logger.info("getArtNameNumsOfRecords() 호출");
		return dao.getArtNameNumsOfRecords(keyword);
	}
	
	@Override
	public int getNicknameNumsOfRecords(String keyword) {
		logger.info("getNicknameNumsOfRecords() 호출");
		return dao.getNicknameNumsOfRecords(keyword);
	}

	@Override
	public List<ArtVO> readArtName(PageCriteria criteria, String keyword) {
		logger.info("readArtName() 호출 : keyword = "+keyword+", criteria = "+criteria);
		return dao.selectArtName(criteria, keyword);
	}

	@Override
	public List<ArtVO> readMemberNickname(PageCriteria criteria, String keyword) {
		logger.info("readMemberNo() : keyword = "+keyword);
		return dao.selectMemberNickname(criteria, keyword);
	}

}
