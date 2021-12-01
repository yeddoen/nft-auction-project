package project.spring.nft.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.spring.nft.domain.ArtReplyVO;
import project.spring.nft.persistence.ArtDAO;
import project.spring.nft.persistence.ArtReplyDAO;

@Service
public class ArtReplyServiceImple implements ArtReplyService {
	private static final Logger logger=
			LoggerFactory.getLogger(ArtReplyServiceImple.class);
	
	@Autowired
	private ArtReplyDAO artReplyDAO;
	@Autowired
	private ArtDAO artDAO;
	
	@Transactional
	@Override
	public int create(ArtReplyVO vo) throws Exception {
		logger.info("create() 호출");
		artReplyDAO.insert(vo);
		logger.info("댓글 입력 성공");

		artDAO.updateReplyCount(1, vo.getArtNo());
		logger.info("작품 댓글 개수 업데이트 성공");
		return 1;
	}

	@Override
	public List<ArtReplyVO> read(int artNo) {
		logger.info("read() 호출");
		List<ArtReplyVO> list = artReplyDAO.select(artNo); //원본 댓글 전체 리스트
		List<ArtReplyVO> list2 = new ArrayList<ArtReplyVO>();
		for(ArtReplyVO vo : list) {
			logger.info(vo.toString());
			list2.add(vo);
			int replyParentNo = vo.getArtReplyNo();
			int index = list.indexOf(vo);			
			List<ArtReplyVO> listReply = artReplyDAO.selectReply(artNo, replyParentNo);
			// list.(index, listReply);
			list2.addAll(listReply);
		}		
		return list2;
	}

	@Override
	public int update(ArtReplyVO vo) {
		logger.info("update() 호출");
		return artReplyDAO.update(vo);
	}

	@Transactional
	@Override
	public int delete(int artReplyNo, int artNo) throws Exception {
		logger.info("delete() 호출");
		int result = artReplyDAO.delete(artReplyNo);
		logger.info(result + "개 댓글 삭제 성공");

		artDAO.updateReplyCount(-result, artNo);
		logger.info("작품 댓글 개수 업데이트 성공");
		return 1;
	}

}
