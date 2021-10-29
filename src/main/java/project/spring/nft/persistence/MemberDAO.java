package project.spring.nft.persistence;

import project.spring.nft.domain.MemberVO;

//회원정보 관련
public interface MemberDAO {
	//회원 등록
	int insertMember(MemberVO vo);
	
	//아이디 중복체크
	int selectIdCheck(String memberId);
	
	//회원 정보 조회
	MemberVO selectByMemberNo(int memberNo);
	
	//로그인 아이디 비밀번호 조회
	MemberVO selectLogin(String memberId, String memberPassword);
	
	//비밀번호 수정
	int updateMemberPassword(int memberNo, String updatePassword);
	
	//회원정보 수정
	int updateMemberInfo(MemberVO vo);
	
	//회원탈퇴
	int deleteMember(int memberNo, String memberPassword);
}
