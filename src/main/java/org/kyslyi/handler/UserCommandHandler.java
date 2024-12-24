package org.kyslyi.handler;

import org.kyslyi.enums.ConversationState;
import org.kyslyi.helper.KeyboardHelper;
import org.kyslyi.model.UserRequest;
import org.kyslyi.model.UserSession;
import org.kyslyi.service.TelegramService;
import org.kyslyi.service.UserSessionService;

public class UserCommandHandler {
//	
//    private final TelegramService telegramService;
//    private final UserSessionService userSessionService;
//    
//    public UserCommandHandler(TelegramService telegramService, UserSessionService userSessionService) {
//        this.telegramService = telegramService;
//        this.userSessionService = userSessionService;
//    }
//	
//	public boolean execute(UserRequest request) {
//		String requestCommand = request.getUpdate().getCallbackQuery().getData();
//		UserSession userSession = request.getUserSession();
//		
//		switch(requestCommand) {
//		case "withdraw":
//			telegramService.editMessage(request.getUpdate(), "Enter your wallet address:", KeyboardHelper.buildBackAndCancelMenu());
//			userSession.setState(ConversationState.WAITING_FOR_WALLET_ADDRESS);
//			break;
//		case "cancel":
//			telegramService.sendMessage(request.getChatId(), "Choose your operation from the menu below ⤵️", KeyboardHelper.buildMainMenu());
//			break;
//		default:
//			return false;
//		}
//		
//		userSessionService.saveSession(request.getChatId(), userSession);
//		return true;
//	}
}
