package com.wd.mapper;

import com.wd.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


/**
 * 用户的持久层接口
 * 在mybatis中针对,CRUD一共有四个注解
 *  @Select @Insert @Update @Delete
 */
public interface UserMapper {

    /**
     * 查询所有操作
     * @return
     */
    @Select("select * from user")
    List<User> findAll();

    /**
     * 保存用户
     * @param user
     */
    @Insert("insert into user (username, address, sex, birthday) values (#{username}, #{address}, #{sex}, #{birthday})")
    void saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    @Update("update user set username = #{username}, address = #{address}, sex = #{sex}, birthday = #{birthday} where id = #{id}")
    void updateUser(User user);

    /**
     * 删除用户
     * @param id
     */
    @Delete("delete from user where id = #{id}")
    void deleteUser(Integer id);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    User findById(Integer id);

    /**
     * 根据用户名称模糊查询
     * @param name
     * @return
     */
    //@Select("select * from user where username like #{name}")
    @Select("select * from user where username like '%${value}%' ")
    List<User> findUserByName(String name);

    /**
     * 查询总用户数量
     * @return
     */
    @Select("select count(*) from user ")
    int findTotalUser();
}
