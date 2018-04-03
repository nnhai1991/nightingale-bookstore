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

import com.nightingale.entity.Stock;
import com.nightingale.service.StockService;
import com.nightingale.util.web.Pagination;
import com.nightingale.util.web.UtilValidation;

@Controller
@RequestMapping("/admin/stock")
public class StockController {

	private static final String MODEL = "stock";
	private static final String MODELS = "stocks";
	private static final String PAGINATION = "pagination";
	private static final String KEYWORD = "keyword";
	private static final String ERROR = "error";

	private final static String FOLDER = "/admin/stock";

	@Autowired
	private StockService stockService;

	@Value("${pageSize}")
	private Integer pageSize = 10;

	@GetMapping("")
	public String home(Model model, @RequestParam(required = false, defaultValue = "") String keyword,
			@RequestParam(required = false, defaultValue = "1") Integer pageNo) {

		if (pageNo < 1)
			pageNo = 1;

		Pair<List<Stock>, Integer> result = Pair.of(new LinkedList<>(), 0);

		if (UtilValidation.isValidSearch(keyword)) {

			result = stockService.getListWithPaginationBySearch(keyword, pageNo, pageSize);
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
		model.addAttribute(MODEL, new Stock());
		return FOLDER + "/create";
	}

	@PostMapping("/create")
	public String create(Model model, @Valid Stock stock, BindingResult validResult) {

		if (validResult.hasErrors()) {
			return FOLDER + "/create";
		} else {

			stockService.create(stock);
			return "redirect:/admin/stock";
		}
	}

	@GetMapping("/delete")
	public String delete(Model model,
			@RequestParam(name = "stockId", required = true, defaultValue = "-1") Integer stockId) {

		if (UtilValidation.isValidId(stockId)) {

			Stock stock = stockService.read(stockId);

			if (stock != null) {
				model.addAttribute(MODEL, stock);
				return FOLDER + "/delete";
			}

		}
		return "redirect:/admin/stock";

	}

	@PostMapping("/delete")
	public String delete(Model model, @ModelAttribute Stock stock) {

		if (stock != null && stock != null) {

			Stock m = stockService.read(stock.getId());

			if (m != null) {
				stockService.delete(m.getId());
			} else {
				model.addAttribute(MODEL, stock);
				return FOLDER + "/delete";
			}

		}

		return "redirect:/admin/stock";

	}

	@GetMapping("/details")
	public String details(Model model,
			@RequestParam(name = "stockId", required = true, defaultValue = "-1") Integer stockId) {

		if (UtilValidation.isValidId(stockId)) {

			Stock stockDto = stockService.read(stockId);

			if (stockDto != null) {

				model.addAttribute(MODEL, stockDto);
				return FOLDER + "/details";
			}

		}
		return "redirect:/admin/stock";

	}

	@GetMapping("/update")
	public String update(Model model,
			@RequestParam(name = "stockId", required = true, defaultValue = "-1") Integer stockId) {

		if (UtilValidation.isValidId(stockId) == false)
			return "redirect:/admin/stock";

		Stock m = stockService.read(stockId);
		if (m != null) {
			model.addAttribute(MODEL, m);
			return FOLDER + "/update";
		}

		return "redirect:/admin/stock";
	}

	@PostMapping("/update")
	public String update(Model model, @Valid Stock stockDto, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			model.addAttribute(MODEL, stockDto);
			return FOLDER + "/update";

		} else {

			stockService.update(stockDto);
			return "redirect:/admin/stock";

		}
	}
}
