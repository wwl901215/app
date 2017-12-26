package com.wwl.can.zhujie;

import java.lang.reflect.Field;

/**
 * Created by wangwenliang on 2017/12/26.
 */

public class QueryGenerator {

    public String generatQuery(Class<?> entityClass) {

        StringBuffer sql = new StringBuffer("Select ");
        TableEntity tableEntity = entityClass.getAnnotation(TableEntity.class);
        String tableName = tableEntity.value();
        Field[] fields = entityClass.getDeclaredFields();
        for (Field f : fields) {
            Column column = f.getAnnotation(Column.class);
            String columnName = column.value();
            sql.append(columnName).append(",");
        }
        sql.append(" FROM " + tableName);
        return sql.toString();
    }
}
