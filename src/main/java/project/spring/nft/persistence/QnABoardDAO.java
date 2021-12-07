package project.spring.nft.persistence;

import java.util.List;

import project.spring.nft.domain.QnABoardVO;
import project.spring.nft.pageutil.PageCriteria;


public interface QnABoardDAO {
	int insert(QnABoardVO vo);
	List<QnABoardVO> select();
	QnABoardVO select(int qnaboardNo);
	int update(QnABoardVO vo);
	int delete(int qnaboardNo);
	List<QnABoardVO> select(PageCriteria c);
	int getTotalNumsOfRecords();
	int updateReplyCount(int amount, int qnaboardNo);
	int selectByMemberId(String memberId);
	List<QnABoardVO> selectListByMemberId(PageCriteria c, String memberId);
	int deleteMemberId(String memberId);
}
