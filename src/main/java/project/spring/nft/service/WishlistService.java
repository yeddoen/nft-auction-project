package project.spring.nft.service;

import java.util.List;

import project.spring.nft.domain.WishlistVO;

public interface WishlistService {
	int create(WishlistVO vo);
	List<WishlistVO> readByMemberId(String memberId);
	int delete(int wishNo);
	
	
	
} // end WishlistService class
