package project.spring.nft.domain;

import java.util.Date;

public class QnABoardVO {
	private int bno; // 게시글 번호
	private String title; // 게시글 제목
	private String content; // 게시글 내용
	private String nickname; // 사용자 닉네임
	private Date cdate; // 등록 날짜
	
	public QnABoardVO() {}

	public QnABoardVO(int bno, String title, String content, String nickname, Date cdate) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.nickname = nickname;
		this.cdate = cdate;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	@Override
	public String toString() {
		return "QnABoardVO [bno=" + bno + ", title=" + title + ", content=" + content + ", nickname=" + nickname
				+ ", cdate=" + cdate + "]";
	}

}
