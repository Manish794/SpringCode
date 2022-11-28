package com.manish.spring.service;

import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class SecondServiceTest {
    private SecondService secondService;

    @BeforeEach
    public void init(){
        secondService = new SecondService();
    }

    @AfterEach
    public void cleanup(){
        secondService = null;
    }

    @Test
    public void testWithNull(){
        String result = secondService.prepareMessage(null);
        assertNull(result);
    }

    @Test
    public void testWithEmpty(){
        String result = secondService.prepareMessage("");
        assertNull(result);
    }
    @Test
    public void testWithSpace(){
        String result = secondService.prepareMessage("  ");
        assertNull(result);
    }

    @RepeatedTest(4)
    public void testWithName(){
        System.out.println("-- testWithName -- ");
        String result = secondService.prepareMessage("Manish");
        assertEquals("Hello Manish", result);
    }

    @RepeatedTest(value=6, name = "{displayName} - {currentRepetition}")
    @DisplayName("testWithNameRahul")
    public void testWithNameRahul(TestInfo testInfo, RepetitionInfo repInfo){
        System.out.println(testInfo.getDisplayName()+" "+ repInfo.getCurrentRepetition()+" of "+ repInfo.getTotalRepetitions());
        String result = secondService.prepareMessage("Rahul");
        assertEquals("Hello Rahul", result);
    }

    @RepeatedTest(value=3, name = "{displayName} - {currentRepetition} of {totalRepetitions}")
    @DisplayName("testWithNameKumar")
    public void testWithNameKumar(TestInfo testInfo, RepetitionInfo repInfo){
        // System.out.println(testInfo.getDisplayName()+" "+ repInfo.getCurrentRepetition()+" of "+ repInfo.getTotalRepetitions());
        String result = secondService.prepareMessage("Rahul");
        assertEquals("Hello Rahul", result);
    }

}