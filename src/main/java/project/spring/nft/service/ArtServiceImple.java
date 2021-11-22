package project.spring.nft.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.nft.domain.ArtVO;
import project.spring.nft.pageutil.PageCriteria;
import project.spring.nft.persistence.ArtDAO;
import project.spring.nft.persistence.AuctionDAO;

@Service
public class ArtServiceImple implements ArtService {
	private static final Logger logger=
			LoggerFactory.getLogger(ArtServiceImple.class);
	
	@Autowired
	private ArtDAO artDAO;
	@Autowired
	private AuctionDAO auctionDAO;
	
	@Override
	public int createArt(ArtVO vo) {
		logger.info("createArt 호출 : vo = "+vo.toString());
		return artDAO.insertArt(vo);
	}
	
	@Override
	public int updateNickname(String memberId) {
		logger.info("updateNickname() 호출 : memberId = "+memberId);
		return artDAO.updateNickName(memberId);
	}

	@Override
	public List<ArtVO> readCurrentArt(PageCriteria criteria) {
		logger.info("readCurrentArt() 호출");
		return artDAO.selectCurrentArt(criteria);
	}

	@Override
	public List<ArtVO> readWishArt(PageCriteria criteria) {
		logger.info("readWishArt() 호출");
		return artDAO.selectWishArt(criteria);
	}

	@Override
	public List<ArtVO> readViewArt(PageCriteria criteria) {
		logger.info("readViewArt() 호출");
		return artDAO.selectViewArt(criteria);
	}
	
	@Override
	public int getTotalNumsOfRecords() {
		logger.info("getTotalNumsOfRecords() 호출");
		return artDAO.getTotalNumsOfRecords();
	}
	
	@Override
	public int getArtNameNumsOfRecords(String keyword) {
		logger.info("getArtNameNumsOfRecords() 호출");
		return artDAO.getArtNameNumsOfRecords(keyword);
	}
	
	@Override
	public int getNicknameNumsOfRecords(String keyword) {
		logger.info("getNicknameNumsOfRecords() 호출");
		return artDAO.getNicknameNumsOfRecords(keyword);
	}

	@Override
	public List<ArtVO> readArtName(PageCriteria criteria, String keyword) {
		logger.info("readArtName() 호출 : keyword = "+keyword+", criteria = "+criteria);
		return artDAO.selectArtName(criteria, keyword);
	}

	@Override
	public List<ArtVO> readMemberNickname(PageCriteria criteria, String keyword) {
		logger.info("readMemberNo() : keyword = "+keyword);
		return artDAO.selectMemberNickname(criteria, keyword);
	}
	
	@Override
	public Map<String, Object> readArtNo(int artNo) {
		logger.info("readArtNo() 호출");
		Map<String, Object> readMap=new HashMap<String, Object>();
		ArtVO vo=artDAO.selectArtNo(artNo);
		logger.info("ArtVO 조회");
		readMap.put("vo", vo);

		int maxMoney = 0;
		try {
			maxMoney=auctionDAO.selectMaxBid(artNo);
			logger.info("maxMoney 조회");			
			readMap.put("maxMoney", maxMoney);
		} catch (NullPointerException e) {
			logger.info(artNo+"의 auction 값 없음");
		}
		return readMap;
	}
	
	@Override
	public int updateView(int artViewCount) {
	
		return 0;
	}

	@Override
	public ArtVO readArtno(int artNo) {
		logger.info("readArtNo() 호출");
		return artDAO.selectArtNo(artNo);
	}

}
