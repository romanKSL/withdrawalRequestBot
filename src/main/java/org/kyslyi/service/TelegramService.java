package org.kyslyi.service;

import org.kyslyi.WithdrawalRequestBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramService {
	
	private static final Logger log = LoggerFactory.getLogger(TelegramService.class);
	
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
        execute(sendMessage);
	}
	
	public void editMessage(Update update, String text, InlineKeyboardMarkup replyKeyboard) {
		Long messageId = (long) update.getCallbackQuery().getMessage().getMessageId();
		Long chatId = update.getCallbackQuery().getMessage().getChatId();
		EditMessageText editMessage = EditMessageText.builder()
				.chatId(chatId.toString())
				.messageId(messageId.intValue())
				.replyMarkup(replyKeyboard)
				.text(text)
				.build();
		execute(editMessage);
	}
	
    private void execute(BotApiMethod botApiMethod) {
        try {
            botSender.execute(botApiMethod);
        } catch(TelegramApiException e) {
        	log.error("Exception: ", e);
        }
    }
    
	public void setBotSender(TelegramLongPollingBot botSender) {
		this.botSender = (WithdrawalRequestBot) botSender;
	}
}
