package project.spring.nft.service;

import java.util.List;

import project.spring.nft.domain.QnABoardVO;
import project.spring.nft.pageutil.PageCriteria;

public interface QnABoardService {
		public abstract int create(QnABoardVO vo);
		public abstract List<QnABoardVO> read(PageCriteria criteria);
		public abstract QnABoardVO read(int qnaboardNo);
		public abstract int update(QnABoardVO vo);
		public abstract int delete(int qnaboardNo);
		public abstract int getTotalNumsOfRecords();
		public abstract List<QnABoardVO> listAll();
		public abstract int selectByMemberId(String memberId);
		public abstract List<QnABoardVO> selectListByMemberId(String memberId);
}
