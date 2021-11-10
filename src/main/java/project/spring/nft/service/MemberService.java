package project.spring.nft.service;

import project.spring.nft.domain.MemberVO;

public interface MemberService {
	//회원정보 등록
	int createMember(MemberVO vo);
	
	//아이디 중복체크
	int readIdCheck(String memberId);
	
	//회원정보 조회
	MemberVO readByMemberId(String memberId);
	
	//로그인 아이디 비밀번호 조회
	MemberVO readLogin(String memberId, String memberPassword);
	
	//비밀번호 수정
	int updateMemberPassword(String memberId, String updatePassword);
	
	//회원정보 수정
	int updateMemberInfo(MemberVO vo);
	
	//회원탈퇴
	int deleteMember(String memberId, String memberPassword);
}
