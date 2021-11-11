package com.ridgue.jangular.database.repository;

import com.ridgue.jangular.database.entity.Photo;
import com.ridgue.jangular.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findByDescription(String description);

    List<Photo> findByUserId(Long id);
}
