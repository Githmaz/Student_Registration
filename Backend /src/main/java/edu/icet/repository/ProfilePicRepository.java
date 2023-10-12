package edu.icet.repository;

import edu.icet.dao.ProfilePicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfilePicRepository extends JpaRepository<ProfilePicEntity,Long> {

}
