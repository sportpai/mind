package com.mind.search.entity;

import com.mind.search.dto.LineObject;
import com.mind.search.param.LineParam;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "line")
@Data
public class Line  implements Serializable {

    private Long id;

    private Long upNodeId;

    private Long downNodeId;

    private Long lastPassTime;

    @Override
    public String toString() {
        return "LineParam{" +
                "id=" + id +
                ", lastPassTime=" + lastPassTime +
                '}';
    }

    public LineObject getInstanceObj(){
        LineObject lineObject = new LineObject();

        lineObject.setId(this.getId());
        lineObject.setUpNodeId(this.getUpNodeId());
        lineObject.setDownNodeId(this.getDownNodeId());
        lineObject.setLastPassTime(this.getLastPassTime());

        return lineObject;
    }
    public static Line getInstance(LineParam param){
        Line line = new Line();
        line.setId(param.getId());
        line.setUpNodeId(param.getUpNodeId());
        line.setDownNodeId(param.getDownNodeId());
        line.setLastPassTime(param.getLastPassTime());
        return line;
    }
}
