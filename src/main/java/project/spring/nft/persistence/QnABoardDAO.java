package project.spring.nft.persistence;

import java.util.List;

import project.spring.nft.domain.QnABoardVO;
import project.spring.nft.pageutil.PageCriteria;


public interface QnABoardDAO {
	int insert(QnABoardVO vo);
	List<QnABoardVO> select();
	QnABoardVO select(int bno);
	int update(QnABoardVO vo);
	int delete(int bno);
	List<QnABoardVO> select(PageCriteria c);
	int getTotalNumsOfRecords();
	int updateReplyCount(int amount, int bno);
}
