package com.wwl.can.zhujie;

import java.util.HashMap;
import java.util.Map;

/**
 * @version V1.0
 * @Created by: 艾克斯 .
 * @Date: 2017-12-24
 * @Package: com.wwl.can.zhujie
 * @Description: ${TODO}
 * @author: 王文亮
 * @邮箱： wwl901215@163.com
 */

@TestA
public class TestAnnotation {
    @TestA
    private Integer age;

    @TestA
    public TestAnnotation() {

    }

    @TestA
    public void a() {
        Map m = new HashMap(0);
    }

    public void b(@TestA Integer a) {

    }
}
