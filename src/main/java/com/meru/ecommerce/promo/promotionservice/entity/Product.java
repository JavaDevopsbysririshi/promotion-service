package com.meru.ecommerce.promo.promotionservice.entity;

import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private int productId;
    private String productName;
    private String category;
    private String productStatus;
    private ProductDescription productDescription;

    public Product() {
    }

    public Product(int productId, String productName, String category, String productStatus,
            ProductDescription productDescription) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.productStatus = productStatus;
        this.productDescription = productDescription;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public ProductDescription getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(ProductDescription productDescription) {
        this.productDescription = productDescription;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", productName=" + productName + ", category=" + category
                + ", productStatus=" + productStatus + ", productDescription=" + productDescription + "]";
    }
}

class ProductDescription implements Serializable {
    private static final long serialVersionUID = 1L;
    private String details; // description of the item
    private String specification; // all product specs
    private double weight; // weight in grams

    public ProductDescription() {
    }

    public ProductDescription(String details, String specification, double weight) {
        this.details = details;
        this.specification = specification;
        this.weight = weight;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "ProductDescription [details=" + details + ", specification=" + specification + ", weight=" + weight
                + "]";
    }
}
