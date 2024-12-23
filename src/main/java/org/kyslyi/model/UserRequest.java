package org.kyslyi.model;

import org.telegram.telegrambots.meta.api.objects.Update;

public class UserRequest {
	private Update update;
	private Long chatId;
	private UserSession userSession;
	
	public UserRequest(Update update, Long userId, UserSession userSession) {
		this.update = update;
		this.chatId = userId;
		this.userSession = userSession;
	}

	public Update getUpdate() {
		return this.update;
	}

	public Long getChatId() {
		return this.chatId;
	}

	public UserSession getUserSession() {
		return this.userSession;
	}
}
