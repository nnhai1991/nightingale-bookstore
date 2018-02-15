package com.nightingale.web.controller.admin;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nightingale.app.service.BaseService;
import com.nightingale.web.util.Pagination;
import com.nightingale.web.util.UtilValidation;

public abstract class CRUDController<BaseEntity,DTO> {
	protected static final String PAGINATION = "pagination";
	protected static final String KEYWORD = "keyword";
	protected static final String ERROR = "error";

	protected abstract BaseService<BaseEntity> BaseEntity();
	protected abstract int pageSize();
	protected abstract String listingName();
	protected abstract String folder();
	protected abstract String entityName();
	protected abstract String dtoName();
	protected abstract E getNewEntity();
	protected abstract Integer getEntityId(E entity);
	
	@GetMapping("")
	public String home(Model model, @RequestParam(required = false, defaultValue = "") String keyword,
			@RequestParam(required = false, defaultValue = "1") Integer pageNo) {

		if (pageNo < 1)
			pageNo = 1;

		Pair<List<E>, Integer> result = Pair.of(new LinkedList<>(), 0);

		if (UtilValidation.isValidSearch(keyword)) {

			result = getService().getListWithPaginationBySearch(keyword, pageNo, pageSize());
			model.addAttribute(listingName(), result.getLeft());

		} else {
			model.addAttribute(ERROR, "invalid_search");
			keyword = "";
		}

		model.addAttribute(KEYWORD, keyword);
		model.addAttribute(PAGINATION, new Pagination(pageNo, result.getRight(), pageSize()));

		return folder() + "/home";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute(entityName(), getNewEntity());
		return folder() + "/create";
	}

	@PostMapping("/create")
	public String create(Model model, @Valid E article, BindingResult validResult) {

		if (validResult.hasErrors()) {
			return folder() + "/create";
		} else {

			getService().create(article);
			return "redirect:/admin/article";
		}
	}
	
	@GetMapping("/delete")
	public String delete(Model model,
			@RequestParam(name = "articleId", required = true, defaultValue = "-1") Integer articleId)
			 {

		if (UtilValidation.isValidId(articleId)) {

			E article = getService().read(articleId);

			if (article != null) {
				model.addAttribute(dtoName(), article);
				return folder() + "/delete";
			}

		}
		return "redirect:/admin/article";

	}

	@PostMapping("/delete")
	public String delete(Model model, @ModelAttribute E entity)  {

		if (entity != null && entity != null) {

			E m = getService().read(getEntityId(entity));

			if (m != null) {
				getService().delete(getEntityId(m));
			} else {
				model.addAttribute(dtoName(), entity);
				return folder() + "/delete";
			}

		}

		return "redirect:/admin/article";

	}
}
