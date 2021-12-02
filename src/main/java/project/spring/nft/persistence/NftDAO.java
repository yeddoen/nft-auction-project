package project.spring.nft.persistence;

import project.spring.nft.domain.NftVO;

public interface NftDAO {
	// Nft에 등록
	int insertNft(NftVO vo);
	// Nft 조회(nft토큰 하나만, 컨트랙트주소와 토큰아이디가 매개변수)
	NftVO selectNft(String nftContractAlias, String nftTokenId);
	// Nft 삭제
	int deleteNft(String nftContractAlias, String nftTokenId);
	// Nft 수정
	int updateNft(NftVO vo);

} // end class
