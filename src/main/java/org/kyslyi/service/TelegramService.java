package org.kyslyi.service;

import org.kyslyi.Application;
import org.kyslyi.WithdrawalRequestBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramService {
	
	private static final Logger LOG = LoggerFactory.getLogger(Application.class);
	
	private WithdrawalRequestBot botSender;
	
	public void sendMessage(Long chatId, String text) {
		sendMessage(chatId, text, null);
	}
	
	public void sendMessage(Long chatId, String text, ReplyKeyboard replyKeyboard) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId.toString())
                .text(text)
                .replyMarkup(replyKeyboard)
                .build();
        this.execute(sendMessage);
	}
	
    private void execute(BotApiMethod botApiMethod) {
        try {
            botSender.execute(botApiMethod);
        } catch(Exception e) {
            LOG.error("Exception: ", e);
        }
    }
    
	public void setBotSender(TelegramLongPollingBot botSender) {
		this.botSender = (WithdrawalRequestBot) botSender;
	}
}
