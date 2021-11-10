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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.spring.nft.domain.MemberVO;
import project.spring.nft.domain.QnABoardVO;
import project.spring.nft.pageutil.PageCriteria;
import project.spring.nft.pageutil.PageMaker;
import project.spring.nft.service.MemberService;
import project.spring.nft.service.QnABoardService;

@Controller
@RequestMapping(value = "/qnaboard")
public class QnABoardController {
	private static final Logger logger = LoggerFactory.getLogger(QnABoardController.class);

	@Autowired
	QnABoardService qnaboardservice;
	@Autowired
	MemberService memberservice;

	@GetMapping("/qnalist")
	public void list(Model model, Integer page, Integer numsPerPage) {
		logger.info("list() 호출");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);

		PageCriteria criteria = new PageCriteria();
		if (page != null) {
			criteria.setPage(page);
		}
		if (numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}

		List<QnABoardVO> list = qnaboardservice.read(criteria);
		model.addAttribute("qnalist", list);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(qnaboardservice.getTotalNumsOfRecords());
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);
	} // end qnalist()

	@GetMapping("/qnadetail")
	public void detail(Model model, Integer qnaboardNo, String memberId) {
		logger.info("detail() 호출 : qnaboardNo = " + qnaboardNo);
		QnABoardVO vo = qnaboardservice.read(qnaboardNo);
		MemberVO mo = memberservice.readByBoardMemberId(memberId);
		MemberVO mo = memberservice.readByMemberId(memberId);
		model.addAttribute("vo", vo);
		model.addAttribute("mo", mo);
	} // end qnadetail()

	@GetMapping("/qnaregister")
	public void registerGET(Model model,String memberId) {
		logger.info("registerGET() 호출");
		MemberVO mo = memberservice.readByMemberId(memberId);
		model.addAttribute("mo", mo);
	} // end registerGET()

	@PostMapping("/qnaregister")
	public String registerPOST(QnABoardVO vo, HttpServletRequest request, RedirectAttributes reAttr) {
		String memberId = null;		
		String memberNickname = null;
		logger.info("registerPOST() 호출");
		logger.info(vo.toString());
		int result = qnaboardservice.create(vo);
		logger.info(result + "개 입력");
		if (result == 1) {
			reAttr.addFlashAttribute("insert_result", "success");
			HttpSession session = request.getSession();
			session.setAttribute(vo.getMemberId(), memberId);
			session.setAttribute(vo.getMemberNickname(), memberNickname);
			return "redirect:/qnaboard/qnalist";
		} else {
			reAttr.addFlashAttribute("insert_result", "fail");
			return "redirect:/qnaboard/qnaregister";
		}
	}

	@GetMapping("/qnaupdate")
	public void updateGET(Model model, Integer qnaboardNo, Integer page) {
		logger.info("updateGET() 호출 : qnaboardNo = " + qnaboardNo);
		QnABoardVO vo = qnaboardservice.read(qnaboardNo);
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
	}

	@PostMapping("/qnaupdate")
	public String updatePUT(QnABoardVO vo, Integer page) {
		logger.info("updatePUT() 호출 : bno" + vo.getQnaboardNo());
		int result = qnaboardservice.update(vo);
		if (result == 1) {
			return "redirect:/qnaboard/qnalist";
		} else {
			return "redirect:/qnaboard/qnaupdate?bno=" + vo.getQnaboardNo();
		}
	}

	@GetMapping("/qnadelete")
	public String delete(Integer qnaboardNo) {
		logger.info("delete() 호출 : bno = " + qnaboardNo);
		int result = qnaboardservice.delete(qnaboardNo);
		if (result == 1) {
			return "redirect:/qnaboard/qnalist";
		} else {
			return "redirect:/qnaboard/qnalist";
		}
	}

}
