package com.manish.spring.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(Parameterized.class)
class FirstServiceTest {
    private FirstService firstService;

    @BeforeEach
    public void init(){
        firstService = new FirstService();
    }

    @AfterEach
    public void cleanup(){
        firstService = null;
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    // @NullAndEmptySource
    void testData(String name){
        System.out.println("Name "+ name);
        boolean result = firstService.isEmpty(name);
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Manish","Raj","Kumar","OK"})
    void testDataWithValue(String name){
        System.out.println("Name Value "+ name);
        boolean result = firstService.isEmpty(name);
        assertFalse(result);
    }

    @ParameterizedTest
    @MethodSource("getTestData")
    void testDataWithValueFromMethod(String name){
        System.out.println("Name Value "+ name);
        boolean result = firstService.isEmpty(name);
        assertFalse(result);
    }

    static Stream<String> getTestData(){
        return Stream.of("Rahul","Ram","Hello");
    }

    @ParameterizedTest
    @MethodSource("getTestDataForLength")
    void testFindLength(String name, int expectedLength){
        int result = firstService.findLength(name);
        assertEquals(expectedLength, result);
    }

    static Stream<Arguments> getTestDataForLength(){
        return Stream.of(
                Arguments.arguments(null, -1),
                Arguments.arguments("", -1),
                Arguments.arguments("   ", -1),
                Arguments.arguments("ABC", 3),
                Arguments.arguments("ok", 2),
                Arguments.arguments("manish", 6)
        );
    }

    @ParameterizedTest
    @CsvSource({
            "null, 4",
            "xyz, 3",
            "hello, 5"
    })
    void testFindLengthWithCsvData(String name, int expectedLength){
        int result = firstService.findLength(name);
        assertEquals(expectedLength, result);
    }






}