package org.kyslyi.handler.impl;

import org.kyslyi.handler.UserRequestHandler;
import org.kyslyi.helper.KeyboardHelper;
import org.kyslyi.model.UserRequest;
import org.kyslyi.service.TelegramService;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public class StartCommandHandler extends UserRequestHandler {
	
	private static final String COMMAND = "/start";
	
    private final TelegramService telegramService;

    public StartCommandHandler(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

	@Override
	public boolean isApplicable(UserRequest userRequest) {
		return isCommand(userRequest.getUpdate(), COMMAND);
	}

	@Override
	public void handle(UserRequest request) {
		ReplyKeyboard replyKeyboard = KeyboardHelper.buildMainMenu();
		telegramService.sendMessage(request.getChatId(), "Hello there!");
		telegramService.sendMessage(request.getChatId(), "Choose your operation from the menu below ⤵️", replyKeyboard);
	}
}
