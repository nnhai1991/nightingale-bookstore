package com.nightingale.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nightingale.app.entity.Tag;

public interface TagRepository extends JpaRepository<Tag,String> {

}
