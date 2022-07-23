package com.zhengyang.mockito;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathUtilsTest {

    @Test
    void add() {
        MathUtils mathUtils = new MathUtils();
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
        MathUtils mathUtils = new MathUtils();
        assertEquals(314.1592653589793, mathUtils.computerCircleArea(10),"should return right circle area");
    }

}