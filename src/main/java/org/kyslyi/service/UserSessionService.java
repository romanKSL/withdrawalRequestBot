package org.kyslyi.service;

import java.util.HashMap;
import java.util.Map;

import org.kyslyi.model.UserSession;

public class UserSessionService {
	
	private Map<Long, UserSession> userSessionMap = new HashMap<>();
	
	public UserSession getSession(Long chatId) {
		return userSessionMap.getOrDefault(chatId, new UserSession(chatId));
	}
	
    public UserSession saveSession(Long chatId, UserSession session) {
        return userSessionMap.put(chatId, session);
    }
}
