package project.spring.nft.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.nft.domain.ArtAuctionVO;
import project.spring.nft.domain.AuctionVO;
import project.spring.nft.persistence.AuctionDAO;

@Service
public class AuctionServiceImple implements AuctionService {
	private static final Logger logger=
			LoggerFactory.getLogger(AuctionServiceImple.class);
	
	@Autowired
	private AuctionDAO dao;
	
	@Override
	public int create(AuctionVO vo) {
		logger.info("create() 호출");
		return dao.insert(vo);
	}

	@Override
	public int updateNickname(String memberNickname) {
		logger.info("updateNickname() 호출");
		return dao.updateNickname(memberNickname);
	}

	@Override
	public List<AuctionVO> readAll(int artNo) {
		logger.info("readAll() 호출");
		return dao.selectAll(artNo);
	}
	
	@Override
	public int updateWinner(int artNo, int maxMoney) {
		logger.info("updateWinner() 호출");
		return dao.updateWinner(artNo, maxMoney);
	}
	
	@Override

	public AuctionVO selectWinner(int artNo) {
		logger.info("selectWinner() 호출");
		return dao.selectWinner(artNo);
	}

	@Override
	public List<ArtAuctionVO> read() {
		logger.info("read() 호출");
		return dao.select();
	}
}
