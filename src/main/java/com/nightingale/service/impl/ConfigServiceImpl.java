package com.nightingale.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nightingale.entity.Config;
import com.nightingale.repository.ConfigRepository;
import com.nightingale.service.ConfigService;
import com.nightingale.util.web.UtilValidation;

@Service
public class ConfigServiceImpl implements ConfigService {

	@Autowired
	private ConfigRepository configRepository;

	@Override
	public Config readByCode(String key)  {

		if(UtilValidation.isValidString(key))
			return configRepository.findByCode(key);
		return null;
	}

}
