package org.kyslyi;

import java.util.ArrayList;
import java.util.Arrays;

import org.kyslyi.handler.UserRequestHandler;
import org.kyslyi.handler.impl.*;
import org.kyslyi.service.TelegramService;
import org.kyslyi.service.UserSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Application {
	
    private static Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
        	log.info("Registering bot...");
        	TelegramService telegramService = new TelegramService();
        	UserSessionService userSessionService = new UserSessionService();
        	
        	TelegramLongPollingBot withdrawalRequestBot = new WithdrawalRequestBot(
            		new Dispatcher(telegramService, userSessionService, new ArrayList<UserRequestHandler>(Arrays.asList(
            				new StartCommandHandler(telegramService),
            				new CancelHandler(telegramService),
            				new MakeWithdrawalHandler(telegramService, userSessionService),
            				new WalletAddressEnteredHandler(telegramService, userSessionService),
            				new AmountEnteredHandler(telegramService, userSessionService)))),
            		userSessionService);

            telegramBotsApi.registerBot(withdrawalRequestBot);
            telegramService.setBotSender(withdrawalRequestBot);
            log.info("Telegram bot is ready to accept updates from user...");
        } catch (TelegramApiRequestException e) {
        	log.error("Failed to register bot(check internet connection / bot token or make sure only one instance of bot is running).");
        }
    }
}
