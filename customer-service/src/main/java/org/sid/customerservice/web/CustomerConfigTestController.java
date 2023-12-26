package org.sid.customerservice.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
//@RefreshScope
@RestController
public class CustomerConfigTestController {
    @Value("${global.params.p1}")
    public String p1;
    @Value("${global.params.p2}")

    public String p2;
    @Value("${customer.params.x}")

    public String x;
    @Value("${customer.params.y}")

    public String y;

    @GetMapping("/params")
    public Map<String,String> params(){
        return Map.of("p1",p1,"p2",p2,"x",x,"y",y);
    }
}
