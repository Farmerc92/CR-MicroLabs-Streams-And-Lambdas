package com.zipcodewilmington.streams;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.stream.Stream;

public class TestStreamMap {

    private String hello = "Hello";
    private String world = "World";
    private String[] stringArray1;

    @Before
    public void setUp(){
        stringArray1 = new String[]{hello, world};
    }

    @Test
    public void testLettersHello(){
        Stream<String> expected = Stream.of("H", "e", "l", "l", "o");
        Stream<String> actual = StreamMap.letters(hello);
        Iterator<String> iteratorExpected = expected.iterator();
        Iterator<String> iteratorActual = actual.iterator();
        while (iteratorExpected.hasNext()){
            Assert.assertEquals(iteratorExpected.next(), iteratorActual.next());
        }
    }

    @Test
    public void testLettersWorld(){
        Stream<String> expected = Stream.of("W", "o", "r", "l", "d");
        Stream<String> actual = StreamMap.letters(world);
        Iterator<String> iteratorExpected = expected.iterator();
        Iterator<String> iteratorActual = actual.iterator();
        while (iteratorExpected.hasNext()){
            Assert.assertEquals(iteratorExpected.next(), iteratorActual.next());
        }
    }

    @Test
    public void testWordsMap(){
        Stream<String> expectedHello = Stream.of("H", "e", "l", "l", "o");
        Stream<String> expectedWorld = Stream.of("W", "o", "r", "l", "d");
        Stream<Stream<String>> expected = Stream.of(expectedHello, expectedWorld);
        Stream<Stream<String>> actual = StreamMap.wordsMap(hello, world);
        Iterator<Stream<String>> expectedIterator = expected.iterator();
        Iterator<Stream<String>> actualIterator = actual.iterator();
        while(expectedIterator.hasNext()){
            Iterator<String> expectedLetterIterator = expectedIterator.next().iterator();
            Iterator<String> actualLetterIterator = actualIterator.next().iterator();
            while(expectedLetterIterator.hasNext()){
                Assert.assertEquals(expectedLetterIterator.next(), actualLetterIterator.next());
            }
        }
    }

    @Test
    public void testWordsFlatMap(){
        Stream<String> expectedHelloWorld = Stream.of("H", "e", "l", "l", "o", "W", "o", "r", "l", "d");
        Stream<String> actual = StreamMap.wordsFlatMap(hello, world);
        Iterator<String> expectedIterator = expectedHelloWorld.iterator();
        Iterator<String> actualIterator = actual.iterator();
        while(expectedIterator.hasNext()){
            Assert.assertEquals(expectedIterator.next(), actualIterator.next());
        }
    }
}
