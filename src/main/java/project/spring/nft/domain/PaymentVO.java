package project.spring.nft.domain;

import java.util.Date;

public class PaymentVO {
	private int paymentNo;
	private String artName;
	private int artNo;
	private String memberId;
	private int artPrice;
	private Date paymentDate;
	private String merchantUid;
	
	public PaymentVO() {}

	public PaymentVO(int paymentNo, String artName, int artNo, String memberId, int artPrice, Date paymentDate, String merchantUid) {
		super();
		this.paymentNo = paymentNo;
		this.artName = artName;
		this.artNo = artNo;
		this.memberId = memberId;
		this.artPrice = artPrice;
		this.paymentDate = paymentDate;
		this.merchantUid = merchantUid;
	}

	public int getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(int paymentNo) {
		this.paymentNo = paymentNo;
	}

	public String getArtName() {
		return artName;
	}

	public void setArtName(String artName) {
		this.artName = artName;
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

	public int getArtPrice() {
		return artPrice;
	}

	public void setArtPrice(int artPrice) {
		this.artPrice = artPrice;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	public String getMerchantUid() {
		return merchantUid;
	}
	
	public void setMerchantUid(String merchantUid) {
		this.merchantUid = merchantUid;
	}
	

	@Override
	public String toString() {
		return "PaymentVO [paymentNo=" + paymentNo + ", artName=" + artName + ", artNo=" + artNo + ", memberId="
				+ memberId + ", artPrice=" + artPrice + ", paymentDate=" + paymentDate + ", merchantUid=" + merchantUid + "]";
	}	
	
}
