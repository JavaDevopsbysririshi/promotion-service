package com.meru.ecommerce.promo.promotionservice.service;

import java.util.List;

import com.meru.ecommerce.promo.promotionservice.entity.Promotion;

public interface PromotionService {
    public List<Promotion> getAllProductsPromotion();

    public Promotion getPromotionById(int promoId);

    public Promotion getPromotionByProductId(int productId);

    public boolean createOrUpdatePromotion(Promotion promo);

    public boolean removePromotion(int promoId);

}
