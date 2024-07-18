package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper //MyBatis 的 Mapper 接口，Spring Boot 会自动扫描并创建实现类
public interface UserMapper {

    @Select("SELECT * FROM users WHERE id = #{id}")
    User findById(Long id);

    @Select("SELECT * FROM users WHERE username = #{username}")
    User findByUsername(String username);

    @Insert("INSERT INTO users(username,password,email) VALUES(#{username},#{password},#{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id",keyColumn = "id") //配置 SQL 执行选项 在插入操作时使用数据库自动生成的主键 生成的主键值将赋给 Java 对象中名为 id 的属性 生成的主键值放在一个特定的列中（比如 id 列），这里指定这个列的名字是 id
    void insert(User user);

    @Update("UPDATE users SET username = #{username}, password = #{password},email = #{email} WHERE id = #{id}")
    void update(User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void deleteById(Long id);

    @Select("SELECT * FROM users")
    List<User> findAll();

    @Delete("DELETE FROM users")
    void deleteAll(); // 新增删除所有用户的方法

}
