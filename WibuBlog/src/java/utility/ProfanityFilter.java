/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author admin
 */
public class ProfanityFilter {

    private static final Set<String> profanities = new HashSet<>();
    private static final Set<String> multiWordProfanities = new HashSet<>();
    private static final Pattern diacritics = Pattern.compile("[\\p{InCombiningDiacriticalMarks}\\p{IsM}\\p{IsLm}\\p{IsSk}]+");

    // loads list of known profanities from files
    // files are located in %PROJECT_ROOT%\assets\
    // file names are self explanatory
    // put words where they belong!
    static {
        try {
            var profanitiesList = new File("assets\\profanities.txt");
            var multiWordProfanitiesList = new File("assets\\multiwordprofanities.txt");

            Scanner sc1 = new Scanner(profanitiesList);
            Scanner sc2 = new Scanner(multiWordProfanitiesList);

            while (sc1.hasNextLine()) {
                profanities.add(sc1.nextLine());
            }

            while (sc2.hasNextLine()) {
                multiWordProfanities.add(sc2.nextLine());
            }

        } catch (FileNotFoundException fnfe) {
            System.err.println("File not found.");
        }
    }

    // checks if word is a profanity
    private static boolean isProfanity(String word) {
        for (String profanity : profanities) {
            if (word.toLowerCase().contains(profanity)) {
                return true;
            }
        }

        return false;
    }

    // checks if phrase if a profanity
    private static boolean isMultiWordProfanity(String phrase) {
        for (String profanity : multiWordProfanities) {
            if (phrase.toLowerCase().contains(profanity)) {
                return true;
            }
        }

        return false;
    }

    // split the string into tokens, delimited by any non-word elements
    private static List<String> parseText(String text) {
        return Arrays.asList(normalize(text).split("\\W+"));
    }

    // normalizes the string
    private static String normalize(String text) {
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

    // count profane words and phrases
    // single-token profanities are counted as is
    // multi-token profanities are counted on a per phrase basis: each phrase is considered 1 profanity
    private static int countProfaneWords(List<String> words) {
        int profaneWordsCount = 0;
        int n = words.size();
        for (int i = 0; i < n; i++) {
            if (ProfanityFilter.isProfanity(words.get(i))) {
                profaneWordsCount++;
                continue;
            }

            // recursive count for multi-token profanities
            // creates a sliding window to capture the whole phrase, then place pointer after end of captured phrase
            for (int j = i + 1; j <= n; j++) {
                String phrase = String.join(" ", words.subList(i, j));
                if (ProfanityFilter.isMultiWordProfanity(phrase)) {
                    profaneWordsCount++;
                    break;
                }
            }
        }

        return profaneWordsCount;
    }

    
    // public facing methods
    
    public static float calculateProfanityPercentage(String text) {
        List<String> words = parseText(text);
        int totalWords = words.size();
        int profaneWords = countProfaneWords(words);

        return totalWords > 0 ? (profaneWords / (float) totalWords) * 100 : 0;
    }

    public static boolean checkProfanity(String text) {
        return calculateProfanityPercentage(text) > 0;
    }
}
