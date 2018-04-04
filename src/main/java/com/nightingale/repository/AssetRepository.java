package com.nightingale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nightingale.entity.Asset;

public interface AssetRepository extends JpaRepository<Asset,Integer> {

}
