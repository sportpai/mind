package com.mind.search.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "user")
@Data
public class User implements Serializable {

    private Long id;
    private String nickName;

    public static User getInstance(){
        User user = new User();
        return  user;
    }

}
