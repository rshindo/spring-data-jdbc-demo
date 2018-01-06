package com.example.demo;

import java.util.Arrays;

import org.springframework.data.jdbc.mapping.model.DefaultNamingStrategy;
import org.springframework.data.jdbc.mapping.model.JdbcPersistentProperty;

public class CustomNamingStrategy extends DefaultNamingStrategy {

    @Override
    public String getColumnName(JdbcPersistentProperty property) {
        String propertyName = property.getName();
        return camelToSnake(propertyName);
    }

    @Override
    public String getTableName(Class<?> type) {
        return super.getTableName(type);
    }

    public String camelToSnake(String original) {
        char[] chars = original.toCharArray();
        char[] buff = new char[chars.length + 10];

        int j = 0;
        for(int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if(buff[buff.length - 1] != ' ') {
                buff = Arrays.copyOf(buff, buff.length + 10);
            }
            if (Character.isUpperCase(c)) {
                buff[j++] = '_';
                buff[j++] = Character.toLowerCase(c);
            } else {
                buff[j++] = c;
            }
        }
        return new String(buff).trim();
    }

}
