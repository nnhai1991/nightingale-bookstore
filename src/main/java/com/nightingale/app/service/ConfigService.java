package com.nightingale.app.service;

import com.nightingale.app.entity.Config;


public interface ConfigService extends BaseService<Config> {
	Config readByCode(String key) ;
}
