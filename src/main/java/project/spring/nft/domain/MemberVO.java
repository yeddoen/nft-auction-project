package project.spring.nft.domain;

public class MemberVO {
	private int memberNo;
	private String memberId, memberPassword;
	private String memberName, memberNickname;
	private String memberPhone, memberEmail;
	private String memberUid;
	
	public MemberVO() {}

	public MemberVO(int memberNo, String memberId, String memberPassword, String memberName, String memberNickname,
			String memberPhone, String memberEmail, String memberUid) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPassword = memberPassword;
		this.memberName = memberName;
		this.memberNickname = memberNickname;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberUid = memberUid;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberUid() {
		return memberUid;
	}

	public void setMemberUid(String memberUid) {
		this.memberUid = memberUid;
	}

	@Override
	public String toString() {
		return "MemberVO [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPassword=" + memberPassword
				+ ", memberName=" + memberName + ", memberNickname=" + memberNickname + ", memberPhone=" + memberPhone
				+ ", memberEmail=" + memberEmail + ", memberUid=" + memberUid + "]";
	}

}
