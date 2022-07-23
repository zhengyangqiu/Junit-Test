package com.zhengyang.mockito;


import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

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

        //mock random object
//        Random random = Mockito.mock(Random.class);
//        Mockito.when(random.nextInt()).thenReturn(100);
//        System.out.println(random.nextInt());
        //验证是否用了nextint方法
//        Mockito.verify(random,Mockito.times(1)).nextInt();




    }

    @Test
    void testComputerCircleRadius(){

        assertEquals(314.1592653589793, mathUtils.computerCircleArea(10),"should return right circle area");
    }
    @Test
    void testDivide(){

        assertThrows(ArithmeticException.class,()-> mathUtils.divide(1,0),"divide by zero should throw");



    }
    @Test
    @Disabled
    @DisplayName("TDD method. should not run")
    void testDisabled(){
        fail("This test should be disabled");
    }
}