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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nightingale.app.entity.Article;
import com.nightingale.app.entity.ArticleImage;
import com.nightingale.app.model.dto.ArticleDTO;
import com.nightingale.app.model.dto.ArticleImageDTO;
import com.nightingale.app.service.ArticleService;
import com.nightingale.web.util.Pagination;
import com.nightingale.web.util.UtilValidation;

@Controller
@RequestMapping("/admin/article")
public class ArticleController {

	private static final String ARTICLE_DTO = "articleDTO";
	private static final String ARTICLE_IMAGE_DTO = "articleImageDTO";
	private static final String MODELS = "articles";
	private static final String PAGINATION = "pagination";
	private static final String KEYWORD = "keyword";
	private static final String ERROR = "error";

	private final static String FOLDER = "/admin/article";

	@Autowired
	private ArticleService articleService;

	@Value("${pageSize}")
	private Integer pageSize = 10;

	@GetMapping("")
	public String home(Model model, @RequestParam(required = false, defaultValue = "") String keyword,
			@RequestParam(required = false, defaultValue = "1") Integer pageNo) {

		if (pageNo < 1)
			pageNo = 1;

		Pair<List<Article>, Integer> result = Pair.of(new LinkedList<>(), 0);

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
		model.addAttribute(ARTICLE_DTO, new ArticleDTO());
		return FOLDER + "/create";
	}

	@PostMapping("/create")
	public String create(Model model, @Valid ArticleDTO articleDTO, BindingResult validResult) {

		if (validResult.hasErrors()) {
			return FOLDER + "/create";
		} else {

			articleService.create(articleDTO);
			return "redirect:/admin/article";
		}
	}

	@GetMapping("/delete")
	public String delete(Model model,
			@RequestParam(name = "articleId", required = true, defaultValue = "-1") Integer articleId) {

		if (UtilValidation.isValidId(articleId)) {

			Article article = articleService.read(articleId);

			if (article != null) {
				model.addAttribute(ARTICLE_DTO, article);
				return FOLDER + "/delete";
			}

		}
		return "redirect:/admin/article";

	}

	@PostMapping("/delete")
	public String delete(Model model, @ModelAttribute Article article) {

		if (article != null && article != null) {

			Article m = articleService.read(article.getId());

			if (m != null) {
				articleService.delete(m.getId());
			} else {
				model.addAttribute(ARTICLE_DTO, article);
				return FOLDER + "/delete";
			}

		}

		return "redirect:/admin/article";

	}

	@GetMapping("/details")
	public String details(Model model,
			@RequestParam(name = "articleId", required = true, defaultValue = "-1") Integer articleId) {

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
			@RequestParam(name = "articleId", required = true, defaultValue = "-1") Integer articleId) {

		if (UtilValidation.isValidId(articleId) == false)
			return "redirect:/admin/article";

		ArticleDTO m = articleService.readDTO(articleId);
		if (m != null) {
			model.addAttribute(ARTICLE_DTO, m);
			model.addAttribute(ARTICLE_IMAGE_DTO, new ArticleImageDTO());
			return FOLDER + "/update";
		}

		return "redirect:/admin/article";
	}

	@PostMapping("/update")
	public String update(Model model, @Valid ArticleDTO articleDto, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			model.addAttribute(ARTICLE_DTO, articleDto);
			model.addAttribute(ARTICLE_IMAGE_DTO, new ArticleImageDTO());
			return FOLDER + "/update";

		} else {

			articleService.update(articleDto);
			return "redirect:/admin/article";

		}
	}
	
	@GetMapping("/create-image")
	public String createImage(Model model,
			@RequestParam(name = "articleId", required = true, defaultValue = "-1") Integer articleId) {

		if (UtilValidation.isValidId(articleId) == false)
			return "redirect:/admin/article";

		ArticleDTO m = articleService.readDTO(articleId);
		if (m != null) {
			model.addAttribute(ARTICLE_DTO, m);
			model.addAttribute(ARTICLE_IMAGE_DTO, new ArticleImageDTO());
			return "/admin/article-image/create";
		}

		return "redirect:/admin/article";
	}

	@PostMapping("/create-image")
	public String createImage(Model model, @Valid ArticleImageDTO articleImageDTO, BindingResult validResult,
			RedirectAttributes redirectAttributes) {

		if (!UtilValidation.isFileNotEmpty(articleImageDTO.getImage()))
			validResult.rejectValue("image", "RequiredImage");
		
		if (validResult.hasErrors()) {
			redirectAttributes.addAttribute(ERROR, "something_went_wrong");
		} else {
			articleService.createArticleImageDTO(articleImageDTO);
			return "redirect:/admin/article/update?articleId=" + articleImageDTO.getArticleId();
		}
		return "redirect:/admin/article/update?articleId=" + articleImageDTO.getArticleId();

	}

	@PostMapping("/update-image")
	public String updateImage(Model model, @Valid ArticleImageDTO articleImageDTO, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			redirectAttributes.addAttribute(ERROR, "something_went_wrong");
		} else {
			articleService.updateArticleImageDTO(articleImageDTO);
		}
		return "redirect:/admin/article/update?articleId=" + articleImageDTO.getArticleId();

	}

	@PostMapping("/delete-image")
	public String deleteImage(Model model,
			@RequestParam(name = "articleImageId", required = true, defaultValue = "-1") Integer articleImageId,
			RedirectAttributes redirectAttributes) {
		if (articleImageId != null) {

			ArticleImage m = articleService.readArticleImage(articleImageId);
			if (m != null) {
				articleService.deleteArticleImage(articleImageId);

			} else {
				redirectAttributes.addAttribute(ERROR, "something_went_wrong");
			}
			return "redirect:/admin/article/update?articleId=" + m.getArticleId();

		}

		return "redirect:/admin/article";

	}
}
