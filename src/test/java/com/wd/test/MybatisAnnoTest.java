package com.wd.test;

import com.wd.mapper.UserMapper;
import com.wd.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * mybatis的入门案例
 */

public class MybatisAnnoTest {

    /**
     * 入门案例
     * @param args
     */
    public static void main(String[] args) throws Exception{
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();
        //4.使用SqlSession创建Mapper代理的接口对象
        UserMapper userMapper = session.getMapper(UserMapper.class);
        //5.使用代理对象执行方法
        List<User> users = userMapper.findAll();
        for (User user:users){
            System.out.println(user);
        }
        //6.释放资源
        session.close();
        in.close();

    }


}


