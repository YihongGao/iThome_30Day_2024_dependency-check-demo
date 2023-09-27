package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // 從 application.yml / app.welcome.message 取此變數值
    @Value("${app.welcome.message}")
    private String welcomeMessage;

    // 從 application.yml / app.env 取此變數值
    @Value("${app.env}")
    private String env;

    // 從 application.yml / app.token 取此變數值
    @Value("${app.token}")
    private String token;

    @RequestMapping("/")
    public String welcome() {
        return welcomeMessage;
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
