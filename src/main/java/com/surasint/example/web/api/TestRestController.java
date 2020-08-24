package com.surasint.example.web.api;


import com.surasint.example.db.NumberBean;
import com.surasint.example.service.NumberService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestRestController {

    private static int i;

    @Autowired
    NumberService1 numberService1;

    @GetMapping("/api/number/add")
    public Integer addFromTwoSources() {
        return numberService1.addNumbers();
    }

}