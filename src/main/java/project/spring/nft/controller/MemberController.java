package project.spring.nft.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.spring.nft.domain.MemberVO;
import project.spring.nft.service.MemberService;

@Controller
@RequestMapping(value = "/members")
public class MemberController {
	private static final Logger logger=
			LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/sign-up")
	public void joinMemberGET() {
		logger.info("joinMemberGET() 호출");
	} //end joinMemberGET()
	
	@PostMapping("/sign-up")
	public String joinMemberPOST(MemberVO vo, RedirectAttributes reAttr) {
		logger.info("joinMemberPOST() 호출 : vo = "+vo.toString());
		int result=memberService.createMember(vo);
		logger.info(result+"행 삽입");
		
		if(result==1) {
			reAttr.addFlashAttribute("joinResult", "success"); 
			//TODO : main에서 회원가입 성공 alert 띄우기
			return "redirect:/main"; 
		}else {
			return "redirect:/members/sign-up";
		}
	} //end joinMemberPOST()
	
	@GetMapping("/login")
	public void loginMemberGET() { 
		logger.info("loginMemberGET() 호출");
	} //end loginMemberGET()
	
	@PostMapping("/login")
	public String loginMemberPOST(String memberId, String memberPassword, 
			HttpServletRequest request, RedirectAttributes reAttr) {
		logger.info("loginMemberPOST() 호출 : memberId = "+memberId+", memberPassword = "+memberPassword);
		//TODO 아이디와 비밀번호가 일치하는 회원이 있으면 회원 아이디로 세션생성하고 타겟url 생성하고 redirect
		MemberVO vo=memberService.readLogin(memberId, memberPassword);
		
		if(vo!=null) { //아이디, 비밀번호가 일치하는 정보가 존재할 경우
			logger.info("로그인 성공 : memberNo = "+vo.getMemberNo());
			HttpSession session=request.getSession();
			session.setAttribute("memberId", memberId); //id세션 생성
			
			String targetURL=(String)session.getAttribute("targetURL");
			logger.info("targetURL : "+targetURL);
			if(targetURL!=null) {
				session.removeAttribute("targetURL");
				return "redirect:"+targetURL;
			}else {
				return "redirect:/main";				
			}
		}else {
			logger.info("로그인 실패");
			reAttr.addFlashAttribute("loginResult", "fail"); 
			return "redirect:/members/login";
		}
	} //end loginMemberPOST()
	
	@GetMapping("/my-page/member")
	public void myPageGET(Model model, HttpServletRequest request) {
		logger.info("myPageGET() 호출");
		
		HttpSession session=request.getSession();
		String memberId = (String) session.getAttribute("memberId");

		MemberVO vo = memberService.readByMemberId(memberId);
		logger.info("마이페이지 회원정보 : " + vo.toString());
		
		// model로 보내주는 것 까먹지 말기!!
		model.addAttribute("vo", vo);
	}
	
	@PostMapping("/my-page/member")
	public void myPagePOST() {
		logger.info("myPagePOST() 호출");
	}
	
	// 회원 정보 수정.
	@GetMapping("/my-page/update")
	public void updateMemberGET(Model model, HttpServletRequest request) {
		logger.info("updateGET() 호출");
		
		HttpSession session=request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		
		// 정보 가져와서 보여주기
		MemberVO vo = memberService.readByMemberId(memberId);
		logger.info("마이페이지 회원정보 : " + vo.toString());

		model.addAttribute("vo", vo);
		
	}
	
	@PostMapping("/my-page/update")
	public String updateMemberPOST(MemberVO vo, HttpServletRequest request) {
		logger.info("updatePOST() 호출 , 입력한 정보 vo : " + vo);
		int result = memberService.updateMemberInfo(vo);
		
		HttpSession session=request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		
		if(result == 1) {
			logger.info(result + "성공 , 세션 유지 : " + memberId);
			// TODO : 리다이렉트, 타겟URI 바꾸기!!
			return "redirect:";
		}else {
			logger.info(result + "실패 , 세션 유지 : " + memberId);
			return "redirect:/main";				
		}
		
	}
	
	// 봐야함! delete 페이지 불러오기에서 프롬프트 띄우는 형식으로 변경.
	@GetMapping("/my-page/delete")
	public void deleteMemberGET(Model model, HttpServletRequest request) {
		logger.info("deleteGET() 호출");
	}

} // end class
