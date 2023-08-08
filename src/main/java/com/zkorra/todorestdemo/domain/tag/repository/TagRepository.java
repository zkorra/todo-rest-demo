package com.zkorra.todorestdemo.domain.tag.repository;

import com.zkorra.todorestdemo.domain.tag.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, String> {
}
