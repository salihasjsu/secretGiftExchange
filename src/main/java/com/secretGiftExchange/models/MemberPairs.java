package com.secretGiftExchange.models;

public class MemberPairs {
	private String memberId;
	private String recipientMemberId;
	private String memberName;
	private String memberRecipientName;
	public MemberPairs() {
		
	}
	public MemberPairs(String memId, String memName, String recipId, String recipName) {
		setMemberId(memId);
		setRecipientMemberId(recipId);
		setMemberName(memName);
		setMemberRecipientName(recipName);
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getRecipientMemberId() {
		return recipientMemberId;
	}
	public void setRecipientMemberId(String recipientMemberId) {
		this.recipientMemberId = recipientMemberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberRecipientName() {
		return memberRecipientName;
	}
	public void setMemberRecipientName(String memberRecipientName) {
		this.memberRecipientName = memberRecipientName;
	}

}
