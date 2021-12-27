package com.ridgue.jangular.database.repository;

import com.ridgue.jangular.database.entity.Photo;
import com.ridgue.jangular.database.entity.PhotoComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoCommentRepository extends JpaRepository<PhotoComment, Long> {

}
