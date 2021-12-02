package project.spring.nft.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	public void list(Model model, Integer page, Integer numsPerPage, HttpServletRequest request) {
		logger.info("list() 호출");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);

		PageCriteria criteria = new PageCriteria();
		if (page != null) {
			criteria.setPage(page);
		}
		if (numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}

		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		session.getAttribute("memberId");
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);

		if(memberId.equals("admin")) {	
			List<QnABoardVO> list = qnaboardservice.read(criteria);
			model.addAttribute("qnalist", list);
			logger.info(list.toString());			
			pageMaker.setTotalCount(qnaboardservice.getTotalNumsOfRecords());
		} else {
			List<QnABoardVO> list = qnaboardservice.selectListByMemberId(criteria, memberId);
			model.addAttribute("qnalist", list);
			logger.info(list.toString());
			pageMaker.setTotalCount(qnaboardservice.selectByMemberId(memberId));
		}
		 
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);
		
	} // end qnalist()

	@GetMapping("/qnadetail")
	public void detail(Model model, Integer qnaboardNo, HttpServletRequest request) {
		logger.info("detail() 호출 : qnaboardNo = " + qnaboardNo);
		QnABoardVO vo = qnaboardservice.read(qnaboardNo);
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		MemberVO mo = memberservice.readByMemberId(memberId);
		model.addAttribute("vo", vo);
		model.addAttribute("mo", mo);
	} // end qnadetail()

	@GetMapping("/qnaregister")
	public void registerGET(Model model, HttpServletRequest request) {
		logger.info("registerGET() 호출");
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
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
			HttpSession session = request.getSession();
			session.setAttribute(vo.getMemberId(), memberId);
			session.setAttribute(vo.getMemberNickname(), memberNickname);
			reAttr.addFlashAttribute("insertResult", "success");
			return "redirect:/qnaboard/qnalist";
		} else {
			reAttr.addFlashAttribute("insertResult", "fail");
			return "redirect:/qnaboard/qnaregister";
		}
	}

	@GetMapping("/qnaupdate")
	public void updateGET(Model model, Integer qnaboardNo) {
		logger.info("updateGET() 호출 : qnaboardNo = " + qnaboardNo);
		QnABoardVO vo = qnaboardservice.read(qnaboardNo);
		// HttpSession session = request.getSession();
		// String memberId = (String) session.getAttribute("memberId");
		model.addAttribute("vo", vo);
		// model.addAttribute("page", page);
	}

	@PostMapping("/qnaupdate")
	public String updatePOST(QnABoardVO vo, RedirectAttributes reAttr) {
		logger.info("updatePOST() 호출 : vo = " + vo.toString());
		int result = qnaboardservice.update(vo);
		if (result == 1) {
			reAttr.addFlashAttribute("updateResult", "success");
			return "redirect:/qnaboard/qnalist";
		} else {
			reAttr.addFlashAttribute("updateResult", "fail");
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
