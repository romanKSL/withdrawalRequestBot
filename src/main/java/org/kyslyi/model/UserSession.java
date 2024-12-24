package org.kyslyi.model;

import org.kyslyi.enums.ConversationState;

public class UserSession {
	private Long chatId;
	private ConversationState state = ConversationState.CONVERSTATION_STARTED;
	private String walletAddress;
	private Double amount;
	
	public UserSession(Long chatId) {
		this.chatId = chatId;
	}
	
	public String toString() {
		return chatId.toString() + " " +
				walletAddress + " " +
				amount.toString();
	}
	
	public ConversationState getState() {
		return this.state;
	}

	public void setState(ConversationState state) {
		this.state = state;
	}

	public String getWalletAddress() {
		return this.walletAddress;
	}
	
	public void setWalletAddress(String walletAddress) {
		this.walletAddress = walletAddress;
	}

	public Double getAmount() {
		return this.amount;
	}
	
	public void setAmount(String amount) {
		this.amount = Double.valueOf(amount);
	}
}
