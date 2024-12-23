package org.kyslyi;

import org.kyslyi.model.UserRequest;
import org.kyslyi.model.UserSession;
import org.kyslyi.service.UserSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class WithdrawalRequestBot extends TelegramLongPollingBot {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);
    
    private final Dispatcher dispatcher;
    private final UserSessionService userSessionService;
    
    public WithdrawalRequestBot(Dispatcher dispatcher, UserSessionService userSessionService) {
    	this.dispatcher = dispatcher;
    	this.userSessionService = userSessionService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            String textFromUser = update.getMessage().getText();

            Long userId = update.getMessage().getChatId();
            String userFirstName = update.getMessage().getFrom().getFirstName();

            LOG.info("[{}, {}] : {}", userId, userFirstName, textFromUser);
            
            Long chatId = update.getMessage().getChatId();
            UserSession session = this.userSessionService.getSession(chatId);

            UserRequest userRequest = new UserRequest(update, userId, session);
            
            boolean dispatched = this.dispatcher.dispatch(userRequest);
            
            if(!dispatched) {
            	LOG.warn("Unexpected update from user");
            }
        } else {
        	LOG.warn("Unexpected update from user");
        }
    }

    @Override
    public String getBotUsername() {
        return "@orangel_bot";
    }

    @Override
    public String getBotToken() {
        return "7397225375:AAEogxM5uJBdh6L03GC8B7Qwn0v7npXHBeE";
    }
}
