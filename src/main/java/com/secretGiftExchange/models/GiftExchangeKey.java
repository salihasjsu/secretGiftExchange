package com.secretGiftExchange.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Id;

public class GiftExchangeKey implements Serializable {

	private String memberId;
	private String memberRecipientId;
	private int year;

    public GiftExchangeKey() {
    }

    public GiftExchangeKey(String memberId, String memberRecipientId, int year) {
        this.memberId = memberId;
        this.memberRecipientId = memberRecipientId;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiftExchangeKey giftExch = (GiftExchangeKey) o;
        return memberRecipientId.equals(giftExch.memberRecipientId) &&
        		memberRecipientId.equals(giftExch.memberRecipientId) &&
        		year == giftExch.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, memberRecipientId,year);
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}