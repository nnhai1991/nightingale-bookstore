package com.nightingale.service.impl;
//package com.nightingale.app.service.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.lang3.tuple.Pair;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.nightingale.entity.TagType;
//import com.nightingale.exception.NightingaleException;
//
//import com.nightingale.repository.TagTypeRepository;
//import com.nightingale.service.TagTypeService;
//import com.nightingale.web.util.UtilValidation;
//
//@Service
//public class TagTypeServiceImpl implements TagTypeService {
//
//	@Autowired
//	private TagTypeRepository tagTypeRepository;
//
//	@Value("${asset.upload.path}")
//	private String uploadpath;
//
//	@Override
//	@Transactional
//	public TagType create(TagType tagType) {
//
//		if (tagType != null) {
//			try {
//				tagType.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
//				return tagTypeRepository.save(tagType);
//			} catch (DataIntegrityViolationException exception) {
//				throw new NightingaleException(exception.getStackTrace(), "create TagType", "",
//						"Failed to create TagType", tagType);
//			}
//		}
//
//		return null;
//	}
//
//	@Override
//	public TagType read(Integer tagTypeId) {
//		if (UtilValidation.isValidId(tagTypeId))
//			return tagTypeRepository.findOne(tagTypeId);
//		return null;
//	}
//
//	@Override
//	@Transactional
//	public TagType update(TagType tagType) {
//		if (tagType != null) {
//			try {
//				tagType.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
//				return tagTypeRepository.save(tagType);
//			} catch (DataIntegrityViolationException exception) {
//				throw new NightingaleException(exception.getStackTrace(), "update TagType", "",
//						"Failed to update TagType", tagType);
//			}
//		}
//
//		return null;
//	}
//
//	@Override
//	public void delete(Integer tagTypeId) {
//
//		if (UtilValidation.isValidId(tagTypeId))
//			tagTypeRepository.delete(tagTypeId);
//	}
//
//	@Override
//	public List<TagType> getListAll() {
//		return tagTypeRepository.findAll();
//	}
//
//	@Override
//	public Pair<List<TagType>, Integer> getListWithPaginationBySearch(String keyword, Integer pageNo,
//			Integer pageSize) {
//		if (pageSize > 0) {
//			PageRequest pageRequest = new PageRequest(pageNo - 1, pageSize);
//			Page<TagType> result;
////			if (UtilValidation.isValidString(keyword)) {
////				result = tagTypeRepository.findBySearch(keyword, pageRequest);
////			} else {
//				result = tagTypeRepository.findAll(pageRequest);
////			}
//			return Pair.of(result.getContent(), (int) result.getTotalElements());
//		}
//		return Pair.of(new ArrayList<>(), 0);
//	}
//}
