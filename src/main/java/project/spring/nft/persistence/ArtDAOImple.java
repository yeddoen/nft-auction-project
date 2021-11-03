package project.spring.nft.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.nft.domain.ArtVO;

@Repository
public class ArtDAOImple implements ArtDAO {
	private static Logger logger=
			LoggerFactory.getLogger(ArtDAOImple.class);
	private static final String NAMESPACE="project.spring.nft.ArtMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertArt(ArtVO vo) {
		//회원번호, 작품명, 즉납가, 작품설명, 이미지파일이름(썸네일) 등록하기
		//TODO nft관련 정보등록은 추후 추가
		logger.info("insertArt() 호출");
		return sqlSession.insert(NAMESPACE+".insert", vo);
	}
	
	@Override
	public int updateNickName(String memberId) {
		logger.info("updateNickName() 호출 : memberId = "+memberId);
		return sqlSession.update(NAMESPACE+".update_nickname", memberId);
	}
	
	@Override
	public List<ArtVO> selectCurrentArt() {
		logger.info("selectCurrentArt() 호출");
		return sqlSession.selectList(NAMESPACE+".select_all_by_no");
	}

	@Override
	public List<ArtVO> selectWishArt() {
		logger.info("selectWishArt() 호출");
		return sqlSession.selectList(NAMESPACE+".select_all_by_wish");
	}

	@Override
	public List<ArtVO> selectViewArt() {
		logger.info("selectViewArt() 호출");
		return sqlSession.selectList(NAMESPACE+".select_all_by_view");
	}

	@Override
	public List<ArtVO> selectArtName(String keyword) {
		logger.info("selectArtName() 호출");
		keyword=keyword+"%";
		return sqlSession.selectList(NAMESPACE+".select_by_art_name", keyword);
	}

	@Override
	public List<ArtVO> selectMemberNo(String keyword) {
		logger.info("selectMemberNo() 호출");
		keyword=keyword+"%";
		return sqlSession.selectList(NAMESPACE+".select_by_member_no", keyword);
	}

}
