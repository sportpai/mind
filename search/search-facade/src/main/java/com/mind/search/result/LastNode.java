package com.mind.search.result;

import lombok.Data;

import java.util.Objects;

/**
 * Created by wangyunlong on 18-3-9.
 */
@Data
public class LastNode {

    private Long id;

    private String name;

    private LastNode last;

    public void add(LastNode addLast){
        LastNode temp = this;

        while(Objects.nonNull(temp.getLast())){
            temp = temp.getLast();
        }

        temp.setLast(addLast);
    }


}
