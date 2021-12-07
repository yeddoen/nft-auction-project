package project.spring.nft.persistence;

import java.util.List;

import project.spring.nft.domain.ArtReplyVO;

public interface ArtReplyDAO {
	int insert(ArtReplyVO vo);
	List<ArtReplyVO> select(int artNo);
	List<ArtReplyVO> selectReply(int artNo, int artReplyParentNo);
	int update(ArtReplyVO vo);
	int delete(int artReplyNo);
	int deleteArtNo(int artNo);
	int deleteMemberId(String memberId);
}
