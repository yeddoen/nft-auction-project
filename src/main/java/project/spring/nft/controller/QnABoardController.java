package project.spring.nft.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.spring.nft.domain.QnABoardVO;
import project.spring.nft.pageutil.PageCriteria;
import project.spring.nft.pageutil.PageMaker;
import project.spring.nft.service.QnABoardService;

@Controller
@RequestMapping(value="/qnaboard")
public class QnABoardController {
	private static final Logger logger =
			LoggerFactory.getLogger(QnABoardController.class);
	
	@Autowired QnABoardService qnaboardservice;
	
	@GetMapping("/qnalist")
	public void list(Model model, Integer page, Integer numsPerPage) {
		logger.info("list() 호출");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);
		
		PageCriteria criteria = new PageCriteria();
		if(page != null) {
			criteria.setPage(page);
		}
		if(numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}
		
		List<QnABoardVO> list = qnaboardservice.read(criteria);	
		model.addAttribute("qnalist", qnaboardservice.listAll());
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(qnaboardservice.getTotalNumsOfRecords());
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);
	} // end qnalist()
	
	@GetMapping("/qnadetail")
	public void detail(Model model, Integer bno) {
		logger.info("detail() 호출 : bno = " + bno);
		QnABoardVO vo = qnaboardservice.read(bno);
		model.addAttribute("vo", vo);
	} // end qnadetail()
	
	@GetMapping("/qnaregister")
	public void registerGET() {
		logger.info("registerGET() 호출");
	} // end registerGET()


	@PostMapping("/qnaregister")
	public String registerPOST(QnABoardVO vo, RedirectAttributes reAttr) {
		logger.info("registerPOST() 호출");
		logger.info(vo.toString());
		int result = qnaboardservice.create(vo);
		logger.info(result + "개 입력");
		if(result == 1) {
			reAttr.addFlashAttribute("insert_result", "success");
			return "redirect:/qnaboard/qnalist";
		} else {
			reAttr.addFlashAttribute("insert_result", "fail");			
			return "redirect:/qnaboard/qnaregister";
		}
	}
	
	@PostMapping("/qnaupdate")
	public String updatePUT(QnABoardVO vo, Integer page) {
		logger.info("updatePUT() 호출 : bno" + vo.getBno());
		int result = qnaboardservice.update(vo);
		if(result == 1) {
			return "redirect:/qnaboard/qnalist";
		} else {
			return "redirect:/qnaboard/qnaupdate?bno=" + vo.getBno();
		}
	}
	
	@GetMapping("/qnadelete")
	public String delete(Integer bno) {
		logger.info("delete() 호출 : bno = " + bno);
		int result = qnaboardservice.delete(bno);
		if(result == 1) {
			return "redirect:/qnaboard/qnalist";
		} else {
			return "redirect:/qnaboard/qnalist";
		}
	}
	
}
