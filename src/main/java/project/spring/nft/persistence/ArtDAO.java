package project.spring.nft.persistence;

import java.util.List;

import project.spring.nft.domain.ArtVO;

//TODO 기능추가 필요
public interface ArtDAO {
	//작품 등록
	int insertArt(ArtVO vo);
	//회원닉네임 업데이트
	int updateNickName(String memberId);
	//최신순 정렬 전체 작품 조회
	List<ArtVO> selectCurrentArt();
	//찜하기순 정렬 전체 작품 조회
	List<ArtVO> selectWishArt();
	//조회수순 정렬  전체 작품 조회
	List<ArtVO> selectViewArt();
	//작품명 검색
	List<ArtVO> selectArtName(String keyword);
	//작가이름 검색
	List<ArtVO> selectMemberNo(String keyword);
	//작품 데이터(작품명, 작품설명) 수정
	//작품(게시된) 삭제
	
}
