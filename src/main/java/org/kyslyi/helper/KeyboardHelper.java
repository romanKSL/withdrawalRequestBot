package org.kyslyi.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class KeyboardHelper {
	public static ReplyKeyboardMarkup buildMainMenu() {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("Make a withdrawal request");
        List<KeyboardRow> kbrl = new ArrayList<>(Arrays.asList(keyboardRow));
        return ReplyKeyboardMarkup.builder()
                .keyboard(kbrl)
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .build();
    }
}
