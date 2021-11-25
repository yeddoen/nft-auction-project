package project.spring.nft.service;

import java.util.List;

import project.spring.nft.domain.ArtReplyVO;


public interface ArtReplyService {
	int create(ArtReplyVO vo) throws Exception;
	List<ArtReplyVO> read(int artNo);
	int update(ArtReplyVO vo);
	int delete(int artReplyNo, int artNo) throws Exception;
}
