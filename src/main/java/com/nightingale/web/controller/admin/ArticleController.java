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

import com.nightingale.app.entity.Article;
import com.nightingale.app.exception.ObjectNotFoundException;
import com.nightingale.app.model.dto.ArticleDTO;
import com.nightingale.app.service.ArticleService;
import com.nightingale.web.util.Pagination;
import com.nightingale.web.util.UtilValidation;

@Controller
@RequestMapping("/admin/article")
public class ArticleController {

	private static final String ARTICLE_DTO = "articleDTO";
	private static final String ARTICLE = "article";
	private static final String PAGINATION = "pagination";
	private static final String KEYWORD = "keyword";
	private static final String ERROR = "error";
	private static final String MODELS = "articles";

	private final static String FOLDER = "/admin/article";
	
	@Autowired
	private ArticleService articleService;

	@Value("${pageSize}")
	private Integer pageSize = 10;

	@GetMapping("")
	public String home(Model model, @RequestParam(required = false, defaultValue = "") String keyword,
			@RequestParam(required = false, defaultValue = "1") Integer pageNo)  {

		if (pageNo < 1)
			pageNo = 1;

		Pair<List<com.nightingale.app.entity.Article>, Integer> result = Pair.of(new LinkedList<>(), 0);

		if (UtilValidation.isValidSearch(keyword)) {

			result = articleService.getListWithPaginationBySearch(keyword, pageNo, pageSize);
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

		model.addAttribute(ARTICLE, new Article());
		return FOLDER + "/create";
	}

	@PostMapping("/create")
	public String create(Model model, @Valid Article article, BindingResult validResult) {

		if (validResult.hasErrors()) {
			return FOLDER + "/create";
		} else {

			articleService.create(article);
			return "redirect:/model";
		}
	}

	@GetMapping("/details")
	public String details(Model model,
			@RequestParam(name = "articleId", required = true, defaultValue = "-1") Integer articleId) throws ObjectNotFoundException {

		if (UtilValidation.isValidId(articleId)) {

			ArticleDTO articleDto =  articleService.readDTO(articleId);

			if (articleDto != null) {

				model.addAttribute(ARTICLE_DTO, articleDto);
				return FOLDER + "/details";
			}

		}
		return "redirect:/model";

	}

	@GetMapping("/update")
	public String update(Model model,
			@RequestParam(name = "articleId", required = true, defaultValue = "-1") Integer articleId) throws ObjectNotFoundException {

		if (UtilValidation.isValidId(articleId)) {

			ArticleDTO m = articleService.readDTO(articleId);
			if (m != null) {
				model.addAttribute(ARTICLE_DTO, m);
				return FOLDER + "/update";
			}
		}

		return "redirect:/model";
	}

	@PostMapping("/update")
	public String update(Model model, @Valid ArticleDTO articleDto, BindingResult bindingResult) throws ObjectNotFoundException {

		if (bindingResult.hasErrors()) {
			model.addAttribute(ARTICLE_DTO, articleDto);
			return FOLDER + "/update";

		} else {
			
			articleService.update(articleDto.getArticle());
			return "redirect:/model";

		}

	}

	@GetMapping("/delete")
	public String delete(Model model,
			@RequestParam(name = "articleId", required = true, defaultValue = "-1") Integer articleId) throws ObjectNotFoundException {

		if (UtilValidation.isValidId(articleId)) {

			Article article= articleService.read(articleId);

			if (article != null) {
				model.addAttribute(ARTICLE_DTO, article);
				return FOLDER + "/delete";
			}

		}
		return "redirect:/model";

	}

	@PostMapping("/delete")
	public String delete(Model model, @ModelAttribute Article article) throws ObjectNotFoundException {

		if (article != null && article != null) {

			com.nightingale.app.entity.Article m = articleService.read(article.getId());

			if (m != null) {
				articleService.delete(m.getId());
			} else {
				model.addAttribute(ARTICLE_DTO, article);
				return FOLDER + "/delete";
			}

		}

		return "redirect:/model";

	}
}
