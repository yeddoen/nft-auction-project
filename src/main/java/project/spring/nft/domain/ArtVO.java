package project.spring.nft.domain;

import java.util.Date;

public class ArtVO {
	private int artNo, memberNo;
	private String artName;
	private int artPrice;
	private String artContent;
	private Date artDate;
	private int artReplyCount, artWishCount, artViewCount;
	
	public ArtVO() {}

	public ArtVO(int artNo, int memberNo, String artName, int artPrice, String artContent, Date artDate,
			int artReplyCount, int artWishCount, int artViewCount) {
		super();
		this.artNo = artNo;
		this.memberNo = memberNo;
		this.artName = artName;
		this.artPrice = artPrice;
		this.artContent = artContent;
		this.artDate = artDate;
		this.artReplyCount = artReplyCount;
		this.artWishCount = artWishCount;
		this.artViewCount = artViewCount;
	}

	public int getArtNo() {
		return artNo;
	}

	public void setArtNo(int artNo) {
		this.artNo = artNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getArtName() {
		return artName;
	}

	public void setArtName(String artName) {
		this.artName = artName;
	}

	public int getArtPrice() {
		return artPrice;
	}

	public void setArtPrice(int artPrice) {
		this.artPrice = artPrice;
	}

	public String getArtContent() {
		return artContent;
	}

	public void setArtContent(String artContent) {
		this.artContent = artContent;
	}

	public Date getArtDate() {
		return artDate;
	}

	public void setArtDate(Date artDate) {
		this.artDate = artDate;
	}

	public int getArtReplyCount() {
		return artReplyCount;
	}

	public void setArtReplyCount(int artReplyCount) {
		this.artReplyCount = artReplyCount;
	}

	public int getArtWishCount() {
		return artWishCount;
	}

	public void setArtWishCount(int artWishCount) {
		this.artWishCount = artWishCount;
	}

	public int getArtViewCount() {
		return artViewCount;
	}

	public void setArtViewCount(int artViewCount) {
		this.artViewCount = artViewCount;
	}

	@Override
	public String toString() {
		return "ArtVO [artNo=" + artNo + ", memberNo=" + memberNo + ", artName=" + artName + ", artPrice=" + artPrice
				+ ", artContent=" + artContent + ", artDate=" + artDate + ", artReplyCount=" + artReplyCount
				+ ", artWishCount=" + artWishCount + ", artViewCount=" + artViewCount + "]";
	}
	
}
