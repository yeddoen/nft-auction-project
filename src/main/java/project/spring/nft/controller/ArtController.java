package project.spring.nft.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.web3j.protocol.exceptions.TransactionException;

import project.spring.nft.domain.ArtVO;
import project.spring.nft.pageutil.PageCriteria;
import project.spring.nft.pageutil.PageMaker;
import project.spring.nft.service.ArtService;
import project.spring.nft.util.FileUploadUtil;
import project.spring.nft.util.KAS;
import project.spring.nft.util.MediaUtil;
import xyz.groundx.caver_ext_kas.CaverExtKAS;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.ApiException;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.api.kip17.model.Kip17TransactionStatusResponse;

@Controller
public class ArtController {
	// 로거
	private static final Logger logger = LoggerFactory.getLogger(ArtController.class);

	@Resource(name = "uploadPath")
	private String uploadPath;

	@Autowired
	private ArtService artService;

	@GetMapping("main")
	public void main(Model model, Integer page, Integer numsPerPage) {
		logger.info("readMain() 호출");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);
		currentAllList(model, page, numsPerPage);
	} // end main()

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
		model.addAttribute("pageMaker", pageMaker);

		return "main";
	} // end search()

	@GetMapping("/arts/register")
	public void registerGET() {
		logger.info("registerGET() 호출");
	} // end registerGET()

	@PostMapping("/arts/register")
	public String registerPOST(ArtVO vo, RedirectAttributes reAttr)
			throws ApiException, NoSuchMethodException, InstantiationException, ClassNotFoundException,
			IllegalAccessException, InvocationTargetException, IOException, TransactionException, InterruptedException {
		logger.info("registerPOST() 호출 : vo = " + vo.toString());
		int result = artService.createArt(vo);
		logger.info(result + "행 삽입");

		// selectAndDeployKip17(vo);

		// 메타데이터와 파일을 json으로 저장.
		String artName = vo.getArtName();
		String artContent = vo.getArtContent();
		String memberId = vo.getMemberId();

		// 빠른 구현을 위해 일단 절대경로로했음. 카카오 일좀 해라
		String artFileName = "http:/localhost:8080/nft-auction/arts/display?fileName=" + vo.getArtFileName();

		// 이제 이 metadata.json을 uri에 넣고 mint 하기.
		mintKip17(vo);

		if (result == 1) { // 등록에 성공하면!
			int nicknameUpdate = artService.updateNickname(vo.getMemberId());
			logger.info(nicknameUpdate + "개 nickname 등록. art 등록 최종완료");
			reAttr.addFlashAttribute("registerResult", "success");

			return "redirect:/main";
		} else {
			return "redirect:/arts/register";
		}
	} // end registerPOST()

	public void mintKip17(ArtVO vo)
			throws NoSuchMethodException, IOException, InstantiationException, ClassNotFoundException,
			IllegalAccessException, InvocationTargetException, TransactionException, ApiException {
		
		CaverExtKAS caver = new CaverExtKAS();
		caver.initKASAPI("1001", "KASKEMNC1D88Q7GH1TNVLZHR", "HOkyolJgnqehhk44F9ecIcbHCN6m-HBk-ARWMOYt");

		String to = vo.getMemberAccount(); // 사용자의 kaikas 지갑.
		String id = "0x" + getRamdomPassword(4); // 토큰의 아이디 그냥 점차 증가하는거로 만들까.
		String uri = vo.getArtJsonUri(); // 메타데이터주소.
		logger.info("jsonuri : " + vo.getArtJsonUri()); // DB에는 안넣음!
		String contractAlias = vo.getMemberId();

		Kip17TransactionStatusResponse response = caver.kas.kip17.mint(contractAlias, to, id, uri);

		System.out.println("KIP-17 토큰 발행 response result " + response);
		response.getTransactionHash();

	} // end mint()

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
	public void detail(Model model, Integer artNo, Integer page, HttpServletRequest request) {
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
		if (readMap.containsKey("maxMoney")) { // maxMoney가 있으면
			int maxMoney = (Integer) readMap.get("maxMoney");
			model.addAttribute("maxMoney", maxMoney);
		}
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
	} // end detail()

	@GetMapping("/arts/update")
	public void updateGET(Model model, Integer artNo) {
		logger.info("updateGET() 호출 : artNo = " + artNo);
		Map<String, Object> readMap = artService.readArtNo(artNo);
		ArtVO vo = (ArtVO) readMap.get("vo");
		model.addAttribute("vo", vo);
	} // end updateGET()

	@PostMapping("arts/update")
	public String updatePOST(ArtVO vo, RedirectAttributes reAttr) {
		logger.info("updatePOST() 호출 : vo = " + vo.toString());
		int result = artService.updateArt(vo);

		if (result == 1) {
			reAttr.addFlashAttribute("updateResult", "success");
			return "redirect:/arts/detail?artNo=" + vo.getArtNo();
		} else {
			reAttr.addFlashAttribute("updateResult", "fail");
			return "redirect:/arts/update?artNo=" + vo.getArtNo();
		}
	} // end updatePOST()

	@GetMapping("arts/delete")
	public String deletePOST(int artNo, RedirectAttributes reAttr) throws Exception {
		logger.info("deletePOST() 호출");
		int result = artService.deleteArt(artNo);

		KAS kas = new KAS();
		kas.selecteNftKip17();

		if (result == 1) {
			reAttr.addFlashAttribute("deleteResult", "success");
			return "redirect:/main";
		} else {
			reAttr.addFlashAttribute("deleteResult", "fail");
			return "redirect:/arts/detail?artNo=" + artNo;
		}
	} // end deletePOST()

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
}
