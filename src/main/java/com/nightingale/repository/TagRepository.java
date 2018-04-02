package com.nightingale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nightingale.entity.Tag;

public interface TagRepository extends JpaRepository<Tag,String> {

}
