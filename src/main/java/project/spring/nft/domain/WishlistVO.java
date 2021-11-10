package project.spring.nft.domain;

// 위시리스트 VO
public class WishlistVO {
	private int wishNo;
	private String memberId;
	private String artName;
	private int artPrice;
	private String artFileName;

	public WishlistVO() {
	}

	public WishlistVO(int wishNo, String memberId, String artName, int artPrice, String artFileName) {
		super();
		this.wishNo = wishNo;
		this.memberId = memberId;
		this.artName = artName;
		this.artPrice = artPrice;
		this.artFileName = artFileName;
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

	@Override
	public String toString() {
		return "WishlistVO [wishNo=" + wishNo + ", memberId=" + memberId + ", artName=" + artName + ", artPrice="
				+ artPrice + ", artFileName=" + artFileName + "]";
	}

} // end class
