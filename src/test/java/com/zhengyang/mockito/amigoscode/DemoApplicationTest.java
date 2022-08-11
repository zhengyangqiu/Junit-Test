package com.zhengyang.mockito.amigoscode;

import org.junit.jupiter.api.Test;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DemoApplicationTest {
    Calcualtor underTest = new Calcualtor();
    @Test
    void isShouldAddTwoNumber(){
        //given
        int numberOne = 20;
        int numberTwo = 30;
        //when
        int result = underTest.add(numberOne,numberTwo);
        //then
        assertThat(result).isEqualTo(50);



    }
    class Calcualtor{
        int add(int a, int b){
            return a + b;
        }
    }

}