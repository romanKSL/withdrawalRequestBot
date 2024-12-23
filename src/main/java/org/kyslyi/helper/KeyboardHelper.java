package org.kyslyi.helper;

import java.util.Arrays;
import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class KeyboardHelper {
	public static ReplyKeyboardMarkup buildMainMenu() {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("⬇️ Withdraw");
        return ReplyKeyboardMarkup.builder()
                .keyboard(Arrays.asList(keyboardRow))
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .build();
    }
	
	public static ReplyKeyboardMarkup buildBackAndCancelMenu() {
        List<KeyboardButton> buttons = Arrays.asList(
                new KeyboardButton("↩️ Back"),
                new KeyboardButton("❌ Cancel"));
        KeyboardRow row1 = new KeyboardRow(buttons);
 
        return ReplyKeyboardMarkup.builder()
                .keyboard(Arrays.asList(row1))
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .build();
    }
}
