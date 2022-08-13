package com.zhengyang.mockito.amigoscode.student;

import com.zhengyang.mockito.amigoscode.student.exception.BadRequestException;
import com.zhengyang.mockito.amigoscode.student.exception.StudentNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


class StudentServiceTest {
    //because studentrepository is already tested so no need autowire to add dependency injection

    private StudentService underTest;
    @Mock
    private StudentRepository studentRepository;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);//⌘ + ⌥ + V
        underTest = new StudentService(studentRepository);
    }
    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
        //close the result after test

    }


    @Test
    void getAllStudents() {
        //when
        underTest.getAllStudents();
        //then
        verify(studentRepository).findAll();
    }

    @Test

    void canAddStudent() {
        //given
        Student student = new Student(
                "Jamila",
                "Jamila@gmail.com",
                Gender.FEMALE

        );
        //when
        underTest.addStudent(student);
        //then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentArgumentCaptor.capture());
        Student capturedStudent = studentArgumentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student);


    }
    @Test
    void willThrowWhenEmailIsTaken() {
        //given
        Student student = new Student(
                "Jamila",
                "Jamila@gmail.com",
                Gender.FEMALE

        );
        given(studentRepository.selectExistsEmail(student.getEmail())) //or 'anythingstring'
                .willReturn(true);
        //when//then
        assertThatThrownBy(()->underTest.addStudent(student))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email "+ student.getEmail()+" taken");
        verify(studentRepository, never()).save(any());
        //verify it never save any student


    }

    @Test
    void canDeleteStudent() {
        //given
        long id = 10;
        given(studentRepository.existsById(id)).willReturn(true);
        //when
        underTest.deleteStudent(id);
        //then
        verify(studentRepository).deleteById(id);

    }
    @Test
    void willThrowWhenDeleteStudentNotFound() {
        //given
        long id = 20;
        given(studentRepository.existsById(id)).willReturn(false);
        //when
        //then
        assertThatThrownBy(()->underTest.deleteStudent(id))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("Student with id "+id+" does not exist");
        verify(studentRepository,never()).deleteById(any());
    }
}