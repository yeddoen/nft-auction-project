package project.spring.nft.domain;

import java.util.Date;

public class ArtAuctionVO {
	private int auctionNo;
	private int artNo;
	private String memberId;
	private String memberNickname;
	private int auctionMoney;
	private Date auctionDate;
	private String auctionResult;
	private String artName;
	private int artPrice;
	private String artContent;
	private String artShowDate;
	private String artBasicFee;
	private int artReplyCount, artWishCount, artViewCount;
	private String artFileName;
	
	public ArtAuctionVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArtAuctionVO(int auctionNo, int artNo, String memberId, String memberNickname, int auctionMoney,
			Date auctionDate, String auctionResult, String artName, int artPrice, String artContent, String artShowDate,
			String artBasicFee, int artReplyCount, int artWishCount, int artViewCount, String artFileName) {
		super();
		this.auctionNo = auctionNo;
		this.artNo = artNo;
		this.memberId = memberId;
		this.memberNickname = memberNickname;
		this.auctionMoney = auctionMoney;
		this.auctionDate = auctionDate;
		this.auctionResult = auctionResult;
		this.artName = artName;
		this.artPrice = artPrice;
		this.artContent = artContent;
		this.artShowDate = artShowDate;
		this.artBasicFee = artBasicFee;
		this.artReplyCount = artReplyCount;
		this.artWishCount = artWishCount;
		this.artViewCount = artViewCount;
		this.artFileName = artFileName;
	}

	public int getAuctionNo() {
		return auctionNo;
	}

	public void setAuctionNo(int auctionNo) {
		this.auctionNo = auctionNo;
	}

	public int getArtNo() {
		return artNo;
	}

	public void setArtNo(int artNo) {
		this.artNo = artNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public int getAuctionMoney() {
		return auctionMoney;
	}

	public void setAuctionMoney(int auctionMoney) {
		this.auctionMoney = auctionMoney;
	}

	public Date getAuctionDate() {
		return auctionDate;
	}

	public void setAuctionDate(Date auctionDate) {
		this.auctionDate = auctionDate;
	}

	public String getAuctionResult() {
		return auctionResult;
	}

	public void setAuctionResult(String auctionResult) {
		this.auctionResult = auctionResult;
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

	public String getArtShowDate() {
		return artShowDate;
	}

	public void setArtShowDate(String artShowDate) {
		this.artShowDate = artShowDate;
	}

	public String getArtBasicFee() {
		return artBasicFee;
	}

	public void setArtBasicFee(String artBasicFee) {
		this.artBasicFee = artBasicFee;
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

	public String getArtFileName() {
		return artFileName;
	}

	public void setArtFileName(String artFileName) {
		this.artFileName = artFileName;
	}

	@Override
	public String toString() {
		return "ArtAuctionVO [auctionNo=" + auctionNo + ", artNo=" + artNo + ", memberId=" + memberId
				+ ", memberNickname=" + memberNickname + ", auctionMoney=" + auctionMoney + ", auctionDate="
				+ auctionDate + ", auctionResult=" + auctionResult + ", artName=" + artName + ", artPrice=" + artPrice
				+ ", artContent=" + artContent + ", artShowDate=" + artShowDate + ", artBasicFee=" + artBasicFee
				+ ", artReplyCount=" + artReplyCount + ", artWishCount=" + artWishCount + ", artViewCount="
				+ artViewCount + ", artFileName=" + artFileName + "]";
	}
	
}
