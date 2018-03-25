package com.mind.cache;

import com.mind.cache.facade.CacheFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wangyunlong on 18-3-19.
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/dubbo-cache-provider.xml"})
public class CacheFacadeTest {
    @Autowired
    private  CacheFacade cacheFacade;

    @Test
    public void test01(){
        cacheFacade.set("dsfsd","sdfsf");
    }

    @Test
    public void test02(){

    }
}
