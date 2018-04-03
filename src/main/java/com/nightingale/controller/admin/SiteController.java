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

import com.nightingale.entity.Site;
import com.nightingale.service.SiteService;
import com.nightingale.util.web.Pagination;
import com.nightingale.util.web.UtilValidation;

@Controller
@RequestMapping("/admin/site")
public class SiteController {

	private static final String MODEL = "site";
	private static final String MODELS = "sites";
	private static final String PAGINATION = "pagination";
	private static final String KEYWORD = "keyword";
	private static final String ERROR = "error";

	private final static String FOLDER = "/admin/site";

	@Autowired
	private SiteService siteService;

	@Value("${pageSize}")
	private Integer pageSize = 10;

	@GetMapping("")
	public String home(Model model, @RequestParam(required = false, defaultValue = "") String keyword,
			@RequestParam(required = false, defaultValue = "1") Integer pageNo) {

		if (pageNo < 1)
			pageNo = 1;

		Pair<List<Site>, Integer> result = Pair.of(new LinkedList<>(), 0);

		if (UtilValidation.isValidSearch(keyword)) {

			result = siteService.getListWithPaginationBySearch(keyword, pageNo, pageSize);
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
		model.addAttribute(MODEL, new Site());
		return FOLDER + "/create";
	}

	@PostMapping("/create")
	public String create(Model model, @Valid Site site, BindingResult validResult) {

		if (validResult.hasErrors()) {
			return FOLDER + "/create";
		} else {

			siteService.create(site);
			return "redirect:/admin/site";
		}
	}

	@GetMapping("/delete")
	public String delete(Model model,
			@RequestParam(name = "siteId", required = true, defaultValue = "-1") Integer siteId) {

		if (UtilValidation.isValidId(siteId)) {

			Site site = siteService.read(siteId);

			if (site != null) {
				model.addAttribute(MODEL, site);
				return FOLDER + "/delete";
			}

		}
		return "redirect:/admin/site";

	}

	@PostMapping("/delete")
	public String delete(Model model, @ModelAttribute Site site) {

		if (site != null && site != null) {

			Site m = siteService.read(site.getId());

			if (m != null) {
				siteService.delete(m.getId());
			} else {
				model.addAttribute(MODEL, site);
				return FOLDER + "/delete";
			}

		}

		return "redirect:/admin/site";

	}

	@GetMapping("/details")
	public String details(Model model,
			@RequestParam(name = "siteId", required = true, defaultValue = "-1") Integer siteId) {

		if (UtilValidation.isValidId(siteId)) {

			Site siteDto = siteService.read(siteId);

			if (siteDto != null) {

				model.addAttribute(MODEL, siteDto);
				return FOLDER + "/details";
			}

		}
		return "redirect:/admin/site";

	}

	@GetMapping("/update")
	public String update(Model model,
			@RequestParam(name = "siteId", required = true, defaultValue = "-1") Integer siteId) {

		if (UtilValidation.isValidId(siteId) == false)
			return "redirect:/admin/site";

		Site m = siteService.read(siteId);
		if (m != null) {
			model.addAttribute(MODEL, m);
			return FOLDER + "/update";
		}

		return "redirect:/admin/site";
	}

	@PostMapping("/update")
	public String update(Model model, @Valid Site siteDto, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			model.addAttribute(MODEL, siteDto);
			return FOLDER + "/update";

		} else {

			siteService.update(siteDto);
			return "redirect:/admin/site";

		}
	}
}
