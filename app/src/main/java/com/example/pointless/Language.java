package com.example.pointless;

/**
 * Enumerator class for Language.
 * @author Krzysztof Baran
 */
public enum Language {
    ENGLISH,
    DUTCH,
    UNKNOWN;

    /**
     * Converter from string to Language Enum.
     * @param str string from which to convert
     * @return Enumerator for language
     */
    public static Language stringToLanguage(String str) {
        switch (str) {
            case "English": return ENGLISH;
            case "Dutch" : return DUTCH;
            default: return UNKNOWN;
        }
    }

    /**
     * Converter from Language Enum to string.
     * @param language Enumerator for language
     * @return string value of the language
     */
    public static String valueOf(Language language) {
        switch (language) {
            case ENGLISH: return "English";
            case DUTCH: return "Dutch";
            default: return "Unknown";
        }
    }
}
