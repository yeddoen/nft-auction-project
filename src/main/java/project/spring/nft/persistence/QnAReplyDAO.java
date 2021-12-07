package project.spring.nft.persistence;

import java.util.List;

import project.spring.nft.domain.QnAReplyVO;

public interface QnAReplyDAO {
	int insert(QnAReplyVO vo);
	List<QnAReplyVO> select(int qnaboardNo);
	List<QnAReplyVO> selectReply(int qnaboardNo, int replyParentNo);
	int update(QnAReplyVO vo);
	int delete(int replyNo);
	int deleteMemberId(String memberId);
}
