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
import project.spring.nft.util.S3Util;
import project.spring.nft.util.UploadFileUtils;
import xyz.groundx.caver_ext_kas.CaverExtKAS;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.ApiException;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.api.kip17.model.Kip17ContractInfoResponse;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.api.kip17.model.Kip17TransactionStatusResponse;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.api.tokenhistory.model.Nft;

@Controller
public class ArtController {
	// ??????
	private static final Logger logger = LoggerFactory.getLogger(ArtController.class);

	@Resource(name = "uploadPath")
	private String uploadPath;

	@Autowired
	private ArtService artService;

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	S3Util s3 = new S3Util();
	String bucketName = "nftauctionbucket";

	@GetMapping("main")
	public void main(Model model, Integer page, Integer numsPerPage) {
		logger.info("readMain() ??????");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);
		currentAllList(model, page, numsPerPage);
	} // end main()
	
	@GetMapping("terms/termsOfService")
	public String termsOfService() {
		logger.info("termsOfService ??????");
		return "terms/termsOfService";	
	}
	
	@GetMapping("terms/termsOfInformation")
	public String termsOfInformation() {
		logger.info("termsOfInformation() ??????");
		return "terms/termsOfInformation";
	}
	
	@GetMapping("cur")
	public String currentSort(Model model, Integer page, Integer numsPerPage) {
		logger.info("currentSort() ??????");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);
		currentAllList(model, page, numsPerPage);

		return "main";
	} // end currentSort()

	private void currentAllList(Model model, Integer page, Integer numsPerPage) {
		logger.info("currentAllList() ??????");

		PageCriteria criteria = new PageCriteria();
		if (page != null) {
			criteria.setPage(page);
		}
		if (numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}

		List<ArtVO> list = artService.readCurrentArt(criteria);
		model.addAttribute("list", list); // list ????????? ?????????

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(artService.getTotalNumsOfRecords());
		pageMaker.setPageData();
		logger.info("?????? ?????? ?????? ?????? : " + pageMaker.isHasPrev());
		logger.info("?????? ?????? ?????? ?????? : " + pageMaker.isHasNext());
		logger.info("?????? ????????? ??? : " + pageMaker.getTotalCount());
		model.addAttribute("pageMaker", pageMaker);
	} // end currentAllList()

	@GetMapping("wish")
	public String wishSort(Model model, Integer page, Integer numsPerPage) {
		logger.info("wishSort() ??????");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);

		PageCriteria criteria = new PageCriteria();
		if (page != null) {
			criteria.setPage(page);
		}
		if (numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}

		List<ArtVO> list = artService.readWishArt(criteria);
		model.addAttribute("list", list); // list ????????? ?????????

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(artService.getTotalNumsOfRecords());
		pageMaker.setPageData();
		logger.info("?????? ?????? ?????? ?????? : " + pageMaker.isHasPrev());
		logger.info("?????? ?????? ?????? ?????? : " + pageMaker.isHasNext());
		logger.info("?????? ????????? ??? : " + pageMaker.getTotalCount());
		model.addAttribute("pageMaker", pageMaker);

		return "main";
	} // end wishSort()

	@GetMapping("view")
	public String viewSort(Model model, Integer page, Integer numsPerPage) {
		logger.info("viewSort() ??????");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);

		PageCriteria criteria = new PageCriteria();
		if (page != null) {
			criteria.setPage(page);
		}
		if (numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}

		List<ArtVO> list = artService.readViewArt(criteria);
		model.addAttribute("list", list); // list ????????? ?????????

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(artService.getTotalNumsOfRecords());
		pageMaker.setPageData();
		logger.info("?????? ?????? ?????? ?????? : " + pageMaker.isHasPrev());
		logger.info("?????? ?????? ?????? ?????? : " + pageMaker.isHasNext());
		logger.info("?????? ????????? ??? : " + pageMaker.getTotalCount());
		model.addAttribute("pageMaker", pageMaker);

		return "main";
	} // end viewSort()

	@GetMapping("search")
	public String search(Model model, Integer page, Integer numsPerPage, String category, String keyword) {
		logger.info("search() ?????? : category = " + category + ", keyword = " + keyword);
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

		if (category.equals("artName")) { // ????????? ??????
			list = artService.readArtName(criteria, keyword);
			logger.info("?????? ??????");
			pageMaker.setTotalCount(artService.getArtNameNumsOfRecords(keyword));
		} else { // ??????????????? ??????
			list = artService.readMemberNickname(criteria, keyword);
			pageMaker.setTotalCount(artService.getNicknameNumsOfRecords(keyword));

		}
		model.addAttribute("list", list);

		pageMaker.setCriteria(criteria);
		pageMaker.setPageData();
		logger.info("?????? ?????? ?????? ?????? : " + pageMaker.isHasPrev());
		logger.info("?????? ?????? ?????? ?????? : " + pageMaker.isHasNext());
		logger.info("?????? ????????? ??? : " + pageMaker.getTotalCount());
		model.addAttribute("pageMaker", pageMaker);

		return "main";
	} // end search()

	@GetMapping("/arts/register")
	public void registerGET() {
		logger.info("registerGET() ??????");
	} // end registerGET()

	@PostMapping("/arts/register")
	public String registerPOST(ArtVO vo, RedirectAttributes reAttr, HttpServletRequest request)
			throws ApiException, NoSuchMethodException, InstantiationException, ClassNotFoundException,
			IllegalAccessException, InvocationTargetException, IOException, TransactionException, InterruptedException {
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		MemberVO mvo = memberService.readByMemberId(memberId);

		logger.info("registerPOST() ?????? : ?????? mvo = " + mvo.toString());
		logger.info("registerPOST() ?????? : vo = " + vo.toString());
		vo.setMemberAccount(mvo.getMemberAccount());

		// mint
		CaverExtKAS caver = new CaverExtKAS();
		caver.initKASAPI("1001", "KASKEMNC1D88Q7GH1TNVLZHR", "HOkyolJgnqehhk44F9ecIcbHCN6m-HBk-ARWMOYt");

		String nftOwner = mvo.getMemberAccount(); // ???????????? account pool ??????
		String nftTokenId = "0x" + getRamdomPassword(4); // ????????? ????????? ?????? ?????? ?????????????????? ?????????.
		vo.setArtTokenId(nftTokenId); // art DB??? ??????.
		logger.info(vo.getArtTokenId());
		String nftJsonUri = vo.getArtJsonUri(); // ?????????????????????.
		String nftContractAlias = vo.getMemberId();

		Kip17TransactionStatusResponse response = caver.kas.kip17.mint(nftContractAlias, nftOwner, nftTokenId,
				nftJsonUri);
		System.out.println("KIP-17 ?????? ?????? response result " + response);

		int result = artService.createArt(vo);
		logger.info(result + "??? ??????");

		// ?????? ????????? ?????? ?????? ?????????????????????. ????????? ?????? ??????
		// String artFileName =
		// "http:/localhost:8080/nft-auction/arts/display?fileName=" +
		// vo.getArtFileName();

		if (result == 1) { // ????????? ????????????!
			// selectAndInsertKip17(mvo, vo, nftTokenId);
			int nicknameUpdate = artService.updateNickname(vo.getMemberId());
			logger.info(nicknameUpdate + "??? nickname ??????. art ?????? ????????????");
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

		// KIP-17 ?????? alias ???????????? ??????(???????????? ?????? ??????)
		String alias = mvo.getMemberId();
		Kip17ContractInfoResponse contractRes = caver.kas.kip17.getContract(alias);
		System.out.println("KIP-17 ?????? " + alias + " ????????? ???????????? ?????? : " + contractRes);
		System.out.println("KIP-17 alias(memberid)??? ??????????????? ???????????? ???????????? : " + contractRes.getAddress());

		String contractAddress = contractRes.getAddress();
		String tokenId = nftTokenId;
		System.out.println("?????? : " + contractAddress + ", ?????? ????????? : " + tokenId);

	} // method end ()

	public String getRamdomPassword(int len) {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		int idx = 0;
		StringBuffer sb = new StringBuffer();
		logger.info("charSet.length : " + charSet.length);

		for (int i = 0; i < len; i++) {
			idx = (int) (charSet.length * Math.random());
			// 9 * ????????? ????????? Int??? ?????? (???????????????)
			logger.info("idx : " + idx);
			sb.append(charSet[idx]);
		}

		return sb.toString();
	} // end getRamdomPassword()

	@GetMapping("/arts/upload-ajax")
	public void uploadAjaxGET() {
		logger.info("uploadAjaxGET() ??????");
	} // end uploadAjaxGET()

	// ????????? ?????????.
	@PostMapping("/arts/upload-ajax")
	@ResponseBody
	public String uploadAjaxPOST(MultipartFile[] files) throws Exception {
		logger.info("uploadAjaxPOST() ?????? : file = "+files[0]);
		// ?????? ????????? ??????
		/*
		 * String result = null; // ?????? ?????? ??? ????????? ????????? ?????? result =
		 * FileUploadUtil.saveUploadedFile(uploadPath, files[0].getOriginalFilename(),
		 * files[0].getBytes()); logger.info("fileuploadutil ??? result ?????? : " + result);
		 * // 11.17 ?????? if (result == null) { return new ResponseEntity<String>("fail",
		 * HttpStatus.OK); } else { return new ResponseEntity<String>(result,
		 * HttpStatus.OK); }
		 */
		logger.info("originalName: " + files[0].getOriginalFilename()); //???????????? ???????????????
		String uploadpath = "FileUpload"; //s3 ????????????

		ResponseEntity<String> img_path = new ResponseEntity<String>(
				UploadFileUtils.uploadFile(uploadpath, files[0].getOriginalFilename(), files[0].getBytes()),
				HttpStatus.CREATED);
		
		String ImagePath = (String) img_path.getBody();
		logger.info(ImagePath);
		
		String artFileName=uploadpath+ImagePath;
		logger.info("db ?????? ????????? : "+artFileName);
		//s3??? ????????? ????????????(????????????)??? ??????. db??? ????????? FileName
		return artFileName;
	} // end uploadAjaxPOST()

	@GetMapping("/arts/display")
	public ResponseEntity<byte[]> display(String fileName) throws Exception {
		// ???????????? ?????????. byte????????? ????????? ??????
		logger.info("display() ??????");
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;

		// ?????? ?????????
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
		logger.info(extension);

		if (extension.equals("gif")) { // ???????????? gif??? ??????
			fileName = fileName.replace("s_", "");
		}

		String filePath = uploadPath + fileName;
		in = new FileInputStream(filePath); // ????????????

		// ????????????(response header) org.springframework.http??? Content-Type ??????
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaUtil.getMediaType(extension));

		// ????????? ??????
		entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), // ???????????? ?????? ?????????
				httpHeaders, // ????????????
				HttpStatus.OK);

		return entity;
	} // end display()

	@GetMapping("/arts/detail")
	public void detail(Model model, Integer artNo, Integer page, HttpServletRequest request) throws ApiException,
			NoSuchMethodException, InstantiationException, ClassNotFoundException, IllegalAccessException,
			InvocationTargetException, IOException, TransactionException, InterruptedException, ParseException {
		logger.info("detail() ?????? : artNo = " + artNo + ", page = " + page);
		// ????????? ?????????
		String ip = request.getRemoteAddr();
		HttpSession session=request.getSession();
		String memberId=(String) session.getAttribute("memberId");
		int count = 0;
		logger.info("ip : " + ip);
		count++;
		int updateView = artService.updateView(artNo, count);
		logger.info(updateView + "??? ????????? ????????????");

		Map<String, Object> readMap = artService.readArtNo(artNo);
		ArtVO vo = (ArtVO) readMap.get("vo");
		logger.info("ArtVO ?????? : " + vo.toString());
		
		if (readMap.containsKey("maxMoney")) { // maxMoney??? ?????????
			int maxMoney = (Integer) readMap.get("maxMoney");
			model.addAttribute("maxMoney", maxMoney);
		}

		PaymentVO pvo = artService.readPayResult(artNo);
		String payResult = "";
		if (pvo != null) { // ????????? ????????????
			payResult = "fail";
		} else {
			payResult = "success";
		}
		
		if(memberId!=null) {
			MemberVO mo = memberService.readByMemberId(memberId);
			model.addAttribute("mo", mo);			
		}else {
			model.addAttribute("mo", null);	
		}
		
		model.addAttribute("payResult", payResult);
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
	} // end detail()

	@GetMapping("/arts/update")
	public void updateGET(Model model, Integer artNo, HttpServletRequest request) {
		logger.info("updateGET() ?????? : artNo = " + artNo);

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
		logger.info("updatePOST() ?????? : vo = " + vo.toString());
		
		
		
		int result = artService.updateArt(vo);

		if (result == 1) {
			logger.info("update ??????");
			reAttr.addFlashAttribute("updateResult", "success");
			return "redirect:/arts/detail?artNo=" + vo.getArtNo();
		} else {
			logger.info("update ??????");
			reAttr.addFlashAttribute("updateResult", "fail");
			return "redirect:/arts/update?artNo=" + vo.getArtNo();
		}
	} // end updatePOST()

	@GetMapping("arts/delete")
	public String delete(int artNo, RedirectAttributes reAttr, HttpServletRequest request) throws Exception {
		logger.info("delete ??????");

		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		MemberVO vo = memberService.readByMemberId(memberId);
		
		ArtVO avo = artService.readArtno(artNo);
		// wishlistService.delete(memberId, avo.getArtName()); // ?????? ????????? wishlist ?????? ??????
		if(memberId.equals(vo.getMemberId())) {
			int result=artService.deleteArt(artNo);
			if (result == 1) {
				deleteKip17(vo, avo, request);
				s3.fileDelete(bucketName, avo.getArtFileName());
				logger.info("?????? ??????");
				reAttr.addFlashAttribute("deleteResult", "success");
				return "redirect:/main";
			} else {
				reAttr.addFlashAttribute("deleteResult", "fail");
				return "redirect:/arts/detail?artNo=" + artNo;
			}	
		}else { //???????????? ?????? ??????
			reAttr.addFlashAttribute("deleteResult", "fail"); 
			return "redirect:/main";
		}
	} // end deletePOST()

	private void deleteKip17(MemberVO vo, ArtVO avo, HttpServletRequest request) throws ApiException {
		CaverExtKAS caver = new CaverExtKAS();
		caver.initKASAPI("1001", "KASKEMNC1D88Q7GH1TNVLZHR", "HOkyolJgnqehhk44F9ecIcbHCN6m-HBk-ARWMOYt");

		String contractAddress = vo.getMemberId(); // alias??? ??????. ???????????? memberId.
		String from = vo.getMemberAccount(); // ???????????? account
		String tokenId = avo.getArtTokenId(); // ?????? ????????? ??????????????? nft ???????????????.
		System.out.println("alias : " + contractAddress + "from : " + from + "tokenId" + tokenId);

		Kip17TransactionStatusResponse res = caver.kas.kip17.burn(contractAddress, from, tokenId);

		System.out.println("KIP-17 ?????? ?????? response result " + res);

	}

	@PostMapping("arts/winning")
	@ResponseBody
	public List<ArtVO> winningBid(String memberId) {
		logger.info("winningBid() ?????? : memberId = " + memberId);
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
		logger.info("declareGET() ??????");
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
		logger.info("declarePOST() ??????");
		int result = sendMail(memberEmail, artNo, declareContent);
			if(result == 1) {
				logger.info(result+"??? ?????? ?????? ??????");
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script> alert('????????? ?????????????????????. ???????????????.'); window.close(); </script>");
				reAttr.addFlashAttribute("emailResult", "success");
			} else {
				logger.info("?????? ?????? ??????");	
				PrintWriter out = response.getWriter();
				out.println("<script> alert('?????? ?????? ?????? ?????? ??????????????????'); window.close(); </script>");
				reAttr.addFlashAttribute("emailResult", "fail");
			}
		
	}
	
	// ?????? ??????
	public int sendMail(String memberEmail, int artNo, String declareContent) throws Exception {
		int result = 0;
		String subject = artNo + "??? ?????? ?????? ??????";
		String content = "?????? ?????? : " + declareContent;
		String from = memberEmail;
		String to = "nft.auction.help@gmail.com";
		
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
