package com.nightingale.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nightingale.entity.Config;
import com.nightingale.exception.NightingaleException;
import com.nightingale.repository.ConfigRepository;
import com.nightingale.service.ConfigService;
import com.nightingale.util.web.UtilValidation;

@Service
public class ConfigServiceImpl implements ConfigService {

	private ConfigRepository configRepository;

	@Autowired
	public ConfigServiceImpl(ConfigRepository configRepository) {
		this.configRepository = configRepository;
	}

	@Override
	public Config create(Config config) {

		if (config != null)
				return configRepository.save(config);
		return null;
	}

	@Override
	public Config read(Integer configId) {

		if (UtilValidation.isValidId(configId))
				return configRepository.findOne(configId);
		return null;
	}

	@Override
	public Config update(Config config) {

		if (config != null)
			try {
				return configRepository.save(config);
			} catch (DataIntegrityViolationException exception) {
				throw new NightingaleException(exception.getStackTrace(), "update Config", "",
						"Failed to update Config", config);
			}

		return null;
	}

	@Override
	public void delete(Integer configId) {

		if (UtilValidation.isValidId(configId)){
			configRepository.delete(configId);
		}
	}

	@Override
	public List<Config> getListAll() {

		return configRepository.findAll();
	}

	@Override
	public Pair<List<Config>, Integer> getListWithPaginationBySearch(String keyword, Integer pageNo, Integer pageSize) {
		if (pageSize > 0) {
			PageRequest pageRequest = new PageRequest(pageNo - 1, pageSize);
			Page<Config> result;
			if (UtilValidation.isValidString(keyword)) {
				result = configRepository.findBySearch(keyword, pageRequest);
			} else {
				result = configRepository.findAll(pageRequest);
			}
			return Pair.of(result.getContent(), (int) result.getTotalElements());
		}
		return Pair.of(new ArrayList<>(), 0);
	}

	@Override
	public Config readByCode(String key)  {

		if(UtilValidation.isValidString(key))
			return configRepository.findByCode(key);
		return null;
	}

}
