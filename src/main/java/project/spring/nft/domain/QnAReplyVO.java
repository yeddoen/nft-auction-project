package project.spring.nft.domain;

import java.util.Date;

public class QnAReplyVO {
	private int replyNo; // 댓글 번호
	private int qnaboardNo; // 댓글이 속한 글 번호
	private int replyParentNo; // 대댓글이 속한 댓글 번호
	private int replyDepth; // 모댓글=0, 대댓글=1
	private String replyContent; // 댓글 내용
	private String memberId; // 댓글 작성자 아이디
	private String memberNickname; // 댓글 작성자 닉네임
	private Date replyDate; // 댓글 작성 날짜
	
	public QnAReplyVO() {}

	public QnAReplyVO(int replyNo, int qnaboardNo, int replyParentNo, int replyDepth, String replyContent,
			String memberId, String memberNickname, Date replyDate) {
		super();
		this.replyNo = replyNo;
		this.qnaboardNo = qnaboardNo;
		this.replyParentNo = replyParentNo;
		this.replyDepth = replyDepth;
		this.replyContent = replyContent;
		this.memberId = memberId;
		this.memberNickname = memberNickname;
		this.replyDate = replyDate;
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public int getQnaboardNo() {
		return qnaboardNo;
	}

	public void setQnaboardNo(int qnaboardNo) {
		this.qnaboardNo = qnaboardNo;
	}

	public int getReplyParentNo() {
		return replyParentNo;
	}

	public void setReplyParentNo(int replyParentNo) {
		this.replyParentNo = replyParentNo;
	}

	public int getReplyDepth() {
		return replyDepth;
	}

	public void setReplyDepth(int replyDepth) {
		this.replyDepth = replyDepth;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
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

	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	@Override
	public String toString() {
		return "QnAReplyVO [replyNo=" + replyNo + ", qnaboardNo=" + qnaboardNo + ", replyParentNo=" + replyParentNo
				+ ", replyDepth=" + replyDepth + ", replyContent=" + replyContent + ", memberId=" + memberId
				+ ", memberNickname=" + memberNickname + ", replyDate=" + replyDate + "]";
	}

}
