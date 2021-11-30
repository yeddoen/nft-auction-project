package project.spring.nft.persistence;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.nft.domain.NftVO;

@Repository
public class NftDAOImple implements NftDAO {
	private static final Logger logger=
			LoggerFactory.getLogger(NftDAOImple.class);
	private static final String NAMESPACE="project.spring.nft.NftMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertNft(NftVO vo) {
		logger.info("insertNft() 호출");
		return sqlSession.insert(NAMESPACE+".insert", vo);
	}

	@Override
	public NftVO selectNft(String nftContractAlias, String nftTokenId) {
		logger.info("selectNft() 호출");
		Map<String, String> countMap=new HashMap<String, String>();
		countMap.put("nftContractAlias", nftContractAlias);
		countMap.put("nftTokenId", nftTokenId);
		return sqlSession.selectOne(NAMESPACE + ".select", countMap);
	}

	@Override
	public int deleteNft(String nftContractAlias, String nftTokenId) {
		logger.info("selectNft() 호출");
		Map<String, String> countMap=new HashMap<String, String>();
		countMap.put("nftContractAlias", nftContractAlias);
		countMap.put("nftTokenId", nftTokenId);
		return sqlSession.delete(NAMESPACE + ".delete", countMap);
	}

	@Override
	public int updateNft(NftVO vo) {
		
		return 0;
	}

} // end class
