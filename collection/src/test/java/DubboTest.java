import com.mind.search.result.NodeInfo;
import com.mind.search.facade.NodeSearch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:application.xml"})
public class DubboTest {

    @Autowired
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
