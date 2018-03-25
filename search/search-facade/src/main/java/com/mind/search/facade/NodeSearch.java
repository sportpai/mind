package com.mind.search.facade;


import com.mind.search.result.NodeInfo;
import com.mind.search.param.LineParam;
import com.mind.search.param.NodeParam;

import java.util.List;

/**
 * Created by Administrator on 2018/1/14.
 */
public interface NodeSearch {

    List<NodeInfo> find(Long sourceId, String name, Byte type);

    NodeInfo add(Long sourceId, LineParam line, NodeParam node);

}
