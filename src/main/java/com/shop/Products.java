package com.shop;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String type;
	
	@Column(nullable = false)
	private double MSRP;
	
	@Column(nullable = false)
	private double price;
	
	@Column(nullable = false)
	private String imgaddr;
	
	@Column(nullable = false)
	private String rating;
	
	@Column(nullable = false)
	private String pkmdesc;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getMSRP() {
		return MSRP;
	}

	public void setMSRP(double mSRP) {
		MSRP = mSRP;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImgAddr() {
		return imgaddr;
	}

	public void setImgAddr(String imgAddr) {
		this.imgaddr = imgAddr;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getPkmDesc() {
		return pkmdesc;
	}

	public void setPkmDesc(String pkmDesc) {
		this.pkmdesc = pkmDesc;
	}
	
}
