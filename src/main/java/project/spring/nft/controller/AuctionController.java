package project.spring.nft.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.spring.nft.domain.ArtAuctionVO;
import project.spring.nft.domain.ArtVO;
import project.spring.nft.domain.AuctionVO;
import project.spring.nft.service.ArtService;
import project.spring.nft.service.AuctionService;

@Controller
@RequestMapping(value = "/arts")
public class AuctionController {
	private static final Logger logger =
			LoggerFactory.getLogger(AuctionController.class);
	
	@Autowired
	AuctionService auctionService;
	
	@Autowired
	ArtService artService;
	
	@GetMapping("/auctionRT")
	public void AuctionRTList(Model model) {
		logger.info("auctionRT 호출");
		List<ArtAuctionVO> RTlist = auctionService.read();

		model.addAttribute("RTlist", RTlist);

	}
}
