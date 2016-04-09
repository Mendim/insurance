package test.cn.annpeter.insurance.services; 

import cn.annpeter.insurance.services.ProductKaDanService;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/** 
* ProductKaDanService Tester. 
* 
* @author <AnnPeter> 
* @since <pre>Apr 6, 2016</pre> 
* @version 1.0 
*/ 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@Transactional
public class ProductKaDanServiceTest { 
    @Resource
    ProductKaDanService productKaDanService;

    @Before
    public void before() throws Exception { 
    } 

    @After
    public void after() throws Exception { 
    } 

        /** 
    * 
    * Method: list(Page page) 
    * 
    */ 
    @Test
    public void testList() throws Exception { 
        //TODO: Test goes here... 
    } 

        /** 
    * 
    * Method: getById(int id) 
    * 
    */ 
    @Test
    public void testGetById() throws Exception { 
        //TODO: Test goes here... 
    } 

        /** 
    * 
    * Method: deleteById(int id) 
    * 
    */ 
    @Test
    public void testDeleteById() throws Exception { 
        //TODO: Test goes here... 
    } 

        /** 
    * 
    * Method: saveOrUpdate(ProductKaDan kaDan) 
    * 
    */ 
    @Test
    public void testSaveOrUpdate() throws Exception { 
        //TODO: Test goes here... 
    } 

        /** 
    * 
    * Method: getCount() 
    * 
    */ 
    @Test
    public void testJsonGetById() throws Exception {
        Object obj = productKaDanService.jsonGetById(14);
        int i = 1;
        i++;
    } 


    
        /** 
    * 
    * Method: save(ProductKaDan productKaDan) 
    * 
    */ 
    @Test
    public void testSave() throws Exception {
        //TODO: Test goes here... 
                    /* 
                    try { 
                       Method method = ProductKaDanService.getClass().getMethod("save", ProductKaDan.class); 
                       method.setAccessible(true); 
                       method.invoke(<Object>, <Parameters>); 
                    } catch(NoSuchMethodException e) { 
                    } catch(IllegalAccessException e) { 
                    } catch(InvocationTargetException e) { 
                    } 
                    */ 
            } 

        /** 
    * 
    * Method: update(ProductKaDan newKaDan) 
    * 
    */ 
    @Test
    public void testUpdate() throws Exception { 
        //TODO: Test goes here... 
                    /* 
                    try { 
                       Method method = ProductKaDanService.getClass().getMethod("update", ProductKaDan.class); 
                       method.setAccessible(true); 
                       method.invoke(<Object>, <Parameters>); 
                    } catch(NoSuchMethodException e) { 
                    } catch(IllegalAccessException e) { 
                    } catch(InvocationTargetException e) { 
                    } 
                    */ 
            } 

    } 
