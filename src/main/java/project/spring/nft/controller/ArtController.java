package project.spring.nft.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ArtController {
	private static final Logger logger=
			LoggerFactory.getLogger(ArtController.class);
	
	@GetMapping("main")
	public void readMain() {
		logger.info("readMain() 호출");
	}
}
