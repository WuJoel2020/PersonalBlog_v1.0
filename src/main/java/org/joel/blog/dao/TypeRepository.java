package org.joel.blog.dao;

import org.joel.blog.pojo.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {
    Type findByName(String name);
    Type getById(Long id);
}
