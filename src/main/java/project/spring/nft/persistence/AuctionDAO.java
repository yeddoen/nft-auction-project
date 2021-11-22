package project.spring.nft.persistence;

import java.util.List;

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
	
	// 실시간 작품 거래 조회 경매 번호순
	List<AuctionVO> select();
}
