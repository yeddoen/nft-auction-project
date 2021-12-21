package project.spring.nft.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.spring.nft.domain.ArtAuctionVO;
import project.spring.nft.domain.AuctionVO;
import project.spring.nft.domain.MemberVO;
import project.spring.nft.domain.PaymentVO;
import project.spring.nft.persistence.ArtDAO;
import project.spring.nft.persistence.ArtReplyDAO;
import project.spring.nft.persistence.AuctionDAO;
import project.spring.nft.persistence.MemberDAO;
import project.spring.nft.persistence.PaymentDAO;
import project.spring.nft.persistence.QnABoardDAO;
import project.spring.nft.persistence.QnAReplyDAO;
import project.spring.nft.persistence.RefundDAO;
import project.spring.nft.persistence.WishlistDAO;

@Service
public class MemberServiceImple implements MemberService {
	private static final Logger logger=
			LoggerFactory.getLogger(MemberServiceImple.class);
	
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private PaymentDAO paymentDAO;
	@Autowired
	private RefundDAO refundDAO;
	@Autowired
	private AuctionDAO auctionDAO;
	@Autowired
	private ArtDAO artDAO;
	@Autowired
	private WishlistDAO wishlistDAO;
	@Autowired
	private QnABoardDAO qnaboardDAO;
	@Autowired
	private QnAReplyDAO qnareplyDAO;
	@Autowired
	private ArtReplyDAO artreplyDAO;
	
	@Override
	public int createMember(MemberVO vo) {
		logger.info("createMemeber() 호출 : vo = "+vo.toString());
		return memberDAO.insertMember(vo);
	}
	
	@Override
	public int readIdCheck(String memberId) {
		logger.info("readIdCheck() 호출");
		return memberDAO.selectIdCheck(memberId);
	}
	
	@Override
	public MemberVO readByMemberId(String memberId) {
		logger.info("readByMemberId() 호출");
		return memberDAO.selectByMemberId(memberId);
	}
	
	@Override
	public MemberVO readLogin(String memberId) {
		logger.info("readLogin() 호출");
		return memberDAO.selectLogin(memberId);
	}

	@Override
	public int updateMemberPassword(String memberId, String updatePassword) {
		logger.info("updateMemberPassword() 호출 : updatePassword = "+updatePassword);
		return memberDAO.updateMemberPassword(memberId, updatePassword);
	}

	@Override
	public int updateMemberInfo(MemberVO vo) {
		logger.info("updateMemberInfo() 호출 : vo ="+vo.toString());
		return memberDAO.updateMemberInfo(vo);
	}

	@Transactional
	@Override
	public int deleteMember(String memberId, String memberPassword) throws Exception {
		logger.info("deleteMember() 호출 : memberId = "+memberId);
		//작품삭제
		artDAO.deleteAllArt(memberId);
		logger.info(memberId+"의 작품삭제 성공");
		//작성자 댓글 삭제
		artreplyDAO.deleteMemberId(memberId);
		logger.info(memberId+"의 댓글 삭제");
		//작성자 qna, qna댓글 삭제
		qnaboardDAO.deleteMemberId(memberId);
		logger.info(memberId+"의 qna 삭제");
		qnareplyDAO.deleteMemberId(memberId);
		logger.info(memberId+"의 qna댓글 삭제");
		//작성자 경매내역 삭제
		auctionDAO.deleteMemberId(memberId);
		logger.info(memberId+"의 경매기록 삭제");
		//작성자 위시 삭제
		wishlistDAO.deleteArtByMemberId(memberId);
		logger.info(memberId+"의 위시리스트 삭제");
		/*
		 * //작성자 정산 삭제 refundDAO.deleteMemberId(memberId);
		 * logger.info(memberId+"의 정산내역 삭제"); //작성자 결제내역 삭제
		 * paymentDAO.deleteMemberId(memberId); logger.info(memberId+"의 결제내역 삭제");
		 */
		
		return memberDAO.deleteMember(memberId, memberPassword);
	}
	
	@Override
	public List<String> findIdasPhone(String memberName, String memberPhone) {
		logger.info("findIdasPhone() 호출");
		return memberDAO.findIdasPhone(memberName, memberPhone);
	}
	
	@Override
	public List<String> findIdasEmail(String memberName, String memberEmail) {
		logger.info("findIdasEmail() 호출");
		return memberDAO.findIdasEmail(memberName, memberEmail);
	}
	
	@Override
	public MemberVO findPasswordasEmail(String memberId, String memberEmail) {
		logger.info("findPasswordasEmail() 호출");
		return memberDAO.findPasswordasEmail(memberId, memberEmail);
	}
	
	@Override
	public List<PaymentVO> readPaymentAll(String memberId) {
		logger.info("readPaymentAll() 호출");
		return paymentDAO.selectAll(memberId);
	}
	
	@Override
	public Double readProfit(String memberId) {
		logger.info("readProfit() 호출 : memberId = "+memberId);
		return paymentDAO.selectProfit(memberId);
	}
	
	
	@Override
	public Integer readRefund(String memberId) {
		logger.info("readRefund() 호출");
		return refundDAO.select(memberId);
	}
	
	@Override
	public List<ArtAuctionVO> readAuctionAll(String memberId) {
		logger.info("readAuctionAll() 호출");
		return auctionDAO.selectMyAcution(memberId);
	}
	
	@Override
	public List<Integer> readPayResult(String memberId) {
		logger.info("readPayResult() 호출");
		return auctionDAO.selectPay(memberId);
	}
} // end class
