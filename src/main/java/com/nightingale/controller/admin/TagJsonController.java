package com.nightingale.controller.admin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nightingale.service.TagService;


@RestController
@RequestMapping("/data/json/tags")
public class TagJsonController {
	
	@Autowired
	private TagService tagService;
	
	@RequestMapping("")
	public List<String> getTags(@RequestParam("tagType") String tagType){
		return tagService.getTagsByTagType(tagType).stream().map(tag->tag.getName()).collect(Collectors.toList());
	}
}
