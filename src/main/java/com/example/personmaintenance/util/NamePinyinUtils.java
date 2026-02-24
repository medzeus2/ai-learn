package com.example.personmaintenance.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

public final class NamePinyinUtils {

    private static final HanyuPinyinOutputFormat FORMAT = new HanyuPinyinOutputFormat();

    static {
        FORMAT.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        FORMAT.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    }

    private NamePinyinUtils() {
    }

    public static String toPinyin(String input) {
        if (input == null || input.isBlank()) {
            return "";
        }
        StringBuilder pinyin = new StringBuilder();
        for (char ch : input.trim().toCharArray()) {
            if (Character.isWhitespace(ch)) {
                continue;
            }
            String py = firstPinyin(ch);
            if (py != null) {
                pinyin.append(py);
            } else if (Character.isLetterOrDigit(ch)) {
                pinyin.append(Character.toLowerCase(ch));
            }
        }
        return pinyin.toString();
    }

    public static String toInitials(String input) {
        if (input == null || input.isBlank()) {
            return "";
        }
        StringBuilder initials = new StringBuilder();
        for (char ch : input.trim().toCharArray()) {
            if (Character.isWhitespace(ch)) {
                continue;
            }
            String py = firstPinyin(ch);
            if (py != null && !py.isEmpty()) {
                initials.append(py.charAt(0));
            } else if (Character.isLetterOrDigit(ch)) {
                initials.append(Character.toLowerCase(ch));
            }
        }
        return initials.toString();
    }

    private static String firstPinyin(char ch) {
        try {
            String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(ch, FORMAT);
            if (pinyins != null && pinyins.length > 0) {
                return pinyins[0];
            }
        } catch (Exception ignored) {
        }
        return null;
    }
}
