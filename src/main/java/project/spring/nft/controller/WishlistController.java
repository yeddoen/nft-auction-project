package project.spring.nft.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.spring.nft.domain.WishlistVO;
import project.spring.nft.service.WishlistService;

@Controller
@RequestMapping(value = "/wishlist")
public class WishlistController {
	private static final Logger logger = LoggerFactory.getLogger(WishlistController.class);

	@Autowired
	private WishlistService wishlistservice;

	// 매핑!!
	@GetMapping("/wishpage")
	public void list(Model model, HttpServletRequest request) {
		logger.info("list() 호출");

		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");

		List<WishlistVO> list = wishlistservice.readByMemberId(memberId);
		model.addAttribute("list", list);

	}

} // end WishlistController class
