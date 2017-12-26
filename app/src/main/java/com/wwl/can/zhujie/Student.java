package com.wwl.can.zhujie;

/**
 * Created by wangwenliang on 2017/12/26.
 */

@TableEntity("stu")
public class Student {
    @Column("name")
    private String name;
    @Column(value = "password")
    private String password;
    @Column(value = "age")
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
