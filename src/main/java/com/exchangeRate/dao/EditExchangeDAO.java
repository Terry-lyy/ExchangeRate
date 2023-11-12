package com.exchangeRate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exchangeRate.bean.ExchangeBean;

public interface EditExchangeDAO extends JpaRepository<ExchangeBean, Long> {
	public ExchangeBean findByExchangeDate(String exchangeDate);
	public List<ExchangeBean> findAll();
	public void deleteById(Integer id);
	public ExchangeBean findById(Integer id);
}