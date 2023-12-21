package edu.icet.repository;

import edu.icet.dao.ProfilePicEntity;
import edu.icet.dao.StudentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {

    boolean existsByUserName(String username);

    StudentEntity findByUserName(String username);
    StudentEntity findBystudentId(long id);

    @Transactional
    @Modifying
    @Query("UPDATE StudentEntity s SET s.profilePic = :newProfilePic WHERE s.studentId = :id")
    void updateProfilePic(@Param("id") Long id, @Param("newProfilePic") ProfilePicEntity newProfilePic);
}

