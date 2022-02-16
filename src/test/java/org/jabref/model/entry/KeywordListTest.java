package org.jabref.model.entry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KeywordListTest {

    private KeywordList keywords;

    @BeforeEach
    public void setUp() throws Exception {
        keywords = new KeywordList();
        keywords.add("keywordOne");
        keywords.add("keywordTwo");
    }

    //Part 3: Our New Test Cases

    @Test
    public void parseListReturnListTest()throws Exception{
        List<String> keywordChains = new ArrayList<>();
        keywordChains.add("key1");
        keywordChains.add("key2");
        assertEquals(new KeywordList(keywordChains),
                KeywordList.parse("key1, key2", ',') );
    }

    @Test
    public void createCloneCronstructorTest() throws Exception{
        KeywordList ky = new KeywordList("key1");
        assertEquals(ky, ky.createClone());
    }

    @Test
    public void replaceAllTest() throws Exception {
        KeywordList ky = new KeywordList("key1", "key2", "key3");
        KeywordList ky_replace = new KeywordList("key2");
        Keyword ky_new = new Keyword("key4");
        KeywordList ky2 = new KeywordList("key1", "key4", "key3");
        ky.replaceAll(ky_replace, ky_new);
        assertEquals(ky2,ky);
    }

    @Test
    public void replaceAllTest2() throws Exception {
        KeywordList ky = new KeywordList("key1", "key2", "key3");
        KeywordList ky_replace = new KeywordList("key5");
        Keyword ky_new = new Keyword("key4");
        KeywordList ky2 = new KeywordList("key1", "key2", "key3", "key4");
        ky.replaceAll(ky_replace, ky_new);
        assertEquals(ky2,ky);
    }

    @Test
    public void removeAllTest() throws Exception {
        KeywordList ky = new KeywordList("key1", "key2", "key3");
        KeywordList ky_remove = new KeywordList("key2");
        KeywordList ky_new = new KeywordList("key1", "key3");
        ky.removeAll(ky_remove);
        assertEquals(ky_new, ky);
    }

    @Test
    public void sizeTest() throws Exception {
        KeywordList ky = new KeywordList("key1", "key2", "key3");
        assertEquals(3, ky.size());
    }

    @Test
    public void isEmptyTest() throws Exception {
        KeywordList ky = new KeywordList();
        assertEquals(true, ky.isEmpty());
    }

    @Test
    public void containsTest() throws Exception {
        KeywordList ky = new KeywordList("key1", "key2", "key3");
        assertEquals(true, ky.contains("key2"));
    }

    @Test
    public void removeKeywordTest() throws Exception {
        KeywordList ky = new KeywordList("key1", "key2", "key3");
        assertEquals(true, ky.remove(new Keyword("key2")));
    }

    @Test
    public void removeKeywordStringTest() throws Exception {
        KeywordList ky = new KeywordList("key1", "key2", "key3");
        assertEquals(true, ky.remove("key2"));
    }

    @Test
    public void addAllTest() throws Exception {
        KeywordList ky = new KeywordList("key1", "key2", "key3");
        ky.addAll(new KeywordList("key4", "key5"));
        assertEquals(new KeywordList("key1", "key2", "key3","key4", "key5"),
                ky);
    }

    @Test
    public void clearTest() throws Exception {
        KeywordList ky = new KeywordList("key1", "key2", "key3");
        ky.clear();
        assertEquals(new KeywordList(),ky);
    }

    @Test
    public void getTest() throws Exception {
        KeywordList ky = new KeywordList("key1", "key2", "key3");
        assertEquals(new Keyword("key2"), ky.get(1));
    }

    //Part 3: End of Our New Test Cases


    @Test
    public void parseEmptyStringReturnsEmptyList() throws Exception {
        assertEquals(new KeywordList(), KeywordList.parse("", ','));
    }


    @Test
    public void parseOneWordReturnsOneKeyword() throws Exception {
        assertEquals(new KeywordList("keywordOne"),
                KeywordList.parse("keywordOne", ','));
    }

    @Test
    public void parseTwoWordReturnsTwoKeywords() throws Exception {
        assertEquals(new KeywordList("keywordOne", "keywordTwo"),
                KeywordList.parse("keywordOne, keywordTwo", ','));
    }

    @Test
    public void parseTwoWordReturnsTwoKeywordsWithoutSpace() throws Exception {
        assertEquals(new KeywordList("keywordOne", "keywordTwo"),
                KeywordList.parse("keywordOne,keywordTwo", ','));
    }

    @Test
    public void parseTwoWordReturnsTwoKeywordsWithDifferentDelimiter() throws Exception {
        assertEquals(new KeywordList("keywordOne", "keywordTwo"),
                KeywordList.parse("keywordOne| keywordTwo", '|'));
    }

    @Test
    public void parseWordsWithWhitespaceReturnsOneKeyword() throws Exception {
        assertEquals(new KeywordList("keyword and one"),
                KeywordList.parse("keyword and one", ','));
    }

    @Test
    public void parseWordsWithWhitespaceAndCommaReturnsTwoKeyword() throws Exception {
        assertEquals(new KeywordList("keyword and one", "and two"),
                KeywordList.parse("keyword and one, and two", ','));
    }

    @Test
    public void parseIgnoresDuplicates() throws Exception {
        assertEquals(new KeywordList("keywordOne", "keywordTwo"),
                KeywordList.parse("keywordOne, keywordTwo, keywordOne", ','));
    }

    @Test
    public void parseWordsWithBracketsReturnsOneKeyword() throws Exception {
        assertEquals(new KeywordList("[a] keyword"), KeywordList.parse("[a] keyword", ','));
    }

    @Test
    public void asStringAddsSpaceAfterDelimiter() throws Exception {
        assertEquals("keywordOne, keywordTwo", keywords.getAsString(','));
    }

    @Test
    public void parseHierarchicalChain() throws Exception {
        Keyword expected = Keyword.of("Parent", "Node", "Child");

        assertEquals(new KeywordList(expected), KeywordList.parse("Parent > Node > Child", ',', '>'));
    }

    @Test
    public void parseTwoHierarchicalChains() throws Exception {
        Keyword expectedOne = Keyword.of("Parent1", "Node1", "Child1");
        Keyword expectedTwo = Keyword.of("Parent2", "Node2", "Child2");

        assertEquals(new KeywordList(expectedOne, expectedTwo),
                KeywordList.parse("Parent1 > Node1 > Child1, Parent2 > Node2 > Child2", ',', '>'));
    }
}
