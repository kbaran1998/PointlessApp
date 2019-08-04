package com.example.pointless;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Scanner;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * Test for QuotesList data structure.
 */
public class QuoteListTest {
    private QuoteList quoteList;
    private Stack<String> quoteStack;

    /**
     * Stack initializer.
     */
    @BeforeEach
    void before() {
        quoteStack = new Stack<>();
    }

    /**
     * Test adding only one quote to the list and see what is the current quote.
     * @param testString string that will be used for that test
     */
    @ParameterizedTest
    @ValueSource(strings = {"Text1", "Text2", "Text3"})
    void getCurrentQuoteWhenCreatedTest(String testString) {
         quoteStack.push(testString);
         quoteList = new QuoteList(quoteStack);
         assertEquals(quoteList.getCurrentQuote(), testString);
    }

    /**
     * Test to check that when having multiple elements, the top element of the stack is picked.
     * @param strList string that contains substrings of quotes
     * @param correct the result that should be outputted.
     */
    @ParameterizedTest
    @CsvSource({"Text1_Text2_Text3, Text3", "Text3_Text2, Text2", "Text2, Text2",
            "Text1 and Text2_Text3, Text3"})
    void getCurrentQuoteWhenCreatedTestMultiple(String strList, String correct) {
        strListPlacedToStack(strList);
        quoteList = new QuoteList(quoteStack);
        assertEquals(quoteList.getCurrentQuote(), correct);
    }

    /**
     * Test to check that getting next quote gets what was below the stack.
     */
    @Test
    void changeQuoteToNext() {
        quoteStack.push("Text3");
        quoteStack.push("Text2");
        quoteList = new QuoteList(quoteStack);
        quoteList.nextQuote();
        assertEquals(quoteList.getCurrentQuote(), "Text3");
    }

    /**
     * Method to parse substrings of the quotes into individual strings.
     * @param str long string with substrings.
     */
    void strListPlacedToStack(String str) {
        String delimiter = "_";
        Scanner scanner = new Scanner(str);
        scanner.useDelimiter(delimiter);
        while(scanner.hasNext()) {
            quoteStack.push(scanner.next());
        }
    }
}
