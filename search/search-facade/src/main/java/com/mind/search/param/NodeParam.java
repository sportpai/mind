package com.mind.search.param;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/14.
 */

@Data
public class NodeParam implements Serializable {

    private Long id;
    private Byte type;
    private String name;

    //深度
    private Integer depthLevel;
    //父级排序呢
    private Integer relevanceNo;
    //父级Id
    private Long originalId;

}
