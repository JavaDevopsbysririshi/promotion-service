package com.meru.ecommerce.promo.promotionservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meru.ecommerce.promo.promotionservice.entity.Promotion;

@Repository("PromotionRepository")
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
    @Query("select promo from Promotion promo where promo.productId=:productId")
    public Promotion getByProductId(@Param("productId") int productId);
}
