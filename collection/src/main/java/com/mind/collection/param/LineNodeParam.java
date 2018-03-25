package com.mind.collection.param;

import com.mind.search.param.LineParam;
import com.mind.search.param.NodeParam;
import lombok.Data;

@Data
public class LineNodeParam {

    private Long sourceId;

    private LineParam line;

    private NodeParam node;

}
