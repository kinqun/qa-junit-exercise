package com.qa.java;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppTest 
{
//    @BeforeAll
//    public static void beforeAll() {
//    	System.out.println("@BeforeAll call");
//    }
//    
//    @AfterAll
//    public static void afterAll() {
//    	System.out.println("@AfterAll call");
//    }
    
    @BeforeEach
    public void beforeEach() {
    	System.out.println("@BeforeEach call");
    }
    
    @AfterEach
    public void afterEach() {
    	System.out.println("@AfterEach call");
    }
    
    @Test
    public void method1() {
    	System.out.println("method 1 call");
    }
    
    @Test
    public void method2() {
    	System.out.println("method 2 call");
    }
}
