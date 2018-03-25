package com.mind.collection;

import com.mind.search.result.NodeInfo;
import com.mind.search.facade.NodeSearch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CollectionApplicationTests {


    @Resource
	private NodeSearch nodeSearch;

	@Test
	public void contextLoads() {
		Long sourceId = 1L;
		String name="wangyunlong";
		Byte type = new Byte("111");
		List<NodeInfo> nodes = nodeSearch.find(sourceId,name,type);
		System.out.println(nodes);
	}
}
