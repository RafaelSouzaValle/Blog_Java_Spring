package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.domain.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {

}
