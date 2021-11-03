package project.spring.nft.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.spring.nft.domain.QnAReplyVO;
import project.spring.nft.service.QnAReplyService;


@RestController
@RequestMapping(value="qnaboard/qnareplies/rest")
public class QnAReplyRESTController {
	private static final Logger logger =
			LoggerFactory.getLogger(QnAReplyRESTController.class);
	
	@Autowired
	private QnAReplyService replyService;
	
	@PostMapping // 댓글 입력
	public ResponseEntity<Integer> createReply(
			@RequestBody QnAReplyVO vo) {
		logger.info(vo.toString());
		int result = 0;
			try {
				result=replyService.create(vo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@GetMapping("/all/{replybno}")
	public ResponseEntity<List<QnAReplyVO>> readReplies(
			@PathVariable("replybno") int replybno) {
		logger.info("readReplies() 호출");
		List<QnAReplyVO> list = replyService.read(replybno);
		return new ResponseEntity<List<QnAReplyVO>>(list, HttpStatus.OK);
	}
	
	@PutMapping("/{replyNo}")
	public ResponseEntity<String> updateReply(
			@PathVariable("replyNo") int replyNo,
			@RequestBody QnAReplyVO vo
			){
		vo.setReplyNo(replyNo);
		int result = replyService.update(vo);
		if(result == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/{replyNo}")
	public ResponseEntity<String> deleteReply(
			@PathVariable("replyNo") int replyNo,
			@RequestBody QnAReplyVO vo) {
		try {
			replyService.delete(replyNo, vo.getReplybno());
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		}
		
	}
	
	
}
