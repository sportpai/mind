package com.mind.config.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyunlong on 18-3-13.
 */

@Data
public class Node {

    private String symbols;

    private Long id;

    private List<Node> children = new ArrayList();

    public void add(Node node){
        this.children.add(node);
    }

}
