package org.kyslyi.handler.impl;

import org.kyslyi.Application;
import org.kyslyi.enums.ConversationState;
import org.kyslyi.handler.UserRequestHandler;
import org.kyslyi.model.UserRequest;
import org.kyslyi.model.UserSession;
import org.kyslyi.service.TelegramService;
import org.kyslyi.service.UserSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AmountEnteredHandler extends UserRequestHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(Application.class);
	
	private final TelegramService telegramService;
    private final UserSessionService userSessionService;

    public AmountEnteredHandler(TelegramService telegramService, UserSessionService userSessionService) {
        this.telegramService = telegramService;
        this.userSessionService = userSessionService;
    }

	@Override
	public boolean isApplicable(UserRequest userRequest) {
		return isTextMessage(userRequest.getUpdate()) &&
				userRequest.getUserSession().getState().equals(ConversationState.WAITING_FOR_AMOUNT);
	}

	@Override
	public void handle(UserRequest request) {
		telegramService.sendMessage(request.getChatId(), "Successfully! We will process your request within the next 24 hours.");
		
		UserSession userSession = request.getUserSession();
		userSession.setAmount(request.getUpdate().getMessage().getText());
		
		userSession.setState(ConversationState.CONVERSTATION_STARTED);
		userSessionService.saveSession(request.getChatId(), userSession);
		LOG.info(userSession.toString());
	}
}
