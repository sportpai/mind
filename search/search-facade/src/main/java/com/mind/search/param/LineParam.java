package com.mind.search.param;

import lombok.Data;

import java.io.Serializable;


@Data
public class LineParam implements Serializable {

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

}
