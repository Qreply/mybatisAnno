package com.wd.test;

import com.wd.entity.User;
import com.wd.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class AnnotationCRUDTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private UserMapper userMapper;

    @Before
    public  void init()throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userMapper = session.getMapper(UserMapper.class);
    }

    @After
    public  void destroy()throws  Exception{
        session.commit();
        session.close();
        in.close();
    }


    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("mybatis annotation");
        user.setAddress("beijing changpingqu");

        userMapper.saveUser(user);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(54);
        user.setUsername("mybatis annotation update");
        user.setAddress("beijingshi haidianqu");
        user.setSex("m");
        user.setBirthday(new Date());

        userMapper.updateUser(user);
    }


    @Test
    public void testDelete(){
        userMapper.deleteUser(51);
    }

    @Test
    public void testFindOne(){
        User user = userMapper.findById(54);
        System.out.println(user);
    }


    @Test
    public  void testFindByName(){
        //List<User> users = userMapper.findUserByName("%mybatis%");
        List<User> users = userMapper.findUserByName("mybatis");
        for(User user : users){
            System.out.println(user);
        }
    }

    @Test
    public  void testFindTotal(){
        int total = userMapper.findTotalUser();
        System.out.println(total);
    }
}
