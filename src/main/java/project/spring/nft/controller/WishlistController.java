package project.spring.nft.controller;

import java.io.Console;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.spring.nft.util.FileUploadUtil;
import project.spring.nft.domain.ArtVO;
import project.spring.nft.domain.WishlistVO;
import project.spring.nft.pageutil.PageCriteria;
import project.spring.nft.pageutil.PageMaker;
import project.spring.nft.service.ArtService;
import project.spring.nft.service.WishlistService;

@Controller
@RequestMapping(value = "/wishlist")
public class WishlistController {
	private static final Logger logger = LoggerFactory.getLogger(WishlistController.class);

	@Resource(name = "uploadPath")
	private String uploadPath;

	@Autowired
	private WishlistService wishlistservice;

	@Autowired
	private ArtService artService;

	// select 매핑!!
	@GetMapping("/wishpage")
	public void listGET(Model model, HttpServletRequest request, Integer page, Integer numsPerPage) {
		logger.info("listGET() 호출");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);

		// TODO : 위시리스트 등록이 안되어있는 상황도 봐야함!

		PageCriteria criteria = new PageCriteria();
		if (page != null) {
			criteria.setPage(page);
		}
		if (numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}

		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");

		List<WishlistVO> list = wishlistservice.readByMemberId(memberId);
		model.addAttribute("list", list);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);

	} // end listGET()

	// detail.jsp에서 ajax로 전송된 정보를 받고 db로 연결.
	@PostMapping("/wishpage")
	public ResponseEntity<Integer> insertWishlistPOST(int artNo, Model model, HttpServletRequest request,
			WishlistVO vo) {
		logger.info("list()호출");
		// wishNo가 제대로 안받아졌음! 왜일까.
		
		// create로 등록할때, art테이블의 찜 수도 업데이트됨!
		

		// 등록시 selectCount로 기존에 장바구니에 있는지 없는지 검사하기
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		String artName = vo.getArtName();
		
		int count = 0;
		int result = 0;
		int resultCount = wishlistservice.readCount(memberId, artName);

		if (resultCount == 1) { // db에 이미 있다면 삭제(delete)
			logger.info("DB에 등록되어있음");
			result = wishlistservice.delete(memberId, artName); // 1이 들어옴
			
			// art테이블의 wishcount 업데이트 증가.
			count--;
			int updateWishCount = artService.updateWishCount(artNo, count);
			logger.info(updateWishCount + "행 찜수 감소");

			if (result == 1) {
				logger.info(result + "행 삭제 성공");
				result = 2;
			} else {
				logger.info(result + "행 삭제 실패");
			}
			// delete는 2로 주기!
			return new ResponseEntity<Integer>(result, HttpStatus.OK); // 1전달

		} else { // DB에 없으면 등록(insert)
			logger.info("DB에 등록되어있지않음, 등록가능");
			result = wishlistservice.create(vo); // 1이 들어옴
			
			// art테이블의 wishcount 업데이트 증가.
			count++;
			int updateWishCount = artService.updateWishCount(artNo, count);
			logger.info(updateWishCount + "행 찜수 증가");
			
			if (result == 1) {
				logger.info(result + "행 삽입 성공");
				logger.info("찜수 업데이트 성공");

			} else {
				logger.info("실패");
				logger.info("찜수 업데이트 실패");
			}

			return new ResponseEntity<Integer>(result, HttpStatus.OK); // 1전달

		}

	} // end insertWishlistPost

} // end WishlistController class