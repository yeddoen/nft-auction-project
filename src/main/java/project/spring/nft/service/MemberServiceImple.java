package project.spring.nft.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.nft.domain.MemberVO;
import project.spring.nft.persistence.MemberDAO;

@Service
public class MemberServiceImple implements MemberService {
	private static final Logger logger=
			LoggerFactory.getLogger(MemberServiceImple.class);
	
	@Autowired
	private MemberDAO dao;
	
	@Override
	public int createMember(MemberVO vo) {
		logger.info("createMemeber() 호출 : vo = "+vo.toString());
		return dao.insertMember(vo);
	}
	
	@Override
	public int readIdCheck(String memberId) {
		logger.info("readIdCheck() 호출");
		return dao.selectIdCheck(memberId);
	}
	
	@Override
	public MemberVO readByMemberId(String memberId) {
		logger.info("readByMemberId() 호출");
		return dao.selectByMemberId(memberId);
	}
	
	@Override
	public MemberVO readLogin(String memberId) {
		logger.info("readLogin() 호출");
		return dao.selectLogin(memberId);
	}

	@Override
	public int updateMemberPassword(String memberId, String updatePassword) {
		logger.info("updateMemberPassword() 호출 : updatePassword = "+updatePassword);
		return dao.updateMemberPassword(memberId, updatePassword);
	}

	@Override
	public int updateMemberInfo(MemberVO vo) {
		logger.info("updateMemberInfo() 호출 : vo ="+vo.toString());
		return dao.updateMemberInfo(vo);
	}

	@Override
	public int deleteMember(String memberId, String memberPassword) {
		logger.info("deleteMember() 호출 : memberNo = "+memberId);
		return dao.deleteMember(memberId, memberPassword);
	}
	
	@Override
	public List<String> findIdasPhone(String memberName, String memberPhone) {
		logger.info("findIdasPhone() 호출");
		return dao.findIdasPhone(memberName, memberPhone);
	}
	
	@Override
	public List<String> findIdasEmail(String memberName, String memberEmail) {
		logger.info("findIdasEmail() 호출");
		return dao.findIdasEmail(memberName, memberEmail);
	}
	
	@Override
	public MemberVO findPasswordasEmail(String memberId, String memberEmail) {
		logger.info("findPasswordasEmail() 호출");
		return dao.findPasswordasEmail(memberId, memberEmail);
	}

} // end class
