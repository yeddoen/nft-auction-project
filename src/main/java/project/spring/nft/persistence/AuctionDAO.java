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
	//작품 삭제 시 경매기록 삭제
	int deleteArtNo(int artNo);
}
