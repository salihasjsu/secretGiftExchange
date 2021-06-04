package com.secretGiftExchange.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class GiftExchange {
	@Id
	@Column(name="member_id")
	private String memberId;
	@Column(name="recipient_member_id")
	private String memberRecipientId;
	@Column(name="year")
	private int year;
	@Column(name="member_name")
	private String memberName;
	@Column(name="recipient_member_name")
	private String memberRecipientName;
	
	public GiftExchange() {
		
	}
	public GiftExchange(String memId, String memName, String recipId, String recipName, int year) {
		setMemberId(memId);
		setMemberRecipientId(recipId);
		setMemberName(memName);
		setMemberRecipientName(recipName);
		setYear(year);
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberRecipientId() {
		return memberRecipientId;
	}
	public void setMemberRecipientId(String memberRecipientId) {
		this.memberRecipientId = memberRecipientId;
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
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

}
