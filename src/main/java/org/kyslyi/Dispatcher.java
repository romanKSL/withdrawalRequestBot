package org.kyslyi;

import java.util.List;

import org.kyslyi.enums.ConversationState;
import org.kyslyi.handler.UserRequestHandler;
import org.kyslyi.helper.KeyboardHelper;
import org.kyslyi.model.UserRequest;
import org.kyslyi.model.UserSession;
import org.kyslyi.service.TelegramService;
import org.kyslyi.service.UserSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dispatcher {
	
	private static Logger log = LoggerFactory.getLogger(Dispatcher.class);

    private final List<UserRequestHandler> handlers;
    private final TelegramService telegramService;
    private final UserSessionService userSessionService;

    public Dispatcher(TelegramService telegramService, UserSessionService userSessionService, List<UserRequestHandler> handlers) {
        this.handlers = handlers;
        this.telegramService = telegramService;
        this.userSessionService = userSessionService;
    }

    public boolean dispatch(UserRequest userRequest) {
        for (UserRequestHandler userRequestHandler : handlers) {
            if(userRequestHandler.isApplicable(userRequest)){
                userRequestHandler.handle(userRequest);
                return true;
            }
        }
        return false;
    }
    
    public boolean execute(UserRequest request) {
		String requestCommand = request.getUpdate().getCallbackQuery().getData();
		UserSession userSession = request.getUserSession();
		
		switch(requestCommand) {
		case "withdraw":
			log.info("[{}] : {}", request.getChatId(), requestCommand);
			telegramService.editMessage(request.getUpdate(), "Enter your wallet address:", KeyboardHelper.buildBackAndCancelMenu());
			userSession.setState(ConversationState.WAITING_FOR_WALLET_ADDRESS);
			break;
		case "cancel":
			telegramService.editMessage(request.getUpdate(), "Choose your operation from the menu below ⤵️", KeyboardHelper.buildMainMenu());
			break;
		default:
			return false;
		}
		
		userSessionService.saveSession(request.getChatId(), userSession);
		return true;
	}
}