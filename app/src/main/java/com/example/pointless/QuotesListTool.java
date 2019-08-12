package com.example.pointless;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * A Quote list helper that handles outside calls from the Quote List data structure.
 * @author Krzysztof Baran
 */
public class QuotesListTool {
    private final String fileName;

    /**
     * Constructor for Quotes List tool that handles DB calls and list edits.
     * @param fileName name of the file
     */
    public QuotesListTool(String fileName) {
        this.fileName = fileName;
    }

    /**
     * List shuffler that makes sure items are shuffled.
     * @param quotes List of quotes
     * @return shuffled list of quotes
     */
    public List<Quote> shuffleQuotesList(List<Quote> quotes) {
        Collections.shuffle(quotes);
        return quotes;
    }

    /**
     * Same premise of shuffling quotes but with a Linked List.
     * @param quotes Linked-List of quotes
     * @return Linked-List of shuffled quotes
     */
    public LinkedList<Quote> shuffleQuotesLinkedList(LinkedList<Quote> quotes) {
        Collections.shuffle(quotes);
        return quotes;
    }

    /**
     * Getter for DB from a text file that get quotes.
     * @return Stack with Quotes
     */
    public Stack<Quote> placeQuotesFromDBInStack() {
        Stack<Quote> stringStack = new Stack<>();
        Scanner sc = null;
        try {
            sc = new Scanner(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<Quote> listOfQuotes = new ArrayList<>();
        if (sc != null) {
            while (sc.hasNext()) {
                String quote = sc.nextLine();
                String quoteLang = sc.nextLine();
                //For now only accept English
                if (quoteLang.equals("English")) {
                    listOfQuotes.add(new Quote(quote, Language.stringToLanguage(quoteLang)));
                }
            }
            sc.close();
            listOfQuotes = shuffleQuotesList(listOfQuotes);
            stringStack.addAll(listOfQuotes);
        }
        return stringStack;
    }
}
