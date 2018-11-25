package com.example.kotlindemo.infrastructure.doma.dao;

import com.example.kotlindemo.infrastructure.doma.entity.CustomerDomaEntity;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@Dao
@ConfigAutowireable
public interface CustomerDomaDao {
    @Select
    List<CustomerDomaEntity> selectAll();

    @Select
    CustomerDomaEntity selectById(int id);

    @Insert
    int insert(CustomerDomaEntity entity);

    @Update
    int update(CustomerDomaEntity entity);

    @Delete
    int delete(CustomerDomaEntity entity);
}
