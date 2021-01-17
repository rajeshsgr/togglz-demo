package com.raj.nola.togglz.web.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.raj.nola.togglz.config.ProductFeatureFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.util.NamedFeature;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FeatureManager featureManager;


    @GetMapping
    @RequestMapping("/product")
    public ResponseEntity<JsonNode> getProduct() {

        ObjectNode product = objectMapper.createObjectNode();
        product.put("productID", "NB101");
        product.put("productName", "NoteBook");
        product.put("price", 5.00);
        product.put("description", "Good Note Book");
        product.put("availabileFrom", "30-Jan-2020");

        if (featureManager.isActive(ProductFeatureFlag.ADDITIONAL_INFORMATION)) {
            product.put("additionalInfo", "8\" x 10-1/2\", college-ruled notebooks fit more writing per page than wide-ruled sheets; each notebook provides 70 double-sided sheets with red margin lines");
        }

        if (featureManager.isActive(ProductFeatureFlag.FESTIVE_DISCOUNT)) {
            product.put("price", 4.00);
        }
        return ResponseEntity.ok(product);
    }

    @GetMapping
    @RequestMapping("/productV2")
    public ResponseEntity<JsonNode> getBookWishlist() {

        if (featureManager.isActive(new NamedFeature("NEW_PRODUCT"))) {

            ObjectNode product = objectMapper.createObjectNode();
            product.put("productID", "NB102");
            product.put("productName", "B5 Spiral Notebook Lined");
            product.put("price", 10.00);
            product.put("description", "Spiral Ruled Journal");
            product.put("availabileFrom", "1-Feb-2020");
            product.put("additionalInfo", "B5 Size: 26cm x 18cm / 10.3\" x 7.2\", 70 sheets (140 pages) per notebook");

            return ResponseEntity.ok(product);
        }

        return ResponseEntity.noContent().build();
    }
}
