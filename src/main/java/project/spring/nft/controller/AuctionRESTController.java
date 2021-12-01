package project.spring.nft.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.spring.nft.domain.AuctionVO;
import project.spring.nft.service.AuctionService;

@RestController
@RequestMapping(value = "arts/auction")
public class AuctionRESTController {
	private static final Logger logger=
			LoggerFactory.getLogger(AuctionRESTController.class);
	
	@Autowired
	private AuctionService auctionService;
	
	@PostMapping
	public ResponseEntity<Integer> auctionBid(@RequestBody AuctionVO vo) {
		logger.info("auctionBid() 호출 : vo = "+vo.toString());
		int result=auctionService.create(vo);
		logger.info(result+"행 삽입");
		if(result==1) {
			int update=auctionService.updateNickname(vo.getMemberId());
			logger.info(update+"개 닉네임 적용");			
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	} //end auctionBid()
	
	@GetMapping("/all/{artNo}")
	public ResponseEntity<List<AuctionVO>> allAuctionList(
			@PathVariable("artNo") int artNo){
		logger.info("allAuctionList() 호출");
		List<AuctionVO> list=auctionService.readAll(artNo);
		return new ResponseEntity<List<AuctionVO>>(list, HttpStatus.OK);	
	} //end allAuctionList()
	
	@PutMapping("{artNo}")
	public ResponseEntity<String> updateAuctionResult(
			@PathVariable("artNo") int artNo, 
			@RequestBody Map<String, Integer> map){
		logger.info("updateAuctionResult() 호출 : maxMoney = "+map.get("maxMoney"));
		int result=auctionService.updateWinner(artNo, map.get("maxMoney"));
		if(result == 1) {
			logger.info(artNo+"번 작품 낙찰자 업데이트");
			AuctionVO vo=auctionService.selectWinner(artNo);
			String memberId=vo.getMemberId();
			logger.info("낙찰자 아이디 조회");
			return new ResponseEntity<String>(memberId, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		}
	} //end updateAuctionResult()
}