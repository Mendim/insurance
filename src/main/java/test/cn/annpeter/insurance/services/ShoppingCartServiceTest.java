package test.cn.annpeter.insurance.services; 

import cn.annpeter.insurance.services.ShoppingCartService;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/** 
* ShoppingCartService Tester. 
* 
* @author <AnnPeter> 
* @since <pre>Apr 6, 2016</pre> 
* @version 1.0 
*/ 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@Transactional
public class ShoppingCartServiceTest { 

    @Resource
    ShoppingCartService shoppingCartService;

    @Before
    public void before() throws Exception { 
    } 

    @After
    public void after() throws Exception { 
    } 

        /** 
    * 
    * Method: list(int member_id) 
    * 
    */ 
    @Test
    public void testList() throws Exception { 
        List list = shoppingCartService.list(3);
        int i = 0;
        i++;
    } 

    
}
