package com.example.demo;

import com.example.demo.vo.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductController {

    private Map<Long, Product> productMap = new HashMap<Long, Product>(){{
        put(1l, new Product("【YONEX】NF1000Z", 5000l));
        put(2l, new Product("【YONEX】ARC 11 PRO", 4000l));
        put(3l, new Product("【VICTOR】龍牙之刃TK-RYUGA II", 4230l));
    }};

    @GetMapping("/productInfo/{id}")
    public Product product(@PathVariable("id") long productId) {
        return productMap.get(productId);
    }
}
