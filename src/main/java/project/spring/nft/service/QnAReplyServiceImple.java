package project.spring.nft.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.spring.nft.domain.QnAReplyVO;
import project.spring.nft.persistence.QnABoardDAO;
import project.spring.nft.persistence.QnAReplyDAO;

@Service
public class QnAReplyServiceImple implements QnAReplyService {

	private static final Logger logger = 
			LoggerFactory.getLogger(QnAReplyServiceImple.class);

	@Autowired
	private QnAReplyDAO replyDAO;

	@Autowired
	private QnABoardDAO boardDAO;

	@Transactional
	@Override
	public int create(QnAReplyVO vo) throws Exception {
		logger.info("create() 호출");
		replyDAO.insert(vo);
		logger.info("댓글 입력 성공");

		boardDAO.updateReplyCount(1, vo.getQnaboardNo());
		logger.info("게시판 댓글 개수 업데이트 성공");
		return 1;
	}

	@Override
	public List<QnAReplyVO> read(int qnaboardNo) {
		logger.info("read() 호출");
		return replyDAO.select(qnaboardNo);
	}

	@Override
	public int update(QnAReplyVO vo) {
		logger.info("update() 호출");
		return replyDAO.update(vo);
	}

	@Transactional
	@Override
	public int delete(int replyNo, int qnaboardNo) throws Exception {
		logger.info("delete() 호출");
		replyDAO.delete(replyNo);
		logger.info("댓글 삭제 성공");

		boardDAO.updateReplyCount(-1, qnaboardNo);
		logger.info("게시판 댓글 개수 업데이트 성공");
		return 1;
	}

}
