package org.kyslyi.handler.impl;

import org.kyslyi.enums.ConversationState;
import org.kyslyi.handler.UserRequestHandler;
import org.kyslyi.model.UserRequest;
import org.kyslyi.model.UserSession;
import org.kyslyi.service.TelegramService;
import org.kyslyi.service.UserSessionService;

public class WalletAddressEnteredHandler extends UserRequestHandler{
	
    private final TelegramService telegramService;
    private final UserSessionService userSessionService;

    public WalletAddressEnteredHandler(TelegramService telegramService, UserSessionService userSessionService) {
        this.telegramService = telegramService;
        this.userSessionService = userSessionService;
    }

	@Override
	public boolean isApplicable(UserRequest userRequest) {
		return userRequest.getUserSession().getState().equals(ConversationState.WAITING_FOR_WALLET_ADDRESS);
	}

	@Override
	public void handle(UserRequest request) {
		telegramService.sendMessage(request.getChatId(), "Successfully! Now enter amount you want to withdrawal:");
		
		UserSession userSession = request.getUserSession();
		userSession.setWalletAddress(request.getUpdate().getMessage().getText());
		
		userSession.setState(ConversationState.WAITING_FOR_AMOUNT);
		userSessionService.saveSession(request.getChatId(), userSession);
	}
}
