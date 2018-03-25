package com.mind.collection.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mind.collection.param.LineNodeParam;
import com.mind.search.facade.NodeSearch;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/node")
public class NodeController {

    @Reference(version = "1.0.0")
    private NodeSearch nodeSearch;

    @RequestMapping("/find")
    public Object find(Long sourceId, String name, Byte type){
        return nodeSearch.find(sourceId,name,type);

    }

    @RequestMapping("/add")
    public Object add(@RequestBody LineNodeParam param){
        nodeSearch.add(param.getSourceId(),
                param.getLine(),
                param.getNode());
        //System.out.println(param);
        return param;
    }
}
