package org.kyslyi.handler;

import org.kyslyi.model.UserRequest;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class UserRequestHandler {

	public abstract boolean isApplicable(UserRequest userRequest);

	public abstract void handle(UserRequest userRequest);
	
	public boolean isCommand(Update update, String command) {
		return update.hasMessage() && update.getMessage().isCommand() &&
				update.getMessage().getText().equals(command);
	}
	
	public boolean isTextMessage(Update update) {
        return update.hasMessage() && update.getMessage().hasText();
    }

    public boolean isTextMessage(Update update, String text) {
        return isTextMessage(update) && update.getMessage().getText().equals(text);
    }
}
