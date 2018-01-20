package com.nightingale.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nightingale.app.entity.Asset;

public interface AssetRepository extends JpaRepository<Asset,Integer> {

}
