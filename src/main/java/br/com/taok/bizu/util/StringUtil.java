package br.com.taok.bizu.util;

import java.text.Normalizer;

public class StringUtil {

    public static String removeAccents(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = str.replaceAll("[^\\p{ASCII}]", "");
        return str;
    }
}
