package com.zhengyang.mockito.amigoscode.student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
        @Query("" +
                "SELECT CASE WHEN COUNT(s) > 0 THEN " +
                "TRUE ELSE FALSE END " +
                "FROM Student s " +
                "WHERE s.email = ?1"
        )
        Boolean selectExistsEmail(String email);
        //this is the only method we write by ourselves so need to test it, no need to test data jpa
        
}
