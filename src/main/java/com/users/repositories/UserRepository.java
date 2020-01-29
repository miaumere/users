package com.users.repositories;

import com.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u " +
            "WHERE (:name is  null or u.name like %:name%)" +
            "AND (:surname is  null or u.surname like %:surname%)" +
            "AND (:grade is  null or u.grade = :grade)" +
            "AND (:salary is  null or u.salary = :salary)"
    )
    List<User> getMatchingUsers(@Param("name") Optional<String> name,
                                @Param("surname") Optional<String> surname,
                                @Param("grade") Optional<Integer> grade,
                                @Param("salary") Optional<Integer> salary);
}
