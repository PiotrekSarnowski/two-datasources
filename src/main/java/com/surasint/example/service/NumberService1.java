package com.surasint.example.service;

import com.surasint.example.db.NumberAPIDao;
import com.surasint.example.db.NumberBean;
import com.surasint.example.db.NumberSTDDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Random;
import org.random.api.*;
import org.springframework.web.client.RestTemplate;

@Component
public class NumberService1 {

    @Autowired
    private NumberAPIDao numberAPIDao;

    @Autowired
    private NumberSTDDao numberSTDDao;

    @Transactional
    public Integer addNumbers(){
        NumberBean firstNumberCore = new NumberBean();
        NumberBean firstNumberAPI = new NumberBean();
        Random rn = new Random();
        int answer = rn.nextInt(10) + 1;

        firstNumberCore.setNumber(rn.nextInt(10) + 1);
        numberSTDDao.addNumbers(firstNumberCore);

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "https://api.random.org/json-rpc/2/invoke";
        NumberBean singleNumberFromAPI = restTemplate
                .getForObject(fooResourceUrl, NumberBean.class);


        RandomOrgClient roc = RandomOrgClient.getRandomOrgClient(YOUR_API_KEY_HERE);

        numberAPIDao.addNumbers(firstNumberAPI);

        //firstNumberAPI.setNumber();
        return numberAPIDao.addNumbers(test) + numberSTDDao.addNumbers(firstNumberCore);
    }

}
