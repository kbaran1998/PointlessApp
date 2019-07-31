package com.example.pointless;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Scanner;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class QuoteListTest {
    private QuoteList quoteList;
    private Stack<String> quoteStack;

    @BeforeEach
    public void before() {
        quoteStack = new Stack<>();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Text1", "Text2", "Text3"})
    public void getCurrentQuoteWhenCreatedTest(String testString) {
         quoteStack.push(testString);
         quoteList = new QuoteList(quoteStack);
         assertEquals(quoteList.getCurrentQuote(), testString);
    }

    @ParameterizedTest
    @CsvSource({"Text1 Text2 Text3, Text3", "Text3 Text2, Text2", "Text2, Text2"})
    public void getCurrentQuoteWhenCreatedTestMultiple(String strList, String correct) {
        strListPlacedToStack(strList);
        quoteList = new QuoteList(quoteStack);
        assertEquals(quoteList.getCurrentQuote(), correct);
    }

    @Test
    public void changeQuoteToNext() {
        quoteStack.push("Text3");
        quoteStack.push("Text2");
        quoteList = new QuoteList(quoteStack);
        quoteList.setCurrentQuote();
        assertEquals(quoteList.getCurrentQuote(), "Text3");
    }

    public void strListPlacedToStack(String str) {
        Scanner scanner = new Scanner(str);
        while(scanner.hasNext()) {
            quoteStack.push(scanner.next());
        }
    }
}
