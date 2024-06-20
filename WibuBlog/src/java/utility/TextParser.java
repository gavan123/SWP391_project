/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author admin
 */
public class TextParser {
    
    private static final Pattern diacritics = Pattern.compile("[\\p{InCombiningDiacriticalMarks}\\p{IsM}\\p{IsLm}\\p{IsSk}]+");
    
    // normalizes the string
    public static String normalize(String text) {
        // turns string into all lowercase
        String normalizedText = text.toLowerCase();

        // removes most special characters, turning them into spaces
        normalizedText = normalizedText.replaceAll("\\/,", " ");
        normalizedText = normalizedText.replaceAll("\\,", " ");
        normalizedText = normalizedText.replaceAll("\\.", " ");
        normalizedText = normalizedText.replaceAll("\\!", " ");
        normalizedText = normalizedText.replaceAll("\\-", " ");
        normalizedText = normalizedText.replaceAll("\\_", " ");
        normalizedText = normalizedText.replaceAll("\\'", " ");
        normalizedText = normalizedText.replaceAll("\\|", " ");
        normalizedText = normalizedText.replaceAll("\\~", " ");
        normalizedText = normalizedText.replaceAll("\\`", " ");
        normalizedText = normalizedText.replaceAll("\\=", " ");
        normalizedText = normalizedText.replaceAll("\\:", " ");
        normalizedText = normalizedText.replaceAll("\\s+", " ");
        normalizedText = normalizedText.replaceAll("\\đ", "d"); // special case bc diacritic removal ignores this

        // removes diacritics (for vietnamese)
        normalizedText = Normalizer.normalize(normalizedText, Normalizer.Form.NFD);
        normalizedText = diacritics.matcher(normalizedText).replaceAll("");

        return normalizedText;
    }
}
