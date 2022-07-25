package com.zhengyang.mockito;


import com.zhengyang.mockito.javaBrains.MathUtils;
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
    TestInfo testinfo;
    TestReporter testReporter;
    @BeforeEach
    void init(TestInfo testinfo,TestReporter testReporter){
        this.testinfo=testinfo;
        this.testReporter=testReporter;
        mathUtils = new MathUtils();
    }

    @AfterEach
    void cleanup(){
        System.out.println("cleaning up...");
    }

    @Test
    @DisplayName("test add method")
    @Tag("Math")
    void add() {

        int expected = 2;
        int actual = mathUtils.add(1,1);
        assertEquals(expected,actual,"The add method should add two numbers");

    }

    @RepeatedTest(3)
    @Tag("circle")
    void testComputerCircleRadius(RepetitionInfo repetitionInfo){

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
    @Tag("Math")
    @DisplayName("Multiple method")
    void testMultiply(){
        System.out.println("running " + testinfo.getDisplayName()+" with tags" + testinfo.getTags());
        testReporter.publishEntry("running " + testinfo.getDisplayName()+" with tags" + testinfo.getTags());

        assertAll(
                ()-> assertEquals(4,mathUtils.multiply(2,2)),
                ()-> assertEquals(0,mathUtils.multiply(0,2)),
                ()-> assertEquals(-2,mathUtils.multiply(2,-1))

        );
    }

    @Nested
    @DisplayName("Add method")
    @Tag("Math")
    class addTest{
        @Test
        @DisplayName("When adding two positive number")
        void testAddPositive(){
            assertEquals(2, mathUtils.add(1,1),"should return the right sum");

        }
        @Test
        @DisplayName("When adding two negative numbers ")
        void testAddNegative(){
            int expected=-2;
            int actual=mathUtils.add(-1,-1);
            assertEquals(-2, mathUtils.add(-1,-1),()->"should return sum"+ expected +"but return "+ actual);

        }



    }

}