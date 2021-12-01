package project.spring.nft.persistence;

import java.util.List;

import project.spring.nft.domain.MemberVO;

//회원정보 관련
public interface MemberDAO {
	//회원 등록
	int insertMember(MemberVO vo);
	
	//아이디 중복체크
	int selectIdCheck(String memberId);
	
	//회원 정보 조회
	MemberVO selectByMemberId(String memberId); // memberId로 바꿈
	
	//로그인 아이디 조회
	MemberVO selectLogin(String memberId);
	
	//비밀번호 수정
	int updateMemberPassword(String memberId, String updatePassword); // memberId로 바꿈
	
	//회원정보 수정
	int updateMemberInfo(MemberVO vo); // memberId로 바꿈
	
	//회원탈퇴
	int deleteMember(String memberId, String memberPassword); // memberId로 바꿈
	
	//전화번호로 아이디찾기(정보 중복이 있을 수 있어 list)
	List<String> findIdasPhone(String memberName, String memberPhone);
	
	//이메일로 아이디찾기
	List<String> findIdasEmail(String memberName, String memberEmail);
	
	//비밀번호 찾기
	MemberVO findPasswordasEmail(String memberId, String memberEmail);
}