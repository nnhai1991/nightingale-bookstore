package com.nightingale.controller.admin;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nightingale.entity.Discount;
import com.nightingale.service.ArticleService;
import com.nightingale.service.SiteService;
import com.nightingale.service.DiscountService;
import com.nightingale.util.web.Pagination;
import com.nightingale.util.web.UtilValidation;

@Controller
@RequestMapping("/admin/discount")
public class DiscountController {

	private static final String MODEL = "discount";
	private static final String MODELS = "discounts";
	private static final String ARTICLES = "articles";
	private static final String SITES = "sites";

	private static final String PAGINATION = "pagination";
	private static final String KEYWORD = "keyword";
	private static final String ERROR = "error";

	private final static String FOLDER = "/admin/discount";
	

	@Autowired
	private DiscountService discountService;
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private ArticleService articleService;

	@Value("${pageSize}")
	private Integer pageSize = 10;

	@GetMapping("")
	public String home(Model model, @RequestParam(required = false, defaultValue = "") String keyword,
			@RequestParam(required = false, defaultValue = "1") Integer pageNo) {

		if (pageNo < 1)
			pageNo = 1;

		Pair<List<Discount>, Integer> result = Pair.of(new LinkedList<>(), 0);

		if (UtilValidation.isValidSearch(keyword)) {

			result = discountService.getListWithPaginationBySearch(keyword, pageNo, pageSize);
			model.addAttribute(MODELS, result.getLeft());

		} else {
			model.addAttribute(ERROR, "invalid_search");
			keyword = "";
		}

		model.addAttribute(KEYWORD, keyword);
		model.addAttribute(PAGINATION, new Pagination(pageNo, result.getRight(), pageSize));

		return FOLDER + "/home";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute(MODEL, new Discount());
		model.addAttribute(ARTICLES, articleService.getAll());
		model.addAttribute(SITES, siteService.getAll());
		return FOLDER + "/create";
	}

	@PostMapping("/create")
	public String create(Model model, @Valid Discount discount, BindingResult bindingResult) {
		if (discount.getStartDateLocal()==null)
			bindingResult.rejectValue("startDateLocal", "NotNull");
		if (discount.getEndDateLocal()==null)
			bindingResult.rejectValue("endDateLocal", "NotNull");
		if (bindingResult.hasErrors()) {
			model.addAttribute(ARTICLES, articleService.getAll());
			model.addAttribute(SITES, siteService.getAll());
			return FOLDER + "/create";
		} else {

			discountService.create(discount);
			return "redirect:/admin/discount";
		}
	}

	@GetMapping("/delete")
	public String delete(Model model,
			@RequestParam(name = "discountId", required = true, defaultValue = "-1") Integer discountId) {

		if (UtilValidation.isValidId(discountId)) {

			Discount discount = discountService.read(discountId);

			if (discount != null) {
				model.addAttribute(MODEL, discount);
				return FOLDER + "/delete";
			}

		}
		return "redirect:/admin/discount";

	}

	@PostMapping("/delete")
	public String delete(Model model, @ModelAttribute Discount discount) {

		if (discount != null && discount != null) {

			Discount m = discountService.read(discount.getId());

			if (m != null) {
				discountService.delete(m.getId());
			} else {
				model.addAttribute(MODEL, discount);
				return FOLDER + "/delete";
			}

		}

		return "redirect:/admin/discount";

	}

	@GetMapping("/details")
	public String details(Model model,
			@RequestParam(name = "discountId", required = true, defaultValue = "-1") Integer discountId) {

		if (UtilValidation.isValidId(discountId)) {

			Discount discount = discountService.read(discountId);

			if (discount != null) {

				model.addAttribute(MODEL, discount);
				return FOLDER + "/details";
			}

		}
		return "redirect:/admin/discount";

	}

	@GetMapping("/update")
	public String update(Model model,
			@RequestParam(name = "discountId", required = true, defaultValue = "-1") Integer discountId) {

		if (UtilValidation.isValidId(discountId) == false)
			return "redirect:/admin/discount";

		Discount m = discountService.read(discountId);
		if (m != null) {
			model.addAttribute(ARTICLES, articleService.getAll());
			model.addAttribute(SITES, siteService.getAll());
			model.addAttribute(MODEL, m);
			return FOLDER + "/update";
		}

		return "redirect:/admin/discount";
	}

	@PostMapping("/update")
	public String update(Model model, @Valid Discount discount, BindingResult bindingResult) {
		if (discount.getStartDateLocal()==null)
			bindingResult.rejectValue("startDateLocal", "NotNull");
		if (discount.getEndDateLocal()==null)
			bindingResult.rejectValue("endDateLocal", "NotNull");
		if (bindingResult.hasErrors()) {
			model.addAttribute(ARTICLES, articleService.getAll());
			model.addAttribute(SITES, siteService.getAll());
			model.addAttribute(MODEL, discount);
			return FOLDER + "/update";
		} else {
			discountService.update(discount);
			return "redirect:/admin/discount";
		}
	}
}
