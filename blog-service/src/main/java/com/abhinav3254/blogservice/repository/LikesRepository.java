package com.abhinav3254.blogservice.repository;

import com.abhinav3254.blogservice.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<Likes,Long> {
}
