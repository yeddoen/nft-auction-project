package project.spring.nft.domain;

// 위시리스트 VO
public class WishlistVO {
	private int wishNo;
	private String memberId;
	private String artName;
	private int artPrice;
	private String artFileName;
	private int artNo;

	public WishlistVO() {
	}

	public WishlistVO(int wishNo, String memberId, String artName, int artPrice, String artFileName, int artNo) {
		super();
		this.wishNo = wishNo;
		this.memberId = memberId;
		this.artName = artName;
		this.artPrice = artPrice;
		this.artFileName = artFileName;
		this.artNo = artNo;
	}

	public int getWishNo() {
		return wishNo;
	}

	public void setWishNo(int wishNo) {
		this.wishNo = wishNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
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

	public String getArtFileName() {
		return artFileName;
	}

	public void setArtFileName(String artFileName) {
		this.artFileName = artFileName;
	}
	
	public int getArtNo() {
		return artNo;
	}

	public void setArtNo(int artNo) {
		this.artNo = artNo;
	}
	
	

	@Override
	public String toString() {
		return "WishlistVO [wishNo=" + wishNo + ", memberId=" + memberId + ", artName=" + artName + ", artPrice="
				+ artPrice + ", artFileName=" + artFileName + ", artNo=" + artNo + "]";
	}

} // end class
