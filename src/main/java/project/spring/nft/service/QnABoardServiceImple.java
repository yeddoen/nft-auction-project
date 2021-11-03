package project.spring.nft.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.nft.domain.QnABoardVO;
import project.spring.nft.pageutil.PageCriteria;
import project.spring.nft.persistence.QnABoardDAO;

@Service
public class QnABoardServiceImple implements QnABoardService {

	private static final Logger logger =
			LoggerFactory.getLogger(QnABoardServiceImple.class);
	
	@Autowired
	private QnABoardDAO dao;
	
	@Override
	public int create(QnABoardVO vo) {
		logger.info("create() 호출 : vo = " + vo.toString());
		return dao.insert(vo);
	}


	@Override
	public QnABoardVO read(int bno) {
		logger.info("read() 호출 : bno = " + bno);
		return dao.select(bno);
	}

	@Override
	public int update(QnABoardVO vo) {
		logger.info("update() 호출 : vo =" + vo.toString());
		return dao.update(vo);
	}

	@Override
	public int delete(int bno) {
		logger.info("delete() 호출 : bno = " + bno);
		return dao.delete(bno);
	}

	@Override
	public int getTotalNumsOfRecords() {
		logger.info("getTotalNumsOfRecords() 호출");
		return dao.getTotalNumsOfRecords();
	}

	@Override
	public List<QnABoardVO> listAll() { // 게시물 전체 조회
		logger.info("listAll() 호출");
		return dao.select();
	}

	@Override
	public List<QnABoardVO> read(PageCriteria criteria) {
		logger.info("read() 호출");
		return dao.select(criteria);
	}


}
