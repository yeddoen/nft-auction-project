package project.spring.nft.persistence;

import java.util.List;

import project.spring.nft.domain.WishlistVO;

public interface WishlistDAO {
	// 위시리스트 찜버튼을 누르면 db로 들어가 등록
	int insertWishlist(WishlistVO vo);
	
	/*
	 * // 위시리스트에서 사용자의 아이디로(세션) 조회하기 WishlistVO selectWishlistByMemberId(String
	 * memberId);
	 */
	
	// 전체 작품들을 조회
	List<WishlistVO> selectWishlist(String memberId);
	
	// 위시리스트 페이지에서 클릭을 선택해서 인덱스번호를 주고 삭제
	int deleteWishlist(int wishNo);
	
	// 업데이트는 없음!!
	
	
} // end WishlistDAO
