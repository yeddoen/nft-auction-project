package project.spring.nft.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.web3j.protocol.exceptions.TransactionException;

import project.spring.nft.domain.ArtVO;
import project.spring.nft.domain.MemberVO;
import project.spring.nft.domain.NftVO;
import project.spring.nft.domain.PaymentVO;
import project.spring.nft.pageutil.PageCriteria;
import project.spring.nft.pageutil.PageMaker;
import project.spring.nft.service.ArtService;
import project.spring.nft.service.MemberService;
import project.spring.nft.service.NftService;
import project.spring.nft.service.WishlistService;
import project.spring.nft.util.FileUploadUtil;
import project.spring.nft.util.KAS;
import project.spring.nft.util.MediaUtil;
import xyz.groundx.caver_ext_kas.CaverExtKAS;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.ApiException;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.api.kip17.model.Kip17ContractInfoResponse;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.api.kip17.model.Kip17TransactionStatusResponse;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.api.tokenhistory.model.Nft;

@Controller
public class ArtController {
	// 로거
	private static final Logger logger = LoggerFactory.getLogger(ArtController.class);

	@Resource(name = "uploadPath")
	private String uploadPath;

	@Autowired
	private ArtService artService;

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private JavaMailSender mailSender;

	@GetMapping("main")
	public void main(Model model, Integer page, Integer numsPerPage) {
		logger.info("readMain() 호출");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);
		currentAllList(model, page, numsPerPage);
	} // end main()
	
	@GetMapping("terms/termsOfService")
	public String termsOfService() {
		logger.info("termsOfService 호출");
		return "terms/termsOfService";	
	}
	
	@GetMapping("terms/termsOfInformation")
	public String termsOfInformation() {
		logger.info("termsOfInformation() 호출");
		return "terms/termsOfInformation";
	}
	
	@GetMapping("cur")
	public String currentSort(Model model, Integer page, Integer numsPerPage) {
		logger.info("currentSort() 호출");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);
		currentAllList(model, page, numsPerPage);

		return "main";
	} // end currentSort()

	private void currentAllList(Model model, Integer page, Integer numsPerPage) {
		logger.info("currentAllList() 호출");

		PageCriteria criteria = new PageCriteria();
		if (page != null) {
			criteria.setPage(page);
		}
		if (numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}

		List<ArtVO> list = artService.readCurrentArt(criteria);
		model.addAttribute("list", list); // list 데이터 보내기

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(artService.getTotalNumsOfRecords());
		pageMaker.setPageData();
		logger.info("이전 버튼 존재 유무 : " + pageMaker.isHasPrev());
		logger.info("다음 버튼 존재 유무 : " + pageMaker.isHasNext());
		logger.info("전체 게시글 수 : " + pageMaker.getTotalCount());
		model.addAttribute("pageMaker", pageMaker);
	} // end currentAllList()

	@GetMapping("wish")
	public String wishSort(Model model, Integer page, Integer numsPerPage) {
		logger.info("wishSort() 호출");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);

		PageCriteria criteria = new PageCriteria();
		if (page != null) {
			criteria.setPage(page);
		}
		if (numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}

		List<ArtVO> list = artService.readWishArt(criteria);
		model.addAttribute("list", list); // list 데이터 보내기

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(artService.getTotalNumsOfRecords());
		pageMaker.setPageData();
		logger.info("이전 버튼 존재 유무 : " + pageMaker.isHasPrev());
		logger.info("다음 버튼 존재 유무 : " + pageMaker.isHasNext());
		logger.info("전체 게시글 수 : " + pageMaker.getTotalCount());
		model.addAttribute("pageMaker", pageMaker);

		return "main";
	} // end wishSort()

	@GetMapping("view")
	public String viewSort(Model model, Integer page, Integer numsPerPage) {
		logger.info("viewSort() 호출");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);

		PageCriteria criteria = new PageCriteria();
		if (page != null) {
			criteria.setPage(page);
		}
		if (numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}

		List<ArtVO> list = artService.readViewArt(criteria);
		model.addAttribute("list", list); // list 데이터 보내기

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(artService.getTotalNumsOfRecords());
		pageMaker.setPageData();
		logger.info("이전 버튼 존재 유무 : " + pageMaker.isHasPrev());
		logger.info("다음 버튼 존재 유무 : " + pageMaker.isHasNext());
		logger.info("전체 게시글 수 : " + pageMaker.getTotalCount());
		model.addAttribute("pageMaker", pageMaker);

		return "main";
	} // end viewSort()

	@GetMapping("search")
	public String search(Model model, Integer page, Integer numsPerPage, String category, String keyword) {
		logger.info("search() 호출 : category = " + category + ", keyword = " + keyword);
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);

		PageCriteria criteria = new PageCriteria();
		if (page != null) {
			criteria.setPage(page);
		}
		if (numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}

		List<ArtVO> list = null;
		PageMaker pageMaker = new PageMaker();

		if (category.equals("artName")) { // 직품명 검색
			list = artService.readArtName(criteria, keyword);
			logger.info("검색 완료");
			pageMaker.setTotalCount(artService.getArtNameNumsOfRecords(keyword));
		} else { // 작가닉네임 검색
			list = artService.readMemberNickname(criteria, keyword);
			pageMaker.setTotalCount(artService.getNicknameNumsOfRecords(keyword));

		}
		model.addAttribute("list", list);

		pageMaker.setCriteria(criteria);
		pageMaker.setPageData();
		logger.info("이전 버튼 존재 유무 : " + pageMaker.isHasPrev());
		logger.info("다음 버튼 존재 유무 : " + pageMaker.isHasNext());
		logger.info("전체 게시글 수 : " + pageMaker.getTotalCount());
		model.addAttribute("pageMaker", pageMaker);

		return "main";
	} // end search()

	@GetMapping("/arts/register")
	public void registerGET() {
		logger.info("registerGET() 호출");
	} // end registerGET()

	@PostMapping("/arts/register")
	public String registerPOST(ArtVO vo, RedirectAttributes reAttr, HttpServletRequest request)
			throws ApiException, NoSuchMethodException, InstantiationException, ClassNotFoundException,
			IllegalAccessException, InvocationTargetException, IOException, TransactionException, InterruptedException {
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		MemberVO mvo = memberService.readByMemberId(memberId);

		logger.info("registerPOST() 호출 : 멤버 mvo = " + mvo.toString());
		logger.info("registerPOST() 호출 : vo = " + vo.toString());
		vo.setMemberAccount(mvo.getMemberAccount());

		// mint
		CaverExtKAS caver = new CaverExtKAS();
		caver.initKASAPI("1001", "KASKEMNC1D88Q7GH1TNVLZHR", "HOkyolJgnqehhk44F9ecIcbHCN6m-HBk-ARWMOYt");

		String nftOwner = mvo.getMemberAccount(); // 사용자의 account pool 계정
		String nftTokenId = "0x" + getRamdomPassword(4); // 토큰의 아이디 그냥 점차 증가하는거로 만들까.
		vo.setArtTokenId(nftTokenId); // art DB에 넣기.
		logger.info(vo.getArtTokenId());
		String nftJsonUri = vo.getArtJsonUri(); // 메타데이터주소.
		String nftContractAlias = vo.getMemberId();

		Kip17TransactionStatusResponse response = caver.kas.kip17.mint(nftContractAlias, nftOwner, nftTokenId,
				nftJsonUri);
		System.out.println("KIP-17 토큰 발행 response result " + response);

		int result = artService.createArt(vo);
		logger.info(result + "행 삽입");

		// 빠른 구현을 위해 일단 절대경로로했음. 카카오 일좀 해라
		// String artFileName =
		// "http:/localhost:8080/nft-auction/arts/display?fileName=" +
		// vo.getArtFileName();

		if (result == 1) { // 등록에 성공하면!
			// selectAndInsertKip17(mvo, vo, nftTokenId);
			int nicknameUpdate = artService.updateNickname(vo.getMemberId());
			logger.info(nicknameUpdate + "개 nickname 등록. art 등록 최종완료");
			reAttr.addFlashAttribute("registerResult", "success");

			return "redirect:/main";
		} else {
			return "redirect:/arts/register";
		}
	} // end registerPOST()

	private void selectAndInsertKip17(MemberVO mvo, ArtVO vo, String nftTokenId)
			throws ApiException, IOException, InterruptedException {
		CaverExtKAS caver = new CaverExtKAS();
		caver.initKASAPI("1001", "KASKEMNC1D88Q7GH1TNVLZHR", "HOkyolJgnqehhk44F9ecIcbHCN6m-HBk-ARWMOYt");

		// KIP-17 특정 alias 컨트랙트 조회(컨트랙트 주소 반환)
		String alias = mvo.getMemberId();
		Kip17ContractInfoResponse contractRes = caver.kas.kip17.getContract(alias);
		System.out.println("KIP-17 별명 " + alias + " 이름의 컨트랙트 조회 : " + contractRes);
		System.out.println("KIP-17 alias(memberid)를 매개변수로 컨트랙트 주소조회 : " + contractRes.getAddress());

		String contractAddress = contractRes.getAddress();
		String tokenId = nftTokenId;
		System.out.println("주소 : " + contractAddress + ", 토큰 아이디 : " + tokenId);

	} // method end ()

	public String getRamdomPassword(int len) {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		int idx = 0;
		StringBuffer sb = new StringBuffer();
		logger.info("charSet.length : " + charSet.length);

		for (int i = 0; i < len; i++) {
			idx = (int) (charSet.length * Math.random());
			// 9 * 생성된 난수를 Int로 추출 (소숫점제거)
			logger.info("idx : " + idx);
			sb.append(charSet[idx]);
		}

		return sb.toString();
	} // end getRamdomPassword()

	@GetMapping("/arts/upload-ajax")
	public void uploadAjaxGET() {
		logger.info("uploadAjaxGET() 호출");
	} // end uploadAjaxGET()

	// 이미지 업로드.
	@PostMapping("/arts/upload-ajax")
	@ResponseBody
	public ResponseEntity<String> uploadAjaxPOST(MultipartFile[] files) throws IOException {
		logger.info("uploadAjaxPOST() 호출");
		// 파일 하나만 저장
		String result = null; // 파일 경로 및 썸네일 이미지 이름
		result = FileUploadUtil.saveUploadedFile(uploadPath, files[0].getOriginalFilename(), files[0].getBytes());
		logger.info("fileuploadutil 의 result 로그 : " + result); // 11.17 수정
		if (result == null) {
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(result, HttpStatus.OK);
		}
	} // end uploadAjaxPOST()

	@GetMapping("/arts/display")
	public ResponseEntity<byte[]> display(String fileName) throws Exception {
		// 이미지도 데이터. byte형태를 가지고 있음
		logger.info("display() 호출");
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;

		// 파일 확장자
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
		logger.info(extension);

		if (extension.equals("gif")) { // 확장자가 gif인 경우
			fileName = fileName.replace("s_", "");
		}

		String filePath = uploadPath + fileName;
		in = new FileInputStream(filePath); // 파일넣기

		// 응답헤더(response header) org.springframework.http에 Content-Type 설정
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaUtil.getMediaType(extension));

		// 데이터 전송
		entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), // 파일에서 읽은 데이터
				httpHeaders, // 응답헤더
				HttpStatus.OK);

		return entity;
	} // end display()

	@GetMapping("/arts/detail")
	public void detail(Model model, Integer artNo, Integer page, HttpServletRequest request) throws ApiException,
			NoSuchMethodException, InstantiationException, ClassNotFoundException, IllegalAccessException,
			InvocationTargetException, IOException, TransactionException, InterruptedException, ParseException {
		logger.info("detail() 호출 : artNo = " + artNo + ", page = " + page);
		// 조회수 카운팅
		String ip = request.getRemoteAddr();
		int count = 0;
		logger.info("ip : " + ip);
		count++;
		int updateView = artService.updateView(artNo, count);
		logger.info(updateView + "행 조회수 업데이트");

		Map<String, Object> readMap = artService.readArtNo(artNo);
		ArtVO vo = (ArtVO) readMap.get("vo");
		logger.info("ArtVO 로그 : " + vo.toString());
		
		if (readMap.containsKey("maxMoney")) { // maxMoney가 있으면
			int maxMoney = (Integer) readMap.get("maxMoney");
			model.addAttribute("maxMoney", maxMoney);
		}

		PaymentVO pvo = artService.readPayResult(artNo);
		String payResult = "";
		if (pvo != null) { // 결제된 작품이다
			payResult = "fail";
		} else {
			payResult = "success";
		}

		model.addAttribute("payResult", payResult);
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
	} // end detail()

	@GetMapping("/arts/update")
	public void updateGET(Model model, Integer artNo, HttpServletRequest request) {
		logger.info("updateGET() 호출 : artNo = " + artNo);

		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");

		Map<String, Object> readMap = artService.readArtNo(artNo);
		ArtVO vo = (ArtVO) readMap.get("vo");

		if (memberId.equals(vo.getMemberId())) {
			model.addAttribute("vo", vo);
			model.addAttribute("access", "success");
		} else {
			model.addAttribute("access", "fail");
		}
	} // end updateGET()

	@PostMapping("arts/update")
	public String updatePOST(ArtVO vo, int artNo, RedirectAttributes reAttr)
			throws IOException, InterruptedException, ParseException {
		logger.info("updatePOST() 호출 : vo = " + vo.toString());
		
		
		
		int result = artService.updateArt(vo);

		if (result == 1) {
			logger.info("update 성공");
			reAttr.addFlashAttribute("updateResult", "success");
			return "redirect:/arts/detail?artNo=" + vo.getArtNo();
		} else {
			logger.info("update 실패");
			reAttr.addFlashAttribute("updateResult", "fail");
			return "redirect:/arts/update?artNo=" + vo.getArtNo();
		}
	} // end updatePOST()

	@GetMapping("arts/delete")
	public String delete(int artNo, RedirectAttributes reAttr, HttpServletRequest request) throws Exception {
		logger.info("delete 호출");

		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		MemberVO vo = memberService.readByMemberId(memberId);
		
		ArtVO avo = artService.readArtno(artNo);
		// wishlistService.delete(memberId, avo.getArtName()); // 작품 삭제시 wishlist 삭제 연동
		if(memberId.equals(vo.getMemberId())) {
			int result=artService.deleteArt(artNo);
			if (result == 1) {
				deleteKip17(vo, avo, request);
				reAttr.addFlashAttribute("deleteResult", "success");
				return "redirect:/main";
			} else {
				reAttr.addFlashAttribute("deleteResult", "fail");
				return "redirect:/arts/detail?artNo=" + artNo;
			}	
		}else { //작성자가 아닌 경우
			reAttr.addFlashAttribute("deleteResult", "fail"); 
			return "redirect:/main";
		}
	} // end deletePOST()

	private void deleteKip17(MemberVO vo, ArtVO avo, HttpServletRequest request) throws ApiException {
		CaverExtKAS caver = new CaverExtKAS();
		caver.initKASAPI("1001", "KASKEMNC1D88Q7GH1TNVLZHR", "HOkyolJgnqehhk44F9ecIcbHCN6m-HBk-ARWMOYt");

		String contractAddress = vo.getMemberId(); // alias로 사용. 사용자의 memberId.
		String from = vo.getMemberAccount(); // 사용자의 account
		String tokenId = avo.getArtTokenId(); // 해당 발행된 컨트랙트의 nft 토큰아이디.
		System.out.println("alias : " + contractAddress + "from : " + from + "tokenId" + tokenId);

		Kip17TransactionStatusResponse res = caver.kas.kip17.burn(contractAddress, from, tokenId);

		System.out.println("KIP-17 토큰 삭제 response result " + res);

	}

	@PostMapping("arts/winning")
	@ResponseBody
	public List<ArtVO> winningBid(String memberId) {
		logger.info("winningBid() 호출 : memberId = " + memberId);
		if (memberId == null) {
			return null;
		} else {
			List<ArtVO> bidList = artService.readWinBid(memberId);
			for (ArtVO vo : bidList) {
				System.out.println(vo.toString());
			}
			return bidList;
		}
	} // end winningBid()
	
	@GetMapping("arts/declare")
	public void declareGET(int artNo, HttpServletRequest request, Model model) {
		logger.info("declareGET() 호출");
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");

		model.addAttribute("artNo", artNo);
		MemberVO vo = artService.readMemberEmail(memberId);
		logger.info(vo.toString());
		String memberEmail = vo.getMemberEmail();
		model.addAttribute("memberEmail", memberEmail);
	}
	
	@PostMapping("arts/declare")
	public void declarePOST(HttpServletResponse response, RedirectAttributes reAttr, String memberEmail, int artNo, String declareContent) throws Exception {
		logger.info("declarePOST() 호출");
		int result = sendMail(memberEmail, artNo, declareContent);
			if(result == 1) {
				logger.info(result+"개 메일 발송 완료");
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script> alert('신고가 접수되었습니다. 감사합니다.'); window.close(); </script>");
				reAttr.addFlashAttribute("emailResult", "success");
			} else {
				logger.info("메일 발송 실패");	
				PrintWriter out = response.getWriter();
				out.println("<script> alert('신고 접수 실패 다시 시도해주세요'); window.close(); </script>");
				reAttr.addFlashAttribute("emailResult", "fail");
			}
		
	}
	
	// 작품 신고
	public int sendMail(String memberEmail, int artNo, String declareContent) throws Exception {
		int result = 0;
		String subject = artNo + "번 작품 신고 접수";
		String content = "신고 내용 : " + declareContent;
		String from = memberEmail;
		String to = "hansl2249@naver.com";
		
		MimeMessage mail = mailSender.createMimeMessage();
		MimeMessageHelper mailHelper = new MimeMessageHelper(mail, "UTF-8");
		mailHelper.setFrom(from);
		mailHelper.setTo(to);
		mailHelper.setSubject(subject);
		mailHelper.setText(content, true);
		
		mailSender.send(mail);
		result = 1;
		return result;
	} // end sendMail()
}
