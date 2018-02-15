package com.nightingale.web.controller.admin;

import javax.validation.Valid;

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


import com.nightingale.app.entity.ArticleImage;
import com.nightingale.app.model.dto.ArticleImageDTO;
import com.nightingale.app.service.ArticleImageService;
import com.nightingale.app.service.ArticleService;
import com.nightingale.web.util.UtilValidation;

/**
 * 
 * @author nn.hai
 *
 */
@Controller
@RequestMapping("/admin/article-image")
public class ArticleImageController {

	private static final String IMAGE = "image";
	private static final String CAMPAIGN_IMAGE_DTO = "articleImageDTO";
	private final static String FOLDER = "/admin/article-image";

	private ArticleImageService articleImageService;
	private ArticleService articleService;

	@Value("${pageSize}")
	private Integer pageSize = 10;

	@Autowired
	public ArticleImageController(ArticleImageService articleImageService,ArticleService articleService) {
		this.articleImageService = articleImageService;
		this.articleService = articleService;
	}

	@GetMapping("/create")
	public String create(Model model,
			@RequestParam(name = "articleId", required = true, defaultValue = "-1") Integer articleId)  {
		ArticleImageDTO articleImageDTO =  new ArticleImageDTO();
		articleImageDTO.setArticle( articleService.read(articleId));

		model.addAttribute(CAMPAIGN_IMAGE_DTO, articleImageDTO);
		return FOLDER + "/create";
	}

	@PostMapping("/create")
	public String create(Model model, 
			@RequestParam(name = "articleId", required = true, defaultValue = "-1") Integer articleId, @Valid ArticleImageDTO articleImageDTO, BindingResult validResult)  {

		if (!UtilValidation.isFileNotEmpty(articleImageDTO.getImage()))
			validResult.rejectValue(IMAGE, "RequiredImage");

		articleImageDTO.setArticle( articleService.read(articleId));

		if (validResult.hasErrors()) {
			model.addAttribute(CAMPAIGN_IMAGE_DTO, articleImageDTO);
			return FOLDER + "/create";

		} else {
			articleImageService.createDTO(articleImageDTO);
			return "redirect:/admin/article/update?articleId="+articleId;
		}
	}

	@GetMapping("/update")
	public String update(Model model,
			@RequestParam(name = "articleImageId", required = true, defaultValue = "-1") Integer articleImageId)  {

		if (UtilValidation.isValidId(articleImageId)) {

			ArticleImageDTO articleImageDTO = articleImageService.readDTO(articleImageId);

			if (articleImageDTO != null) {

				model.addAttribute(CAMPAIGN_IMAGE_DTO, articleImageDTO);
				return FOLDER + "/update";
			}
		}

		return "redirect:/admin/model";
	}

	@PostMapping("/update")
	public String update(Model model, @Valid ArticleImageDTO articleImageDTO, BindingResult bindingResult)  {

		if (bindingResult.hasErrors()) {

			model.addAttribute(CAMPAIGN_IMAGE_DTO, articleImageDTO);
			return FOLDER + "/update";

		} else {

			articleImageService.updateDTO(articleImageDTO);
			return "redirect:/admin/article/update?articleId="+articleImageDTO.getArticleImage().getArticleId();

		}

	}

	@GetMapping("/delete")
	public String delete(Model model,
			@RequestParam(name = "articleImageId", required = true, defaultValue = "-1") Integer articleImageId)  {

		if (UtilValidation.isValidId(articleImageId)) {

			ArticleImageDTO articleImageDTO= articleImageService.readDTO(articleImageId);

			if (articleImageDTO != null) {
				model.addAttribute(CAMPAIGN_IMAGE_DTO, articleImageDTO);
				return FOLDER + "/delete";
			}

		}
		return  "redirect:/admin/article";

	}

	@PostMapping("/delete")
	public String delete(Model model, @ModelAttribute ArticleImageDTO articleImageDTO)  {
		if (articleImageDTO != null && articleImageDTO.getArticleImage()!=null) {

			ArticleImage m = articleImageService.read(articleImageDTO.getArticleImage().getId());
			if (m != null) {
				articleImageService.delete(m.getId());
				return "redirect:/admin/article/update?articleId="+m.getArticleId();
			} else {
				model.addAttribute(CAMPAIGN_IMAGE_DTO, articleImageDTO);
				return "redirect:/admin/article";
			}

		}

		return "redirect:/admin/article";

	}
}
