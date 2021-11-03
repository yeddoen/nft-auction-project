package project.spring.nft.service;

import java.util.List;

import project.spring.nft.domain.ArtVO;

//TODO 기능 추가 필요
public interface ArtService {
	//작품 등록
	int createArt(ArtVO vo);
	//닉네임 적용
	int updateNickname(String memberId);
	//최신순 정렬 전체 작품 조회
	List<ArtVO> readCurrentArt();
	//찜하기순 정렬 전체 작품 조회
	List<ArtVO> readWishArt();
	//조회수순 정렬  전체 작품 조회
	List<ArtVO> readViewArt();
	//작품명 검색
	List<ArtVO> readArtName(String keyword);
	//작가이름 검색
	List<ArtVO> readMemberNo(String keyword);
}
