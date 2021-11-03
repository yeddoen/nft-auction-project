package project.spring.nft.domain;

import java.util.Date;

public class QnAReplyVO {
	private int replyNo; // 댓글 번호
	private int replybno; // 댓글이 속한 글 번호
	private int parentNo; // 대댓글이 속한 댓글 번호
	private int depth; // 모댓글=0, 대댓글=1
	private String replyContent; // 댓글 내용
	private String replyNickname; // 댓글 작성자
	private Date rDate; // 댓글 작성 날짜
	
	public QnAReplyVO() {}

	public QnAReplyVO(int replyNo, int replybno, int parentNo, int depth, String replyContent, String replyNickname,
			Date rDate) {
		super();
		this.replyNo = replyNo;
		this.replybno = replybno;
		this.parentNo = parentNo;
		this.depth = depth;
		this.replyContent = replyContent;
		this.replyNickname = replyNickname;
		this.rDate = rDate;
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public int getReplybno() {
		return replybno;
	}

	public void setReplybno(int replybno) {
		this.replybno = replybno;
	}

	public int getParentNo() {
		return parentNo;
	}

	public void setParentNo(int parentNo) {
		this.parentNo = parentNo;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyNickname() {
		return replyNickname;
	}

	public void setReplyNickname(String replyNickname) {
		this.replyNickname = replyNickname;
	}

	public Date getRDate() {
		return rDate;
	}

	public void setRDate(Date rDate) {
		this.rDate = rDate;
	}

	@Override
	public String toString() {
		return "QnAReplyVO [replyNo=" + replyNo + ", replybno=" + replybno + ", parentNo=" + parentNo + ", depth="
				+ depth + ", replyContent=" + replyContent + ", replyNickname=" + replyNickname + ", rDate="
				+ rDate + "]";
	}


}
