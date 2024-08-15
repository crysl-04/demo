package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RomanizationService {

    // 使用 HashMap 进行大规模的条目初始化
    private static final Map<Character, String> ROMANIZATION_MAP = new HashMap<>();

    static {
        ROMANIZATION_MAP.put('ㄱ', "g");
        ROMANIZATION_MAP.put('ㄲ', "kk");
        ROMANIZATION_MAP.put('ㄳ', "gs");
        ROMANIZATION_MAP.put('ㄴ', "n");
        ROMANIZATION_MAP.put('ㄵ', "nj");
        ROMANIZATION_MAP.put('ㄶ', "nh");
        ROMANIZATION_MAP.put('ㄷ', "d");
        ROMANIZATION_MAP.put('ㄸ', "tt");
        ROMANIZATION_MAP.put('ㄹ', "r");
        ROMANIZATION_MAP.put('ㄺ', "lg");
        ROMANIZATION_MAP.put('ㄻ', "lm");
        ROMANIZATION_MAP.put('ㄼ', "lb");
        ROMANIZATION_MAP.put('ㄽ', "ls");
        ROMANIZATION_MAP.put('ㄾ', "lt");
        ROMANIZATION_MAP.put('ㄿ', "lp");
        ROMANIZATION_MAP.put('ㅀ', "lh");
        ROMANIZATION_MAP.put('ㅁ', "m");
        ROMANIZATION_MAP.put('ㅂ', "b");
        ROMANIZATION_MAP.put('ㅃ', "pp");
        ROMANIZATION_MAP.put('ㅄ', "bs");
        ROMANIZATION_MAP.put('ㅅ', "s");
        ROMANIZATION_MAP.put('ㅆ', "ss");
        ROMANIZATION_MAP.put('ㅇ', "");
        ROMANIZATION_MAP.put('ㅈ', "j");
        ROMANIZATION_MAP.put('ㅉ', "jj");
        ROMANIZATION_MAP.put('ㅊ', "ch");
        ROMANIZATION_MAP.put('ㅋ', "k");
        ROMANIZATION_MAP.put('ㅌ', "t");
        ROMANIZATION_MAP.put('ㅍ', "p");
        ROMANIZATION_MAP.put('ㅎ', "h");
        ROMANIZATION_MAP.put('ㅏ', "a");
        ROMANIZATION_MAP.put('ㅐ', "ae");
        ROMANIZATION_MAP.put('ㅑ', "ya");
        ROMANIZATION_MAP.put('ㅒ', "yae");
        ROMANIZATION_MAP.put('ㅓ', "eo");
        ROMANIZATION_MAP.put('ㅔ', "e");
        ROMANIZATION_MAP.put('ㅕ', "yeo");
        ROMANIZATION_MAP.put('ㅖ', "ye");
        ROMANIZATION_MAP.put('ㅗ', "o");
        ROMANIZATION_MAP.put('ㅘ', "wa");
        ROMANIZATION_MAP.put('ㅙ', "wae");
        ROMANIZATION_MAP.put('ㅚ', "oe");
        ROMANIZATION_MAP.put('ㅛ', "yo");
        ROMANIZATION_MAP.put('ㅜ', "u");
        ROMANIZATION_MAP.put('ㅝ', "wo");
        ROMANIZATION_MAP.put('ㅞ', "we");
        ROMANIZATION_MAP.put('ㅟ', "wi");
        ROMANIZATION_MAP.put('ㅠ', "yu");
        ROMANIZATION_MAP.put('ㅡ', "eu");
        ROMANIZATION_MAP.put('ㅢ', "ui");
        ROMANIZATION_MAP.put('ㅣ', "i");
    }

    public String romanizeKorean(String text) {
        StringBuilder romanized = new StringBuilder();
        for (char c : text.toCharArray()) {
            romanized.append(ROMANIZATION_MAP.getOrDefault(c, String.valueOf(c)));
        }
        return romanized.toString();
    }
}
