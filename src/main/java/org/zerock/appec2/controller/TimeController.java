package org.zerock.appec2.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

@RestController
@RequestMapping("/api/time")
public class TimeController {
    @Autowired
    private DataSource dataSource;

    @GetMapping("now")
    public Map<String, String> getNow(){
        String now = "";

        try(Connection connection= dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select now()");
            ResultSet resultSet = preparedStatement.executeQuery();
        ){
            resultSet.next();

            System.out.println("NOW" + now);
        } catch (Exception e){
            e.printStackTrace();
        }

        return Map.of("NOW" , now);
    }
}
