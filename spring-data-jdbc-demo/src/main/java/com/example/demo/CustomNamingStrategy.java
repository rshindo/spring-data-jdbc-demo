package com.example.demo;

import java.util.Arrays;

import org.springframework.data.jdbc.mapping.model.JdbcPersistentProperty;
import org.springframework.data.jdbc.mapping.model.NamingStrategy;

public class CustomNamingStrategy implements NamingStrategy {

    @Override
    public String getColumnName(JdbcPersistentProperty property) {
        String propertyName = property.getName();
        return camelToSnake(propertyName);
    }

    @Override
    public String getTableName(Class<?> type) {
        String className = type.getSimpleName();
        return camelToSnake(className);
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
