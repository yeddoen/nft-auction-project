package project.spring.nft.persistence;

import java.util.List;

import project.spring.nft.domain.QnAReplyVO;

public interface QnAReplyDAO {
	int insert(QnAReplyVO vo);
	List<QnAReplyVO> select(int replybno);
	int update(QnAReplyVO vo);
	int delete(int replyNo);
	
}
