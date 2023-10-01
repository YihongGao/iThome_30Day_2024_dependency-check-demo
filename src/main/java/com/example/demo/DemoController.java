package com.example.demo;

import com.example.demo.vo.Product;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class DemoController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private WebClient productClient;

    // 從 application.yml / app.welcome.message 取此變數值
    @Value("${app.welcome.message}")
    private String welcomeMessage;

    // 從 application.yml / app.env 取此變數值
    @Value("${app.env}")
    private String env;

    // 從 application.yml / app.token 取此變數值
    @Value("${app.token}")
    private String token;

    @Value("${internal-api.product.url}")
    private String productURL;

    @PostConstruct
    public void init() {
        this.productClient = WebClient.builder().baseUrl(this.productURL).build();
    }

    @RequestMapping("/")
    public String welcome() {
        return welcomeMessage;
    }

    @GetMapping("/product/{id}")
    public Product product(@PathVariable("id") long id) {
        logger.info("productURL: " + productURL);
        return productClient.get().uri("/productInfo/{id}", id).retrieve().bodyToMono(Product.class).block();
    }


    @RequestMapping("/env")
    public String env() {
        return env;
    }

    @RequestMapping("/token")
    public String token() {
        return token;
    }

    @PostMapping("/log-message")
    public void logMessage(@RequestBody String message) {
        // 將訊息記錄到日誌中
        logger.info("接收到訊息：{}", message);
    }
}
