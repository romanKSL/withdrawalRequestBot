package org.kyslyi.helper;

import java.util.Arrays;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class KeyboardHelper {
	
	public static InlineKeyboardMarkup buildBackAndCancelMenu() {
//		KeyboardRow row = new KeyboardRow();
		InlineKeyboardButton button = InlineKeyboardButton.builder()
				.text("↩️ Back")
				.callbackData("back")
				.build();
		InlineKeyboardButton button2 = InlineKeyboardButton.builder()
				.text("❌ Cancel")
				.callbackData("cancel")
				.build();
		return InlineKeyboardMarkup.builder()
				.keyboardRow(Arrays.asList(button, button2))
				.build();
	}
	
	public static InlineKeyboardMarkup buildMainMenu() {
		InlineKeyboardButton button = InlineKeyboardButton.builder()
				.text("⬇️ Withdraw")
				.callbackData("withdraw")
				.build();
		return InlineKeyboardMarkup.builder()
				.keyboardRow(Arrays.asList(button))
				.build();
	}
}
