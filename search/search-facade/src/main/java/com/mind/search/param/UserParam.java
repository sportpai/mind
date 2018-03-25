package com.mind.search.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserParam implements Serializable {

    private Long Id;
    private String nickName;

}
