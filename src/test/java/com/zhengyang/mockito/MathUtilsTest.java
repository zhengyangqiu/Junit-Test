package com.zhengyang.mockito;


import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@DisplayName("when running mathutils")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//only create instance for once no matter the number of methods
class MathUtilsTest {
    //static method  has no  dependency on the instance, we can run static method before mathutilstest instance created
    @BeforeAll
    static void beforeAllInt(){
        System.out.println("This needs to run before all ");
    }
    MathUtils mathUtils;
    @BeforeEach
    void init(){
        mathUtils = new MathUtils();
    }

    @AfterEach
    void cleanup(){
        System.out.println("cleaning up...");
    }

    @Test
    @DisplayName("test add method")
    void add() {

        int expected = 2;
        int actual = mathUtils.add(1,1);
        assertEquals(expected,actual,"The add method should add two numbers");

    }

    @Test
    void testComputerCircleRadius(){

        assertEquals(314.1592653589793, mathUtils.computerCircleArea(10),"should return right circle area");
    }
    @Test
    void testDivide(){
        boolean isServerUp = false;
        assumeTrue(isServerUp);

        assertThrows(ArithmeticException.class,()-> mathUtils.divide(1,0),"divide by zero should throw");



    }
    @Test
    @Disabled
    @DisplayName("TDD method. should not run")
    void testDisabled(){
        fail("This test should be disabled");
    }


    @Test
    @DisplayName("Multiple method")
    void testMultiply(){

        assertAll(
                ()-> assertEquals(4,mathUtils.multiply(2,2)),
                ()-> assertEquals(0,mathUtils.multiply(0,2)),
                ()-> assertEquals(-2,mathUtils.multiply(2,-1))

        );
    }

    @Nested
    @DisplayName("Add method")
    class addTest{
        @Test
        @DisplayName("When adding two positive number")
        void testAddPositive(){
            assertEquals(2, mathUtils.add(1,1),"should return the right sum");

        }
        @Test
        @DisplayName("When adding two negative numbers ")
        void testAddNegative(){
            assertEquals(-2, mathUtils.add(-1,-1),"should return the right sum");

        }



    }

}