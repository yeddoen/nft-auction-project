package project.spring.nft.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.nft.domain.NftVO;
import project.spring.nft.persistence.NftDAO;

@Service
public class NftServiceImple implements NftService {
	private static final Logger logger = LoggerFactory.getLogger(NftServiceImple.class);

	@Autowired
	private NftDAO nftDAO;

	@Override
	public int create(NftVO vo) {
		logger.info("create 호출 : vo = " + vo.toString());
		return nftDAO.insertNft(vo);
	}

	@Override
	public NftVO read(String nftContractAlias, String nftTokenId) {
		logger.info("read 호출()");
		return nftDAO.selectNft(nftContractAlias, nftTokenId);
	}

	@Override
	public int delete(String nftContractAlias, String nftTokenId) {
		logger.info("delete 호출()");
		return nftDAO.deleteNft(nftContractAlias, nftTokenId);
	}

	@Override
	public int update(NftVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

} // end class
