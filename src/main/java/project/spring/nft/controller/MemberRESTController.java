package project.spring.nft.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import project.spring.nft.service.MemberService;

@RestController
@RequestMapping(value = "members/rest")
public class MemberRESTController {
	private static final Logger logger=
			LoggerFactory.getLogger(MemberRESTController.class);
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/id-check/{member_id}")
	public ResponseEntity<Integer> idCheck(@PathVariable("member_id") String memberId) {
		logger.info("idCheck() 호출");
		//TODO db연결하고 확인해보기
		int result=memberService.readIdCheck(memberId);
		if(result==0) {
			logger.info("아이디 중복 없음 : memberId = "+memberId);
		}else {
			logger.info("중복된 아이디 있음");
		}	
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	} //end idCheck()
	
	@PostMapping("/confirm")
	public ResponseEntity<String> confirmMemberPOST(String memberUid) {
		logger.info("confirmMemberPOST() 호출 : memberUid = "+memberUid);
		
		return new ResponseEntity<String>(memberUid, HttpStatus.OK);
	} //end confirmMemberPOST()
}