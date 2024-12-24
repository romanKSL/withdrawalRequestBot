package org.kyslyi.helper;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class KeyboardHelper {
//	public static ReplyKeyboardMarkup buildMainMenu() {
//        KeyboardRow keyboardRow = new KeyboardRow();
//        keyboardRow.add("⬇️ Withdraw");
//        return ReplyKeyboardMarkup.builder()
//                .keyboard(Arrays.asList(keyboardRow))
//                .selective(true)
//                .resizeKeyboard(true)
//                .oneTimeKeyboard(false)
//                .build();
//    }
//	
//	public static ReplyKeyboardMarkup buildBackAndCancelMenu() {
//        List<KeyboardButton> buttons = Arrays.asList(
//                new KeyboardButton("↩️ Back"),
//                new KeyboardButton("❌ Cancel"));
//        KeyboardRow row1 = new KeyboardRow(buttons);
// 
//        return ReplyKeyboardMarkup.builder()
//                .keyboard(Arrays.asList(row1))
//                .selective(true)
//                .resizeKeyboard(true)
//                .oneTimeKeyboard(false)
//                .build();
//    }
	
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
//		KeyboardRow row = new KeyboardRow();
		InlineKeyboardButton button = InlineKeyboardButton.builder()
				.text("⬇️ Withdraw")
				.callbackData("withdraw")
				.build();
		return InlineKeyboardMarkup.builder()
				.keyboardRow(Arrays.asList(button))
				.build();
	}
}
