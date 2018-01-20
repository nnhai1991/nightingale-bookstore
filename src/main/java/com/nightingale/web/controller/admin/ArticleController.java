package com.nightingale.web.controller.admin;

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

import com.nightingale.app.exception.ObjectNotFoundException;
import com.nightingale.app.model.dto.ArticleDTO;
import com.nightingale.app.service.ArticleService;
import com.nightingale.web.util.Pagination;
import com.nightingale.web.util.UtilValidation;

@Controller
@RequestMapping("/admin/article")
public class ArticleController {

	private static final String ARTICLE_DTO = "articleDTO";
	private static final String PAGINATION = "pagination";
	private static final String KEYWORD = "keyword";
	private static final String ERROR = "error";
	private static final String MODELS = "articles";

	private final static String FOLDER = "/admin/article";
	
	@Autowired
	private ArticleService modelService;

	@Value("${pageSize}")
	private Integer pageSize = 10;

	@GetMapping("")
	public String home(Model model, @RequestParam(required = false, defaultValue = "") String keyword,
			@RequestParam(required = false, defaultValue = "1") Integer pageNo)  {

		if (pageNo < 1)
			pageNo = 1;

		Pair<List<com.nightingale.app.entity.Article>, Integer> result = Pair.of(new LinkedList<>(), 0);

		if (UtilValidation.isValidSearch(keyword)) {

			result = modelService.getListWithPaginationBySearch(keyword, pageNo, pageSize);

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

		model.addAttribute(ARTICLE_DTO, new ArticleDTO());
		return FOLDER + "/create";
	}

	@PostMapping("/create")
	public String create(Model model, @Valid ArticleDTO articleDTO, BindingResult validResult) {

//		if (!UtilValidation.isFileNotEmpty(articleDTO.getImage()))
//			validResult.rejectValue(IMAGE, "RequiredImage");

		if (validResult.hasErrors()) {

			model.addAttribute(ARTICLE_DTO, articleDTO);
			return FOLDER + "/create";

		} else {

			modelService.createDTO(articleDTO);
			return "redirect:/model";
		}
	}

	@GetMapping("/details")
	public String details(Model model,
			@RequestParam(name = "articleId", required = true, defaultValue = "-1") Integer articleId) throws ObjectNotFoundException {

		if (UtilValidation.isValidId(articleId)) {

			ArticleDTO articleDTO = modelService.readDTO(articleId);

			if (articleDTO != null) {

				model.addAttribute(ARTICLE_DTO, articleDTO);
				return FOLDER + "/details";
			}

		}
		return "redirect:/model";

	}

	@GetMapping("/update")
	public String update(Model model,
			@RequestParam(name = "articleId", required = true, defaultValue = "-1") Integer articleId) throws ObjectNotFoundException {

		if (UtilValidation.isValidId(articleId)) {

			com.nightingale.app.entity.Article m = modelService.read(articleId);

			if (m != null) {

				ArticleDTO articleDTO = new ArticleDTO();
				articleDTO.setArticle(m);
				
				model.addAttribute(ARTICLE_DTO, articleDTO);
				return FOLDER + "/update";
			}
		}

		return "redirect:/model";
	}

	@PostMapping("/update")
	public String update(Model model, @Valid ArticleDTO articleDTO, BindingResult bindingResult) throws ObjectNotFoundException {

		if (bindingResult.hasErrors()) {
			model.addAttribute(ARTICLE_DTO, articleDTO);
			return FOLDER + "/update";

		} else {

			modelService.updateDTO(articleDTO);
			return "redirect:/model";

		}

	}

	@GetMapping("/delete")
	public String delete(Model model,
			@RequestParam(name = "articleId", required = true, defaultValue = "-1") Integer articleId) throws ObjectNotFoundException {

		if (UtilValidation.isValidId(articleId)) {

			ArticleDTO articleDTO= modelService.readDTO(articleId);

			if (articleDTO != null) {
				model.addAttribute(ARTICLE_DTO, articleDTO);
				return FOLDER + "/delete";
			}

		}
		return "redirect:/model";

	}

	@PostMapping("/delete")
	public String delete(Model model, @ModelAttribute ArticleDTO articleDTO) throws ObjectNotFoundException {

		if (articleDTO != null && articleDTO.getArticle() != null) {

			com.nightingale.app.entity.Article m = modelService.read(articleDTO.getArticle().getId());

			if (m != null) {
				modelService.delete(m.getId());
			} else {
				model.addAttribute(ARTICLE_DTO, articleDTO);
				return FOLDER + "/delete";
			}

		}

		return "redirect:/model";

	}
}
