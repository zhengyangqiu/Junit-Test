package com.zhengyang.mockito.amigoscode.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
// connect h2 database
class StudentRepositoryTest {
    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
        //for each test, we have clean state
    }

    @Test
    void ItShouldCheckIfStudentEmailExists() {
        //given
        String email = "Jamila@gmail.com";
        Student student = new Student(
                "Jamila",email ,Gender.MALE
        );
        underTest.save(student);
        //when
        boolean expected = underTest.selectExistsEmail(email);

        //then
        assertThat(expected).isTrue();
    }

    @Test
    void ItShouldCheckIfStudentEmailDoesNotExists() {
        //given
        String email = "Jamila@gmail.com";

        //when
        boolean expected = underTest.selectExistsEmail(email);

        //then
        assertThat(expected).isFalse();
    }

}