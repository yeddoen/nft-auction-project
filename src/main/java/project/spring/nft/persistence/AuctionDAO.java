package project.spring.nft.persistence;

import java.util.List;

import project.spring.nft.domain.ArtAuctionVO;
import project.spring.nft.domain.AuctionVO;

public interface AuctionDAO {
	//입찰하기
	int insert(AuctionVO vo);
	//입찰자 닉네임 적용하기
	int updateNickname(String memberNickname);
	//작품의 입찰내역 조회
	List<AuctionVO> selectAll(int artNo);
	//최고 입찰가 조회
	int selectMaxBid(int artNo);
	//작품 삭제 시 경매기록 삭제
	int deleteArtNo(int artNo);
	//낙찰자 등록
	int updateWinner(int artNo, int maxMoney);
	//낙찰자 조회
	String selectWinner(int artNo);
	// 실시간 작품 거래 조회 경매 번호순
	List<ArtAuctionVO> select();
}
