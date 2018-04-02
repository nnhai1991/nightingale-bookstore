package com.nightingale.service;

import com.nightingale.entity.Config;


public interface ConfigService extends BaseService<Config> {
	Config readByCode(String key) ;
}
