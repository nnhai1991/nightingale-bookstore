package com.nightingale.controller.admin;
//package com.nightingale.web.controller.admin;
//
//import java.util.LinkedList;
//import java.util.List;
//
//import javax.validation.Valid;
//
//import org.apache.commons.lang3.tuple.Pair;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.nightingale.entity.TagType;
//import com.nightingale.service.TagTypeService;
//import com.nightingale.web.util.Pagination;
//import com.nightingale.web.util.UtilValidation;
//
//@Controller
//@RequestMapping("/admin/tagType")
//public class TagTypeController {
//
//	private static final String TAG_TYPE_DTO = "tagTypeDTO";
//	private static final String TAG_TYPE = "tagType";
//	private static final String PAGINATION = "pagination";
//	private static final String KEYWORD = "keyword";
//	private static final String ERROR = "error";
//	private static final String MODELS = "tagTypes";
//
//	private final static String FOLDER = "/admin/tagType";
//
//	@Autowired
//	private TagTypeService tagTypeService;
//
//	@Value("${pageSize}")
//	private Integer pageSize = 10;
//
//	@GetMapping("")
//	public String home(Model model, @RequestParam(required = false, defaultValue = "") String keyword,
//			@RequestParam(required = false, defaultValue = "1") Integer pageNo) {
//
//		if (pageNo < 1)
//			pageNo = 1;
//
//		Pair<List<com.nightingale.app.entity.TagType>, Integer> result = Pair.of(new LinkedList<>(), 0);
//
//		if (UtilValidation.isValidSearch(keyword)) {
//
//			result = tagTypeService.getListWithPaginationBySearch(keyword, pageNo, pageSize);
//			model.addAttribute(MODELS, result.getLeft());
//
//		} else {
//			model.addAttribute(ERROR, "invalid_search");
//			keyword = "";
//		}
//
//		model.addAttribute(KEYWORD, keyword);
//		model.addAttribute(PAGINATION, new Pagination(pageNo, result.getRight(), pageSize));
//
//		return FOLDER + "/home";
//	}
//
//	@GetMapping("/create")
//	public String create(Model model) {
//
//		model.addAttribute(TAG_TYPE, new TagType());
//		return FOLDER + "/create";
//	}
//
//	@PostMapping("/create")
//	public String create(Model model, @Valid TagType tagType, BindingResult validResult) {
//
//		if (validResult.hasErrors()) {
//			return FOLDER + "/create";
//		} else {
//
//			tagTypeService.create(tagType);
//			return "redirect:/admin/tagType";
//		}
//	}
//
//	@GetMapping("/details")
//	public String details(Model model,
//			@RequestParam(name = "tagTypeId", required = true, defaultValue = "-1") Integer tagTypeId)
//			{
//
//		if (UtilValidation.isValidId(tagTypeId)) {
//
//			TagType tagType = tagTypeService.read(tagTypeId);
//
//			if (tagType != null) {
//
//				model.addAttribute(TAG_TYPE_DTO, tagType);
//				return FOLDER + "/details";
//			}
//
//		}
//		return "redirect:/admin/tagType";
//
//	}
//
//	@GetMapping("/update")
//	public String update(Model model,
//			@RequestParam(name = "tagTypeId", required = true, defaultValue = "-1") Integer tagTypeId)
//			 {
//
//		if (UtilValidation.isValidId(tagTypeId) == false)
//			return "redirect:/admin/tagType";
//
//		TagType m = tagTypeService.read(tagTypeId);
//		if (m != null) {
//			model.addAttribute(TAG_TYPE_DTO, m);
//			return FOLDER + "/update";
//		}
//		
//		return "redirect:/admin/tagType";
//	}
//
//	@PostMapping("/update")
//	public String update(Model model, @Valid TagType tagType, BindingResult bindingResult)
//			 {
//
//		if (bindingResult.hasErrors()) {
//			model.addAttribute(TAG_TYPE_DTO, tagType);
//			return FOLDER + "/update";
//
//		} else {
//
//			tagTypeService.update(tagType);
//			return "redirect:/admin/tagType";
//
//		}
//
//	}
//
//	@GetMapping("/delete")
//	public String delete(Model model,
//			@RequestParam(name = "tagTypeId", required = true, defaultValue = "-1") Integer tagTypeId)
//			 {
//
//		if (UtilValidation.isValidId(tagTypeId)) {
//
//			TagType tagType = tagTypeService.read(tagTypeId);
//
//			if (tagType != null) {
//				model.addAttribute(TAG_TYPE_DTO, tagType);
//				return FOLDER + "/delete";
//			}
//
//		}
//		return "redirect:/admin/tagType";
//
//	}
//
//	@PostMapping("/delete")
//	public String delete(Model model, @ModelAttribute TagType tagType)  {
//
//		if (tagType != null && tagType != null) {
//
//			com.nightingale.app.entity.TagType m = tagTypeService.read(tagType.getId());
//
//			if (m != null) {
//				tagTypeService.delete(m.getId());
//			} else {
//				model.addAttribute(TAG_TYPE_DTO, tagType);
//				return FOLDER + "/delete";
//			}
//
//		}
//
//		return "redirect:/admin/tagType";
//
//	}
//}
