package project.spring.nft.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.nft.domain.MemberVO;

@Repository
public class MemberDAOImple implements MemberDAO {
	private static final Logger logger=
			LoggerFactory.getLogger(MemberDAOImple.class);
	
	private static final String NAMESPACE="project.spring.nft.MemberMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertMember(MemberVO vo) {
		logger.info("insertMember() 호출");
		return sqlSession.insert(NAMESPACE+".insert", vo);
	}
	
	@Override
	public int selectIdCheck(String memberId) {
		logger.info("selectIdCheck() 호출");
		return sqlSession.selectOne(NAMESPACE+".select_by_check_id", memberId);
	}
	
	@Override
	public MemberVO selectByMemberId(String memberId) {
		logger.info("selectByMemberId() 호출");
		return sqlSession.selectOne(NAMESPACE+".select_by_member_id", memberId);
	}
	
	@Override
	public MemberVO selectLogin(String memberId) {
		logger.info("selectLogin() 호출 : memberId = "+memberId);
		return sqlSession.selectOne(NAMESPACE+".select_by_member_id", memberId);
	}  

	@Override
	public int updateMemberPassword(String memberId, String updatePassword) {
		logger.info("updateMemberPassword() 호출");
		Map<String, String> updateMap=new HashMap<String, String>();
		updateMap.put("memberId", memberId);
		updateMap.put("memberPassword", updatePassword);
		return sqlSession.update(NAMESPACE+".update_pw", updateMap);
	}

	@Override
	public int updateMemberInfo(MemberVO vo) {
		logger.info("updateMemberInfo() 호출");
		return sqlSession.update(NAMESPACE+".update_info", vo);
	}

	@Override
	public int deleteMember(String memberId, String memberPassword) {
		logger.info("deleteMember() 호출");
		Map<String, String> deleteMap=new HashMap<String, String>();
		deleteMap.put("memberId", memberId);
		deleteMap.put("memberPassword", memberPassword);
		return sqlSession.delete(NAMESPACE+".delete", deleteMap);
	}
	
	@Override
	public List<String> findIdasPhone(String memberName, String memberPhone) {
		logger.info("findIdasPhone() 호출");
		Map<String, String> findMap=new HashMap<String, String>();
		findMap.put("memberName", memberName);
		findMap.put("memberPhone", memberPhone);
		return sqlSession.selectList(NAMESPACE+".select_id_find_phone", findMap);
	}
	
	@Override
	public List<String> findIdasEmail(String memberName, String memberEmail) {
		logger.info("findIdasEmail() 호출");
		Map<String, String> findMap=new HashMap<String, String>();
		findMap.put("memberName", memberName);
		findMap.put("memberEmail", memberEmail);
		return sqlSession.selectList(NAMESPACE+".select_id_find_email", findMap);
	}
	
	@Override
	public MemberVO findPasswordasEmail(String memberId, String memberEmail) {
		logger.info("findPasswordasEmail() 호출");
		Map<String, String> findMap=new HashMap<String, String>();
		findMap.put("memberId", memberId);
		findMap.put("memberEmail", memberEmail);
		return sqlSession.selectOne(NAMESPACE+".select_pw_find_email", findMap);
	}
}
