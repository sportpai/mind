package com.mind.search.entity;

import com.mind.search.dto.NodeObject;
import com.mind.search.param.NodeParam;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/14.
 */
@Document(collection = "node")
@Data
public class Node implements Serializable {

    @Id
    private Long id;
    private Byte type;
    private String name;

    //深度
    private Integer depthLevel;
    //父级排序呢
    private Integer relevanceNo;
    //父级Id
    private Long originalId;

    public NodeObject getInstanceObj(){
        NodeObject nodeObject = new NodeObject();

        nodeObject.setId(this.getId());
        nodeObject.setType(this.getType());
        nodeObject.setName(this.getName());
        nodeObject.setDepthLevel(this.getDepthLevel());
        nodeObject.setRelevanceNo(this.getRelevanceNo());

        return nodeObject;
    }

    public static Node getInstance(NodeParam param){
        Node node = new Node();
        node.setId(param.getId());
        node.setType(param.getType());
        node.setName(param.getName());
        node.setDepthLevel(param.getDepthLevel());
        node.setRelevanceNo(param.getRelevanceNo());
        return node;
    }

}
