package project.spring.nft.domain;

import java.util.Date;

public class ArtReplyVO {
	private int artReplyNo; // 댓글 번호
	private int artNo; // 댓글이 속한 글 번호
	private int artReplyParentNo; // 대댓글이 속한 댓글 번호
	private String artReplyContent; // 댓글 내용
	private Date artReplyDate; // 댓글 작성 날짜
	private String memberId; // 댓글 작성자 아이디
	private String memberNickname; // 댓글 작성자 닉네임
	
	public ArtReplyVO() {}

	public ArtReplyVO(int artReplyNo, int artNo, int artReplyParentNo, String artReplyContent, Date artReplyDate,
			String memberId, String memberNickname) {
		super();
		this.artReplyNo = artReplyNo;
		this.artNo = artNo;
		this.artReplyParentNo = artReplyParentNo;
		this.artReplyContent = artReplyContent;
		this.artReplyDate = artReplyDate;
		this.memberId = memberId;
		this.memberNickname = memberNickname;
	}

	public int getArtReplyNo() {
		return artReplyNo;
	}

	public void setArtReplyNo(int artReplyNo) {
		this.artReplyNo = artReplyNo;
	}

	public int getArtNo() {
		return artNo;
	}

	public void setArtNo(int artNo) {
		this.artNo = artNo;
	}

	public int getArtReplyParentNo() {
		return artReplyParentNo;
	}

	public void setArtReplyParentNo(int artReplyParentNo) {
		this.artReplyParentNo = artReplyParentNo;
	}

	public String getArtReplyContent() {
		return artReplyContent;
	}

	public void setArtReplyContent(String artReplyContent) {
		this.artReplyContent = artReplyContent;
	}

	public Date getArtReplyDate() {
		return artReplyDate;
	}

	public void setArtReplyDate(Date artReplyDate) {
		this.artReplyDate = artReplyDate;
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

	@Override
	public String toString() {
		return "ArtReplyVO [artReplyNo=" + artReplyNo + ", artNo=" + artNo + ", artReplyParentNo=" + artReplyParentNo
				+ ", artReplyContent=" + artReplyContent + ", artReplyDate=" + artReplyDate + ", memberId=" + memberId
				+ ", memberNickname=" + memberNickname + "]";
	}
}
