package test.cn.annpeter.insurance.services; 

import cn.annpeter.insurance.services.ProductTuanYiService;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/** 
* ProductTuanYiService Tester. 
* 
* @author <AnnPeter> 
* @since <pre>Apr 8, 2016</pre> 
* @version 1.0 
*/ 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@Transactional
public class ProductTuanYiServiceTest { 

    @Resource
    ProductTuanYiService productTuanYiService;

    @Before
    public void before() throws Exception { 
    } 

    @After
    public void after() throws Exception { 
    } 

        /** 
    * 
    * Method: save(ProductTuanYi newProductTuanYi, Score score) 
    * 
    */ 
    @Test
    public void testSave() throws Exception { 
        //TODO: Test goes here... 
    } 

        /** 
    * 
    * Method: list() 
    * 
    */ 
    @Test
    public void testList() throws Exception {
        System.out.println(productTuanYiService.list());
    } 

    
    } 
