package com.mind.search.dto;

import com.mind.search.entity.Line;
import com.mind.search.entity.Node;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyunlong on 18-3-12.
 */
@Data
public class LineObject extends Line {

    private NodeObject upNode;

    private NodeObject downNode;

    private List<PassUser> passUsers = new ArrayList();



}
