package com.exchangeRate.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "EXCHANGE")
public class ExchangeBean {
	private static final long serialVersionUID = 1L;
	
	public ExchangeBean() {
		
    }
	public ExchangeBean(String exchangeDate, String usdToNtd, String rmbToNtd,String usdToRmb) {
        this.exchangeDate = exchangeDate;
        this.usdToNtd = usdToNtd;
        this.rmbToNtd = rmbToNtd;
        this.usdToRmb = usdToRmb;
    }
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "EXCHANGEDATE")
	private String exchangeDate;
	
	@Column(name = "USDTONTD")
	private String usdToNtd;
	
	@Column(name = "RMBTONTD")
	private String rmbToNtd;
	
	@Column(name = "USDTORMB")
	private String usdToRmb;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getExchangeDate() {
		return exchangeDate;
	}
	public void setExchangeDate(String exchangeDate) {
		this.exchangeDate = exchangeDate;
	}
	public String getUsdToNtd() {
		return usdToNtd;
	}
	public void setUsdToNtd(String usdToNtd) {
		this.usdToNtd = usdToNtd;
	}
	public String getRmbToNtd() {
		return rmbToNtd;
	}
	public void setRmbToNtd(String rmbToNtd) {
		this.rmbToNtd = rmbToNtd;
	}
	public String getUsdToRmb() {
		return usdToRmb;
	}
	public void setUsdToRmb(String usdToRmb) {
		this.usdToRmb = usdToRmb;
	}
}