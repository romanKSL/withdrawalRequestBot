package org.kyslyi.handler.impl;

import org.kyslyi.enums.ConversationState;
import org.kyslyi.handler.UserRequestHandler;
import org.kyslyi.model.UserRequest;
import org.kyslyi.model.UserSession;
import org.kyslyi.service.TelegramService;
import org.kyslyi.service.UserSessionService;

public class MakeWithdrawalHandler extends UserRequestHandler {

	private static final String COMMAND = "Make a withdrawal request";
	
    private final TelegramService telegramService;
    private final UserSessionService userSessionService;

    public MakeWithdrawalHandler(TelegramService telegramService, UserSessionService userSessionService) {
        this.telegramService = telegramService;
        this.userSessionService = userSessionService;
    }

	@Override
	public boolean isApplicable(UserRequest userRequest) {
		return isTextMessage(userRequest.getUpdate(), COMMAND) &&
				userRequest.getUpdate().hasMessage();
	}

	@Override
	public void handle(UserRequest request) {
		telegramService.sendMessage(request.getChatId(), "Enter your wallet address:");
		
		UserSession userSession = request.getUserSession();
		userSession.setState(ConversationState.WAITING_FOR_WALLET_ADDRESS);
		userSessionService.saveSession(request.getChatId(), userSession);
	}
}
