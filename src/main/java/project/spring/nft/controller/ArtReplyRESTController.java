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

import project.spring.nft.domain.ArtReplyVO;
import project.spring.nft.service.ArtReplyService;


@RestController
@RequestMapping(value = "arts/replies/rest")
public class ArtReplyRESTController {
	private static final Logger logger =
			LoggerFactory.getLogger(QnAReplyRESTController.class);
	
	@Autowired
	private ArtReplyService artReplyService;
	
	@PostMapping // 댓글 입력
	public ResponseEntity<Integer> createReply(
			@RequestBody ArtReplyVO vo) {
		logger.info(vo.toString());
		int result = 0;
			try {
				result=artReplyService.create(vo);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	} //end createReply()

	@GetMapping("/all/{artNo}")
	public ResponseEntity<List<ArtReplyVO>> readReplies(
			@PathVariable("artNo") int artNo) {
		logger.info("readReplies() 호출");
		List<ArtReplyVO> list = artReplyService.read(artNo);
		for (ArtReplyVO vo : list) {
			System.out.println(vo.toString());
		}
		return new ResponseEntity<List<ArtReplyVO>>(list, HttpStatus.OK);
	} //end readReplies()
	
	@PutMapping("/{artReplyNo}")
	public ResponseEntity<String> updateReply(
			@PathVariable("artReplyNo") int artReplyNo,
			@RequestBody ArtReplyVO vo){
		vo.setArtReplyNo(artReplyNo);
		int result = artReplyService.update(vo);
		if(result == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		}
	} //end updateReply()
	
	@DeleteMapping("/{artReplyNo}")
	public ResponseEntity<String> deleteReply(
			@PathVariable("artReplyNo") int artReplyNo,
			@RequestBody ArtReplyVO vo) {
		try {
			artReplyService.delete(artReplyNo, vo.getArtNo());
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		}
		
	} //end deleteReply()
}
