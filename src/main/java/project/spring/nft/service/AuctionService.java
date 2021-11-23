package project.spring.nft.service;

import java.util.List;

import project.spring.nft.domain.ArtAuctionVO;
import project.spring.nft.domain.AuctionVO;

public interface AuctionService {
	//입찰하기
	int create(AuctionVO vo);
	//입찰자 닉네임 적용하기
	int updateNickname(String memberNickname);
	//작품의 입찰내역 조회
	List<AuctionVO> readAll(int artNo);
	//경매 낙찰자 등록
	int updateWinner(int artNo, int maxMoney);
	//낙찰자 조회
	String selectWinner(int artNo);
	// 실시간 목록 조회
	List<ArtAuctionVO> read();
}
