/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: MetaFieldHandler
 */
package com.kiritor.hshop.serviceuser.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * @Class MetaFieldHandler
 * @Description 自动填充处理类
 * @Author 子梁
 * @Date 2020/7/9
 * @Verson 1.0.0
 *
 */

public class MetaFieldHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("enabled",1,metaObject);
        this.setFieldValByName("accountNonExpired",1,metaObject);
        this.setFieldValByName("accountNonLocked",1,metaObject);
        this.setFieldValByName("credentialsNonExpired",1,metaObject);
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
