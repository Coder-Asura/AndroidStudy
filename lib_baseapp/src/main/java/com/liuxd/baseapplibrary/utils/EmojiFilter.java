package com.liuxd.baseapplibrary.utils;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Liuxd on 2016/8/1 16:29.
 * emoji表情过滤器
 */
public class EmojiFilter implements InputFilter {

    private Pattern mEmojiPattern = Pattern.compile("[\\ud83c\\udc00-\\ud83c\\udfff]|[\\ud83d\\udc00-\\ud83d\\udfff]|[\\u2600-\\u27ff]",
            Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        Matcher emojiMatcher = mEmojiPattern.matcher(source);
        if (emojiMatcher.find()) {
            LogUtil.d("EmojiFilter", "source: " + source.toString() + " is match.");
            return "";
        }
        LogUtil.d("EmojiFilter", "source: " + source.toString() + " is not match.");
        return null;
    }

}
