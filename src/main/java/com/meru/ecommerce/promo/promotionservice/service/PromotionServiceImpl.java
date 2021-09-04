package com.meru.ecommerce.promo.promotionservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meru.ecommerce.promo.promotionservice.dao.PromotionRepository;
import com.meru.ecommerce.promo.promotionservice.entity.Promotion;

@Service("PromotionService")
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    PromotionRepository pr;

   

    @Override
    public List<Promotion> getAllProductsPromotion() {
        return pr.findAll();
    }

    @Override
    public Promotion getPromotionById(int promoId) {
        return pr.findOne(promoId);
    }

    @Override
    public Promotion getPromotionByProductId(int productId) {
        return pr.getByProductId(productId);
    }

    @Override
    public boolean createOrUpdatePromotion(Promotion promo) {
        Promotion updatedPromotion = pr.save(promo);
        boolean status = false;
        if (null != updatedPromotion) {
            status = true;
        }
        
        return status;
    }

    @Override
    public boolean removePromotion(int promoId) {
        Promotion deletedPromotion = pr.findOne(promoId);
        pr.delete(promoId);
        boolean deleted = false;
        if (null != deletedPromotion) {
            
            deleted = true;
        }
        return deleted;
    }

  

}
