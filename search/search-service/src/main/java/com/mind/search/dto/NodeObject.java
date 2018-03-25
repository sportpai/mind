package com.mind.search.dto;

import com.mind.search.entity.Node;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyunlong on 18-3-12.
 */
@Data
public class NodeObject extends Node {

    private List<LineObject> upLines = new ArrayList();
    private List<LineObject> downLines = new ArrayList();
    private List<LineObject> accessUsers = new ArrayList();
    private List<Integer> levels = new ArrayList();

    private List<Long> upLineId = new ArrayList();
    private List<Long> downLineId = new ArrayList() ;
    private List<Integer> location = new ArrayList();


    public void addDownLine(LineObject  lineObj){
        downLines.add(lineObj);
    }

    public void addDownLines(List<LineObject> lineObjs){
        downLines.addAll(lineObjs);
    }

    public void addUpLines(LineObject lineObj){
        upLines.add(lineObj);
    }

    public void addUpLines(List<LineObject> lineObjs){
        upLines.addAll(lineObjs);
    }

    @Override
    public String toString() {
        return "NodeObject{" +
                "upLines=" + upLines +
                ", downLines=" + downLines +
                ", accessUsers=" + accessUsers +
                ", levels=" + levels +
                ", upLineId=" + upLineId +
                ", downLineId=" + downLineId +
                ", location=" + location +
                '}';
    }
}
