package com.example.pointless;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;

import java.util.Scanner;
import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test for QuotesList data structure.
 */
public class QuoteListTest {
    private final static String delimiter = "_";
    private QuoteList quoteList;
    private Stack<Quote> quoteStack;
    @Mock
    private QuotesListTool quoteToolMock;
    /**
     * Stack initializer.
     */
    @BeforeEach
    void before() {
        quoteStack = new Stack<>();
        quoteToolMock = mock(QuotesListTool.class);
    }

    /**
     * Test adding only one quote to the list and see what is the current quote.
     * @param testString string that will be used for that test
     */
    @ParameterizedTest
    @ValueSource(strings = {"Text1", "Text2", "Text3"})
    void getCurrentQuoteWhenCreatedTest(String testString) {
         quoteStack.push(new Quote(testString, Language.ENGLISH));
         quoteList = new QuoteList(quoteStack, quoteToolMock);
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
        quoteList = new QuoteList(quoteStack, quoteToolMock);
        assertEquals(quoteList.getCurrentQuote(), correct);
    }

    /**
     * Test to check that getting next quote gets what was below the stack.
     */
    @Test
    void changeQuoteToNext() {
        quoteStack.push(new Quote("Text3", Language.ENGLISH));
        quoteStack.push(new Quote("Text2", Language.ENGLISH));
        quoteList = new QuoteList(quoteStack, quoteToolMock);
        quoteList.nextQuote();
        assertEquals(quoteList.getCurrentQuote(), "Text3");
    }

    /**
     * Test for checking that when the user uses up all of the quotes,
     * the system starts to use old quotes. The QuoteListTool class is
     * mocked because we would like to have observability and conceivability
     * of the class as the shuffling process is not deterministic.
     * @param strList string to be parsed into a list
     * @param size size of the list used to loop until list is empty
     * @param correct the correct answer
     */
    @ParameterizedTest
    @CsvSource({"Text1_Text2_Text3, 3, Text1", "Text3_Text2, 2, Text3", "Text2, 1, Text2",
            "Text1 and Text2_Text3, 2, Text1 and Text2"})
    void nextQuoteWhenUsedAllQuotes(String strList, int size, String correct) {
        strListPlacedToStack(strList);
        quoteList = new QuoteList(quoteStack, quoteToolMock);
        when(quoteToolMock.shuffleQuotesLinkedList(quoteList.getUsedQuotes()))
                .thenReturn(quoteList.getUsedQuotes());
        while(size>0){
            quoteList.nextQuote();
            size--;
        }
        assertEquals(quoteList.getCurrentQuote(), correct);
    }

    /**
     * Test that when quote list is empty, there is only one quote.
     */
    @Test
    void newQuoteListThatHasEmptyStack() {
        quoteList = new QuoteList(quoteStack, quoteToolMock);
        assertEquals(quoteList.getCurrentQuote(), quoteList.getEmptyQuoteStack());
    }

    /*
        TO DO: TEST size of the used quotes list.
    */
    /**
     * Method to parse substrings of the quotes into individual strings.
     * @param str long string with substrings.
     */
    void strListPlacedToStack(String str) {
        Scanner scanner = new Scanner(str);
        scanner.useDelimiter(delimiter);
        while(scanner.hasNext()) {
            quoteStack.push(new Quote(scanner.next(), Language.ENGLISH));
        }
    }
}
