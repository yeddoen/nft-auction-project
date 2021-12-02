package project.spring.nft.service;

import java.util.Map;

import project.spring.nft.domain.NftVO;

public interface NftService {
	int create(NftVO vo);

	NftVO read(String nftContractAlias, String nftTokenId);

	int delete(String nftContractAlias, String nftTokenId);

	int update(NftVO vo);

} // end class
