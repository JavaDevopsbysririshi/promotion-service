package com.meru.ecommerce.promo.promotionservice.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.meru.ecommerce.promo.promotionservice.entity.Promotion;
import com.meru.ecommerce.promo.promotionservice.service.PromotionService;

@Controller
public class PromotionController {
    static String WELCOME_MSG = "Welcome to Product Promotion Service. This is not a valid service path.";
    static String CREATE_SUB_COMPONENT_MSG = "Create %s for Promotion %d in Promotion is %s";
    static String UPDATE_SUB_COMPONENT_MSG = "Update %s for Promotion %d in Promotion is %s";
    static String DELETE_MSG = "Delete Promotion for PromotionId: %s %s";
    static String SUCCESS = "Success";
    static String ERROR = "Failed";
    static String COMPONENT_PROMOTION = "Promotion";
    static String RETURN_TEMPLATE = "{\"message\":\"%s\"}";

    @Autowired
    PromotionService ps;

    @RequestMapping(value={"/PromotionDetails"},method = RequestMethod.GET)
	public String index(Model model){
		List<Promotion> promotion= ps.getAllProductsPromotion();
		model.addAttribute("promotion", promotion);
		
		return "PromotionDetails";
	}
    
    
    @RequestMapping(value="search",method = RequestMethod.GET)
	public String search(){
		return "searchPromotion";
	}
	
	
	@RequestMapping(value="searchbyid",method = RequestMethod.GET)
	public String searchById(@RequestParam(value = "search", required = false) int id, Model model){		
		Promotion promotion=  ps.getPromotionById(id);	 
		 model.addAttribute("search", promotion);
		 
		return "searchPromotion";
	
	}
	
	@RequestMapping(value="add")
	public String addPromotion(Model model) {
		
		model.addAttribute("promotion", new Promotion());
		return "addPromotion";
	}
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	public String savePromotion(Promotion promotion) {
		ps.createOrUpdatePromotion(promotion);
		return "redirect:/PromotionDetails";
	}
	
	
	@RequestMapping(value="/delete/{promotionId}", method=RequestMethod.GET)
	public String deletePromotion(@PathVariable("promotionId") int promotionId, Model model) {
		ps.removePromotion(promotionId);
		return "redirect:/PromotionDetails";
	}
    
    
    
	
    
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ResponseEntity<String> showInfo() {
        return ResponseEntity.badRequest().headers(new HttpHeaders()).body(WELCOME_MSG);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Promotion> getPromotionById(@PathVariable int id) {
        Promotion promo = ps.getPromotionById(id);
        HttpStatus status = HttpStatus.OK;
        if (null == promo) {
            status = HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<>(promo, new HttpHeaders(), status);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public ResponseEntity<List<Promotion>> getAllProductsPromotion() {
        return ResponseEntity.ok().body(ps.getAllProductsPromotion());
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<String> deletePromotion(@PathVariable int id) {
        boolean deleted = ps.removePromotion(id);
        HttpStatus status = HttpStatus.OK;
        String msg = String.format(DELETE_MSG, id, SUCCESS);
        if (!deleted) {
            msg = String.format(DELETE_MSG, id, ERROR);
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(String.format(RETURN_TEMPLATE, msg), new HttpHeaders(), status);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ResponseEntity<String> addPromotion(@RequestBody Promotion promo) {
        boolean created = ps.createOrUpdatePromotion(promo);
        HttpStatus status = HttpStatus.OK;
        String msg = String.format(CREATE_SUB_COMPONENT_MSG, COMPONENT_PROMOTION, promo.getPromoId(), SUCCESS);
        if (!created) {
            String.format(CREATE_SUB_COMPONENT_MSG, COMPONENT_PROMOTION, promo.getPromoId(), ERROR);
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(String.format(RETURN_TEMPLATE, msg), new HttpHeaders(), status);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<String> updatePromotion(@PathVariable int id, @RequestBody Promotion promo) {
        boolean updated = ps.createOrUpdatePromotion(promo);
        HttpStatus status = HttpStatus.OK;
        String msg = String.format(UPDATE_SUB_COMPONENT_MSG, COMPONENT_PROMOTION, id, SUCCESS);
        if (!updated) {
            msg = String.format(UPDATE_SUB_COMPONENT_MSG, COMPONENT_PROMOTION, id, ERROR);
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(String.format(RETURN_TEMPLATE, msg), new HttpHeaders(), status);
    }
}
