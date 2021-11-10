package project.spring.nft.domain;

import java.util.Date;

public class AuctionVO {
	private int auctionNo;
	private int artNo;
	private String memberId;
	private String memberNickname;
	private int auctionMoney;
	private Date auctionDate;
	private String auctionResult; //T,F만
	
	public AuctionVO() {}

	public AuctionVO(int auctionNo, int artNo, String memberId, String memberNickname, int auctionMoney,
			Date auctionDate, String auctionResult) {
		super();
		this.auctionNo = auctionNo;
		this.artNo = artNo;
		this.memberId = memberId;
		this.memberNickname = memberNickname;
		this.auctionMoney = auctionMoney;
		this.auctionDate = auctionDate;
		this.auctionResult = auctionResult;
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

	@Override
	public String toString() {
		return "AuctionVO [auctionNo=" + auctionNo + ", artNo=" + artNo + ", memberId=" + memberId + ", memberNickname="
				+ memberNickname + ", auctionMoney=" + auctionMoney + ", auctionDate=" + auctionDate
				+ ", auctionResult=" + auctionResult + "]";
	}
	
}	
