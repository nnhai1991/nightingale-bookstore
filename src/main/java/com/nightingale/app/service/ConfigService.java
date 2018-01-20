package com.nightingale.app.service;

import com.nightingale.app.entity.Config;
import com.nightingale.app.exception.ObjectNotFoundException;

public interface ConfigService extends BaseService<Config> {
	Config readByKey(String key) throws ObjectNotFoundException;
}
