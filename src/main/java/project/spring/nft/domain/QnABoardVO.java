package project.spring.nft.domain;

import java.util.Date;

public class QnABoardVO {
	private int qnaboardNo; // 게시글 번호
	private String qnaboardTitle; // 게시글 제목
	private String qnaboardContent; // 게시글 내용
	private String memberId; // 사용자 아이디
	private String memberNickname; // 사용자 닉네임
	private Date qnaboardDate; // 등록 날짜
	
	public QnABoardVO() {}

	public QnABoardVO(int qnaboardNo, String qnaboardTitle, String qnaboardContent, String memberId,
			String memberNickname, Date qnaboardDate) {
		super();
		this.qnaboardNo = qnaboardNo;
		this.qnaboardTitle = qnaboardTitle;
		this.qnaboardContent = qnaboardContent;
		this.memberId = memberId;
		this.memberNickname = memberNickname;
		this.qnaboardDate = qnaboardDate;
	}

	public int getQnaboardNo() {
		return qnaboardNo;
	}

	public void setQnaboardNo(int qnaboardNo) {
		this.qnaboardNo = qnaboardNo;
	}

	public String getQnaboardTitle() {
		return qnaboardTitle;
	}

	public void setQnaboardTitle(String qnaboardTitle) {
		this.qnaboardTitle = qnaboardTitle;
	}

	public String getQnaboardContent() {
		return qnaboardContent;
	}

	public void setQnaboardContent(String qnaboardContent) {
		this.qnaboardContent = qnaboardContent;
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

	public Date getQnaboardDate() {
		return qnaboardDate;
	}

	public void setQnaboardDate(Date qnaboardDate) {
		this.qnaboardDate = qnaboardDate;
	}

	@Override
	public String toString() {
		return "QnABoardVO [qnaboardNo=" + qnaboardNo + ", qnaboardTitle=" + qnaboardTitle + ", qnaboardContent="
				+ qnaboardContent + ", memberId=" + memberId + ", memberNickname=" + memberNickname + ", qnaboardDate="
				+ qnaboardDate + "]";
	}

}
