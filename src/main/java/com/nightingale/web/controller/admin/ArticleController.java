package com.nightingale.web.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nightingale.app.entity.Article;
import com.nightingale.app.model.dto.ArticleDTO;
import com.nightingale.app.service.ArticleService;
import com.nightingale.app.service.BaseService;
import com.nightingale.web.util.UtilValidation;

@Controller
@RequestMapping("/admin/article")
public class ArticleController extends CRUDController<Article, ArticleDTO>{

	private static final String ARTICLE_DTO = "articleDTO";
	private static final String ARTICLE = "article";
	private static final String MODELS = "articles";

	private final static String FOLDER = "/admin/article";

	@Autowired
	private ArticleService articleService;

	@Value("${pageSize}")
	private Integer pageSize = 10;
	
	@Override
	protected BaseService<Article> getService() {
		return articleService;
	}

	@Override
	protected int pageSize() {
		return pageSize;
	}

	@Override
	protected String listingName() {
		return MODELS;
	}

	@Override
	protected String folder() {
		return FOLDER;
	}

	@Override
	protected String entityName() {
		return ARTICLE;
	}

	@Override
	protected String dtoName() {
		return ARTICLE_DTO;
	}

	@Override
	protected Article getNewEntity() {
		return new Article();
	}

	@Override
	protected Integer getEntityId(Article entity) {
		return entity.getId();
	}

	@GetMapping("/details")
	public String details(Model model,
			@RequestParam(name = "articleId", required = true, defaultValue = "-1") Integer articleId)
			 {

		if (UtilValidation.isValidId(articleId)) {

			ArticleDTO articleDto = articleService.readDTO(articleId);

			if (articleDto != null) {

				model.addAttribute(ARTICLE_DTO, articleDto);
				return FOLDER + "/details";
			}

		}
		return "redirect:/admin/article";

	}

	@GetMapping("/update")
	public String update(Model model,
			@RequestParam(name = "articleId", required = true, defaultValue = "-1") Integer articleId)
			 {

		if (UtilValidation.isValidId(articleId) == false)
			return "redirect:/admin/article";

		ArticleDTO m = articleService.readDTO(articleId);
		if (m != null) {
			model.addAttribute(ARTICLE_DTO, m);
			return FOLDER + "/update";
		}
		
		return "redirect:/admin/article";
	}

	@PostMapping("/update")
	public String update(Model model, @Valid ArticleDTO articleDto, BindingResult bindingResult)
			 {

		if (bindingResult.hasErrors()) {
			model.addAttribute(ARTICLE_DTO, articleDto);
			return FOLDER + "/update";

		} else {

			articleService.update(articleDto.getArticle());
			return "redirect:/admin/article";

		}

	}



}
