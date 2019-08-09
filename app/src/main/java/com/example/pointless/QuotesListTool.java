package com.example.pointless;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class QuotesListTool {
    private String fileName;

    public QuotesListTool(String fileName) {
        this.fileName = fileName;
    }

    public List<String> shuffleQuotesList(List<String> quotes) {
        Collections.shuffle(quotes);
        return quotes;
    }

    public LinkedList<String> shuffleQuotesLinkedList(LinkedList<String> quotes) {
        Collections.shuffle(quotes);
        return quotes;
    }

    public Stack<String> placeQuotesInStack() {
        Stack<String> stringStack = new Stack<>();
        Scanner sc = new Scanner(fileName);
        List<String> listOfQuotes = new ArrayList<>();
        while (sc.hasNext()) {
            String quote = sc.nextLine();
            //String quoteLang = sc.nextLine();
            listOfQuotes.add(quote);
        }
        sc.close();
        listOfQuotes = shuffleQuotesList(listOfQuotes);
        stringStack.addAll(listOfQuotes);

        return stringStack;
    }
}
