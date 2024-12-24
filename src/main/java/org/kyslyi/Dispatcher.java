package org.kyslyi;

import org.kyslyi.enums.ConversationState;
import org.kyslyi.helper.KeyboardHelper;
import org.kyslyi.model.UserRequest;
import org.kyslyi.model.UserSession;
import org.kyslyi.service.TelegramService;
import org.kyslyi.service.UserSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dispatcher {
	
	private static Logger log = LoggerFactory.getLogger(Dispatcher.class);

//    private final List<UserRequestHandler> handlers;
    private final TelegramService telegramService;
    private final UserSessionService userSessionService;

    public Dispatcher(TelegramService telegramService, UserSessionService userSessionService) {
//        this.handlers = handlers;
        this.telegramService = telegramService;
        this.userSessionService = userSessionService;
    }

//    public boolean dispatch(UserRequest userRequest) {
//        for (UserRequestHandler userRequestHandler : handlers) {
//            if(userRequestHandler.isApplicable(userRequest)){
//                userRequestHandler.handle(userRequest);
//                return true;
//            }
//        }
//        return false;
//    }
    
    public boolean executeCommand(UserRequest request) {
    	String requestCommand = request.getUpdate().getMessage().getText();
    	UserSession userSession = request.getUserSession();
    	
    	switch(requestCommand) {
    	case "/start":
    		log.info("[{}] : {}", request.getChatId(), requestCommand);
    		telegramService.sendMessage(request.getChatId(), "Hello there!");
    		telegramService.sendMessage(request.getChatId(), "Choose your operation from the menu below ⤵️", KeyboardHelper.buildMainMenu());
    		break;
    	case "/withdraw":
    		telegramService.sendMessage(request.getChatId(), "Enter your wallet address:", KeyboardHelper.buildBackAndCancelMenu());
    		userSession.setState(ConversationState.WAITING_FOR_WALLET_ADDRESS);
    		break;
    	case "/cancel":
    		telegramService.sendMessage(request.getChatId(), "Choose your operation from the menu below ⤵️", KeyboardHelper.buildMainMenu());
    		break;
    	default:
    		return false;
    	}
    	
    	userSessionService.saveSession(request.getChatId(), userSession);
		return true;
    }
    
    public boolean executeInline(UserRequest request) {
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
    
    public boolean executeText(UserRequest request) {
    	String requestCommand = request.getUpdate().getMessage().getText();
    	UserSession userSession = request.getUserSession();
    	
    	ConversationState requestState = request.getUserSession().getState();
    	if(requestState == ConversationState.WAITING_FOR_WALLET_ADDRESS) {
    		userSession.setWalletAddress(requestCommand);
    		telegramService.sendMessage(request.getChatId(), "Successfully! Now enter amount you want to withdrawal:",
    				KeyboardHelper.buildBackAndCancelMenu());

    		userSession.setState(ConversationState.WAITING_FOR_AMOUNT);
    	} else if(requestState == ConversationState.WAITING_FOR_AMOUNT) {
    		userSession.setAmount(request.getUpdate().getMessage().getText());
    		
    		telegramService.sendMessage(request.getChatId(),
    				"Successfully! Your request is " + userSession.getAmount() + " to " +
    						userSession.getWalletAddress() + ". We will process your request within the next 24 hours.");
    		telegramService.sendMessage(request.getChatId(), "Choose your operation from the menu below ⤵️",
    				KeyboardHelper.buildMainMenu());
    		
    		userSession.setState(ConversationState.CONVERSTATION_STARTED);
    	}
    	
    	userSessionService.saveSession(request.getChatId(), userSession);
		return true;
    }
}