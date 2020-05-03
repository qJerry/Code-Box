package com.github.qjerry.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Collection;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: </p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/3/29
 */
public class CodeQueryWrapper extends QueryWrapper {
    private Boolean tf;

    public static CodeQueryWrapper create(){
        CodeQueryWrapper query = new CodeQueryWrapper();
        return query;
    }

    @Override
    public CodeQueryWrapper select(String... column){
        super.select(column);
        return this;
    }

    public CodeQueryWrapper one(){
        super.last("limit 1");
        return this;
    }

    public CodeQueryWrapper limit(int count){
        super.last("limit "+count);
        return this;
    }

    public CodeQueryWrapper eq(String column , Object value){
        super.eq(column, value);
        return this;
    }

    public CodeQueryWrapper isNull(String column){
        super.isNull(column);
        return this;
    }

    public CodeQueryWrapper isNotNull(String column){
        super.isNotNull(column);
        return this;
    }

    public CodeQueryWrapper ne(String column , Object value){
        super.ne(column, value);
        return this;
    }
    public CodeQueryWrapper ge(String column , Object value){
        super.ge(column, value);
        return this;
    }
    public CodeQueryWrapper le(String column , Object value){
        super.le(column, value);
        return this;
    }
    public CodeQueryWrapper notIn(String column , Collection value){
        super.notIn(column, value);
        return this;
    }

    @Override
    public CodeQueryWrapper last(String sql){
        super.last(sql);
        return this;
    }

    public CodeQueryWrapper orderByAsc(String... columns){
        super.orderByAsc(columns);
        return this;
    }

    public CodeQueryWrapper orderByDesc(String... columns){
        super.orderByDesc(columns);
        return this;
    }

    public CodeQueryWrapper columns(String... columns){
        super.select(columns);
        return this;
    }

    public CodeQueryWrapper group(String... columns){
        super.groupBy(columns);
        return this;
    }

}
