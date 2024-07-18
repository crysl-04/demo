package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Entity //User 类将会被映射到数据库中的表结构。每个实例对象代表数据库中的一行记录，每个类属性代表数据库表中的一个列。
public class User {

    @Id //该属性为实体的唯一标识符
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自动管理主键的生成，通常用于支持自增长的数据库列
    private Long id;

    private String username;
    private String password;
    private String email;

    public User(){}

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        // 返回用户的权限集合，例如角色列表
//        return null; // 需要根据实际情况实现
//    }

}
