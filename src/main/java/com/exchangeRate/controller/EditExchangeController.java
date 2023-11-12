package com.exchangeRate.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exchangeRate.bean.ExchangeBean;
import com.exchangeRate.service.EditExchangeService;

@Controller
public class EditExchangeController {
	@Autowired
	private EditExchangeService editExchangeService;
	
	/**
	 * 實作查詢政府資料 API,將美金換台幣、人民幣換台幣、美金換人民幣三種
	 * 匯率資訊顯示於畫面並存於 DB,Table 自訂
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(path = "/getExchangeRateFromOpenAPI")
	public String getExchangeRateFromOpenAPI(Model model) {
		ArrayList<ExchangeBean> ebList = editExchangeService.getExchangeRateFromOpenAPI();
		model.addAttribute("ebList", ebList);
		
		return "exchangeRateFromOpenAPI";
	}
	
	/**
	 * 實作匯率資料表查詢 API,規格自訂
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(path = "/selectExchangeRate")
	public String selectExchangeRate(Model model) {
		model.addAttribute("ebList", editExchangeService.selectExchangeRate());
		
		return "selectExchangeRate";
	}
	
	/**
	 * 實作匯率資料表新增 API,規格自訂,往新增頁面
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(path = "/insertExchangeRate")
	public String insertExchangeRate(Model model) {
		model.addAttribute("exchangeBean", new ExchangeBean());
		
		return "insertExchangeRate";
	}
	
	/**
	 * 實作匯率資料表新增 API,規格自訂
	 * 
	 * @param ExchangeBean
	 * @return
	 */
	@PostMapping(path = "/insertExchangeRate")
	public String insertExchangeRate(@ModelAttribute ExchangeBean exchangeBean) {
		editExchangeService.editExchangeRate(exchangeBean);

		return "redirect:/selectExchangeRate";
	}
	
	/**
	 * 實作匯率資料表更新 API,規格自訂,往更新頁面
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/updateExchangeRate")
	public String updateExchangeRate(@RequestParam Integer id,Model model) {
		model.addAttribute("exchangeBean", editExchangeService.selectExchangeRateById(id));
		
		return "updateExchangeRate";
	}
	
	/**
	 * 實作匯率資料表更新 API,規格自訂
	 * 
	 * @param ExchangeBean
	 * @return
	 */
	@PostMapping(path = "/updateExchangeRate")
	public String updateExchangeRate(@ModelAttribute ExchangeBean exchangeBean) {
		editExchangeService.editExchangeRate(exchangeBean);

		return "redirect:/selectExchangeRate";
	}
	
	/**
	 * 實作匯率資料表刪除 API,規格自訂
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping(path = "/deleteExchangeRate")
	@ResponseBody
	public String deleteExchangeRate(@RequestParam Integer id) {
		editExchangeService.deleteExchangeRate(id);

		return "success";
	}
}