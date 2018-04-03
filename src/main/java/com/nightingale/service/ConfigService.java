package com.nightingale.service;

import com.nightingale.entity.Config;


public interface ConfigService{
	Config readByCode(String key) ;
}
