package project.spring.nft.domain;

import java.sql.Timestamp;
import java.util.Date;

// db는 timestamp로 그냥 넣장.. 히히
public class NftVO {
	private int nftNo;
	private String nftTokenId;
	private String nftContractAlias;
	private String nftOwner;
	private String nftPreviousOwner;
	private String nftJsonUri;
	private String nftTxHash;
	private Timestamp nftCreatedAt;
	private Timestamp nftUpdatedAt;

	public NftVO() {
	}

	public NftVO(int nftNo, String nftTokenId, String nftContractAlias, String nftOwner, String nftPreviousOwner,
			String nftJsonUri, String nftTxHash, Timestamp nftCreatedAt, Timestamp nftUpdatedAt) {
		super();
		this.nftNo = nftNo;
		this.nftTokenId = nftTokenId;
		this.nftContractAlias = nftContractAlias;
		this.nftOwner = nftOwner;
		this.nftPreviousOwner = nftPreviousOwner;
		this.nftJsonUri = nftJsonUri;
		this.nftTxHash = nftTxHash;
		this.nftCreatedAt = nftCreatedAt;
		this.nftUpdatedAt = nftUpdatedAt;
	}

	public int getNftNo() {
		return nftNo;
	}

	public void setNftNo(int nftNo) {
		this.nftNo = nftNo;
	}

	public String getNftTokenId() {
		return nftTokenId;
	}

	public void setNftTokenId(String nftTokenId) {
		this.nftTokenId = nftTokenId;
	}

	public String getNftContractAlias() {
		return nftContractAlias;
	}

	public void setNftContractAlias(String nftContractAlias) {
		this.nftContractAlias = nftContractAlias;
	}

	public String getNftOwner() {
		return nftOwner;
	}

	public void setNftOwner(String nftOwner) {
		this.nftOwner = nftOwner;
	}

	public String getNftPreviousOwner() {
		return nftPreviousOwner;
	}

	public void setNftPreviousOwner(String nftPreviousOwner) {
		this.nftPreviousOwner = nftPreviousOwner;
	}

	public String getNftJsonUri() {
		return nftJsonUri;
	}

	public void setNftJsonUri(String nftJsonUri) {
		this.nftJsonUri = nftJsonUri;
	}

	public String getNftTxHash() {
		return nftTxHash;
	}

	public void setNftTxHash(String nftTxHash) {
		this.nftTxHash = nftTxHash;
	}

	public Timestamp getNftCreatedAt() {
		return nftCreatedAt;
	}

	public void setNftCreatedAt(Timestamp nftCreatedAt) {
		this.nftCreatedAt = nftCreatedAt;
	}

	public Timestamp getNftUpdatedAt() {
		return nftUpdatedAt;
	}

	public void setNftUpdatedAt(Timestamp nftUpdatedAt) {
		this.nftUpdatedAt = nftUpdatedAt;
	}

	@Override
	public String toString() {
		return "NftVO [nftNo=" + nftNo + ", nftTokenId=" + nftTokenId + ", nftContractAlias=" + nftContractAlias
				+ ", nftOwner=" + nftOwner + ", nftPreviousOwner=" + nftPreviousOwner + ", nftJsonUri=" + nftJsonUri
				+ ", nftTxHash=" + nftTxHash + ", nftCreatedAt=" + nftCreatedAt + ", nftUpdatedAt=" + nftUpdatedAt
				+ "]";
	}
	
	
	

} // end class
