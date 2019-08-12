package com.example.pointless;

/**
 * Class for storing quote's information.
 * @author Krzysztof Baran
 */
public class Quote {
    private String quoteContent;
    private Language quoteLanguage;

    /**
     * Constructor for Quote class
     * @param quoteContent actual text for the quote
     * @param quoteLanguage language of that quote
     */
    public Quote(String quoteContent, Language quoteLanguage) {
        this.quoteContent = quoteContent;
        this.quoteLanguage = quoteLanguage;
    }

    /**
     * Get the string of the quote.
     * @return string for the quote
     */
    public String getQuoteContent() {
        return quoteContent;
    }

    /**
     * Getting the language of the quote.
     * @return Language of the quote
     */
    public Language getQuoteLanguage() {
        return quoteLanguage;
    }
}
