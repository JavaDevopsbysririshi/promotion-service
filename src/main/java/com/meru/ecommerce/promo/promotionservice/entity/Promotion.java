package com.meru.ecommerce.promo.promotionservice.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.web.bind.annotation.ModelAttribute;

@Entity
@Table(name = "Promotion")
public class Promotion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NumberFormat(pattern = "#,###,###,###.##")
    private int promoId;
    @Column
    private int productId;
    @Column
    private String productName; 
    @Column
    private String promoCode; 
    @Column
    private String promoDescription; 
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date promoStartDate; 
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date promoEndDate; 
    @Column
    @NumberFormat(pattern = "#,###,###,###.##")
    private int promoValue; 

    public Promotion() {
    }

    public Promotion(int productId, String productName, String promoCode, String promoDescription, Date promoStartDate,
            Date promoEndDate, int promoValue) {
        this.productId = productId;
        this.productName = productName;
        this.promoCode = promoCode;
        this.promoDescription = promoDescription;
        this.promoStartDate = promoStartDate;
        this.promoEndDate = promoEndDate;
        this.promoValue = promoValue;
    }

    public Promotion(int promoId, int productId, String productName, String promoCode, String promoDescription,
            Date promoStartDate, Date promoEndDate, int promoValue) {
        this.promoId = promoId;
        this.productId = productId;
        this.productName = productName;
        this.promoCode = promoCode;
        this.promoDescription = promoDescription;
       
        this.promoStartDate = promoStartDate;
        this.promoEndDate = promoEndDate;
        this.promoValue = promoValue;
    }

    public int getPromoId() {
        return promoId;
    }

    public void setPromoId(int promoId) {
        this.promoId = promoId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getPromoDescription() {
        return promoDescription;
    }

    public void setPromoDescription(String promoDescription) {
        this.promoDescription = promoDescription;
    }

    public Date getPromoStartDate() {
        return promoStartDate;
    }

    public void setPromoStartDate(Date promoStartDate) {
        this.promoStartDate = promoStartDate;
    }

    public Date getPromoEndDate() {
        return promoEndDate;
    }

    public void setPromoEndDate(Date promoEndDate) {
        this.promoEndDate = promoEndDate;
    }

   
    public int getPromoValue() {
        return promoValue;
    }

    public void setPromoValue(int promoValue) {
        this.promoValue = promoValue;
    }

    @Override
    public String toString() {
        return "Promotion [promoId=" + promoId + ", productId=" + productId + ", productName=" + productName
                + ", promoCode=" + promoCode + ", promoDescription=" + promoDescription + ", promoStartDate="
                + promoStartDate + ", promoEndDate=" + promoEndDate + ", promoValue=" + promoValue + "]";
    }
}
