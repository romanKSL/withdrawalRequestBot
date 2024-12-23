package org.kyslyi.handler.impl;

import org.kyslyi.enums.ConversationState;
import org.kyslyi.handler.UserRequestHandler;
import org.kyslyi.helper.KeyboardHelper;
import org.kyslyi.model.UserRequest;
import org.kyslyi.model.UserSession;
import org.kyslyi.service.TelegramService;
import org.kyslyi.service.UserSessionService;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public class MakeWithdrawalHandler extends UserRequestHandler {

	private static final String COMMAND = "⬇️ Withdraw";
	
    private final TelegramService telegramService;
    private final UserSessionService userSessionService;

    public MakeWithdrawalHandler(TelegramService telegramService, UserSessionService userSessionService) {
        this.telegramService = telegramService;
        this.userSessionService = userSessionService;
    }

	@Override
	public boolean isApplicable(UserRequest userRequest) {
		return isNeededMessage(userRequest.getUpdate(), COMMAND);
	}

	@Override
	public void handle(UserRequest request) {
		ReplyKeyboard replyKeyboard = KeyboardHelper.buildBackAndCancelMenu();
		telegramService.sendMessage(request.getChatId(), "Enter your wallet address:", replyKeyboard);
		
		UserSession userSession = request.getUserSession();
		userSession.setState(ConversationState.WAITING_FOR_WALLET_ADDRESS);
		userSessionService.saveSession(request.getChatId(), userSession);
	}
}
