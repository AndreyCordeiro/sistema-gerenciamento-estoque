package com.sge.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class Util {
    public static String removerAcentos(String value) {
        String normalizer = Normalizer.normalize(value, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalizer).replaceAll("");
    }
}
