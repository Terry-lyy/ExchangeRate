package com.exchangeRate.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.exchangeRate.bean.ExchangeBean;
import com.exchangeRate.dao.EditExchangeDAO;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EditExchangeService {
	@Autowired
	private EditExchangeDAO editExchangeDAO;
	
	private final String API_URL = "https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates";

	public ArrayList<ExchangeBean> getExchangeRateFromOpenAPI() {
		 ArrayList<ExchangeBean> ebList=new ArrayList<ExchangeBean>();
		 
		 try {
	            URL url = new URL(API_URL);

	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

	            connection.setRequestMethod("GET");

	            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            String line;
	            StringBuilder response = new StringBuilder();

	            while ((line = reader.readLine()) != null) {
	                response.append(line);
	            }
	            reader.close();

	            connection.disconnect();
	            String responseString=response.toString();

	            Gson gson = new Gson();
	            JsonArray jsonArray = gson.fromJson(responseString, JsonArray.class);

	            for (JsonElement element : jsonArray) {
	                JsonObject jsonObject = element.getAsJsonObject();
	                String date = jsonObject.get("Date").getAsString();
	                String usdToNtd = jsonObject.get("USD/NTD").getAsString();
	                String rmbToNtd = jsonObject.get("RMB/NTD").getAsString();
	                String usdToRmb = jsonObject.get("USD/RMB").getAsString();

	                ExchangeBean eb = new ExchangeBean(date,usdToNtd,rmbToNtd,usdToRmb);
	                ebList.add(eb);
	                
		            if(editExchangeDAO.findByExchangeDate(date)==null) {
			            editExchangeDAO.save(eb);
		            }		            
	            }	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		 return ebList;
	}
	
	public List<ExchangeBean> selectExchangeRate() {
		return editExchangeDAO.findAll();
	}
	
	public void deleteExchangeRate(Integer id) {
		editExchangeDAO.deleteById(id);
	}
	
	public void editExchangeRate(ExchangeBean exchangeBean) {
		editExchangeDAO.save(exchangeBean);
	}
	
	public ExchangeBean selectExchangeRateById(Integer id) {
		return editExchangeDAO.findById(id);
	}
}