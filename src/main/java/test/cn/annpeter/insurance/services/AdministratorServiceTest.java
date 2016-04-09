package test.cn.annpeter.insurance.services; 

import cn.annpeter.insurance.entities.Administrator;
import cn.annpeter.insurance.entities.auth.AuthGroup;
import cn.annpeter.insurance.services.AdministratorService;
import cn.org.rapid_framework.page.Page;
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
* AdministratorService Tester. 
* 
* @author <AnnPeter> 
* @since <pre>Apr 5, 2016</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@Transactional
public class AdministratorServiceTest { 

    @Resource
    AdministratorService administratorService;

    @Before
    public void before() throws Exception { 
    } 

    @After
    public void after() throws Exception { 
    } 

        /** 
    * 
    * Method: save(Administrator admin) 
    * 
    */ 
    @Test
    public void testSave() throws Exception { 
        //TODO: Test goes here... 
    } 

        /** 
    * 
    * Method: getCount(String where) 
    * 
    */ 
    @Test
    public void testGetCount() throws Exception { 
        //TODO: Test goes here... 
    } 

        /** 
    * 
    * Method: getByPage(Page page) 
    * 
    */ 
    @Test
    public void testGetByPagePage() throws Exception { 
        //TODO: Test goes here... 
    } 

        /** 
    * 
    * Method: getByPage(Page page, int groupId) 
    * 
    */ 
    @Test
    public void testGetByPageForPageGroupId() throws Exception {
        Page page = new Page<AuthGroup>(0, 13, administratorService.getCount("groupId = "+1).intValue());
        List<Administrator> list = administratorService.getByPage(page, 1);
        int i = 0;
        i++;
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
    * Method: update(Administrator newAdmin) 
    * 
    */ 
    @Test
    public void testUpdate() throws Exception { 
        //TODO: Test goes here... 
    } 

        /** 
    * 
    * Method: deleteById(int adminId) 
    * 
    */ 
    @Test
    public void testDeleteById() throws Exception { 
        //TODO: Test goes here... 
    } 

        /** 
    * 
    * Method: checkPhone(String phone) 
    * 
    */ 
    @Test
    public void testCheckPhone() throws Exception { 
        //TODO: Test goes here... 
    } 

        /** 
    * 
    * Method: checkNickname(String nickname) 
    * 
    */ 
    @Test
    public void testCheckNickname() throws Exception { 
        //TODO: Test goes here... 
    } 

        /** 
    * 
    * Method: login(String nicknameOrMobileOrEmail, String passwd) 
    * 
    */ 
    @Test
    public void testLogin() throws Exception { 
        //TODO: Test goes here... 
    } 

    
    } 
