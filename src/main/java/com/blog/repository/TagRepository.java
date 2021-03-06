package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.domain.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>{

}
