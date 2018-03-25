package com.mind.search.facade.impl;

import com.mind.search.result.LastNode;
import com.mind.search.result.NodeInfo;
import com.mind.search.dto.LineObject;
import com.mind.search.dto.NodeObject;
import com.mind.search.entity.Line;
import com.mind.search.entity.Node;
import com.mind.search.facade.NodeSearch;
import com.mind.search.param.LineParam;
import com.mind.search.param.NodeParam;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("nodeSearch")
public class NodeSearchImpl implements NodeSearch, InitializingBean {

    private volatile Long nodeIdIndex = 0L;
    private volatile Long lineIdIndex = 0L;

    private Map<Long, NodeObject> nodeData = new HashMap<>(1024);
    private Map<Long, LineObject> lineData = new HashMap<>(1024);

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public List<NodeInfo> find(Long sourceId, String name, Byte type) {
        List<NodeInfo> nodes = new ArrayList<>();
        NodeObject sourceNode = nodeData.get(sourceId);
        if (null == sourceNode) {
            return null;
        }
        List<NodePath> nodesTemp = new ArrayList<>();
        NodePath path = new NodePath();
        LastNode last = new LastNode();
        last.setId(sourceNode.getId());
        last.setName(sourceNode.getName());

        path.setNode(sourceNode);
        path.setLast(last);

        nodesTemp.add(path);
        return findNode(nodesTemp, name, nodes);
    }

    @Override
    public NodeInfo add(Long sourceId, LineParam lineParam, NodeParam nodeParam) {
        Long nodeId = ++this.nodeIdIndex;
        Long lineId = ++this.lineIdIndex;

        Node node = Node.getInstance(nodeParam);
        Line line = Line.getInstance(lineParam);

        node.setId(nodeId);
        line.setId(lineId);

        NodeObject sourceNode = nodeData.get(sourceId);

        NodeObject nodeObject = node.getInstanceObj();
        LineObject lineObject = line.getInstanceObj();

        sourceNode.addDownLine(lineObject);
        lineObject.setUpNode(sourceNode);
        lineObject.setDownNode(nodeObject);
        nodeObject.addUpLines(lineObject);

        nodeData.put(nodeId, nodeObject);
        lineData.put(lineId, lineObject);

        mongoTemplate.save(node);

        NodeInfo ndoeInfo = new NodeInfo();
        ndoeInfo.setId(node.getId());
        ndoeInfo.setName(node.getName());
        return ndoeInfo;
    }


    /**
     * 查找节点
     *
     *
     * @param nodesTemp
     * @param name
     * @param nodes
     * @return
     */
    private List<NodeInfo> findNode(List<NodePath> nodesTemp, String name, List<NodeInfo> nodes) {
        List<NodePath> tempNodes = new ArrayList<>();
        for (NodePath nodePath : nodesTemp) {

            NodeObject node = nodePath.getNode();
            String nodeName = node.getName();

            if (nodeName.contains(name)) {
                LastNode lastNode = nodePath.getLast();

                NodeInfo nodeInfo = new NodeInfo();

                nodeInfo.setId(node.getId());
                nodeInfo.setName(node.getName());
                nodeInfo.setLast(lastNode.getLast());

                nodes.add(nodeInfo);

                if (nodes.size() > 10) {
                    return nodes;
                }
            }
            List<LineObject> lines = node.getDownLines();

            for (LineObject line : lines) {
                NodeObject nodeTemp = line.getDownNode();

                LastNode lastNode = nodePath.getLast();

                NodePath path = new NodePath();
                LastNode last = new LastNode();

                last.add(lastNode);

                last.setId(nodeTemp.getId());
                last.setName(nodeTemp.getName());

                path.setNode(nodeTemp);
                path.setLast(last);

                tempNodes.add(path);
            }
        }
        if (tempNodes.size() > 0) {
            return findNode(tempNodes, name, nodes);
        }
        return nodes;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Long nodeId = ++this.nodeIdIndex;

        Node node = new Node();
        node.setId(nodeId);
        node.setName("总节点");

        nodeData.put(nodeId, node.getInstanceObj());
    }

    @Data
    class NodePath{

        private NodeObject node;

        private LastNode last;

    }
}
