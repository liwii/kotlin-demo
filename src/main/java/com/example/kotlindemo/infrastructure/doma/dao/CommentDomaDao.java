package com.example.kotlindemo.infrastructure.doma.dao;

import com.example.kotlindemo.infrastructure.doma.entity.CommentDomaEntity;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.Insert;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface CommentDomaDao {
    @Select
    List<CommentDomaEntity> selectAllByCustomerId(int customerId);

    @Insert
    int insert(CommentDomaEntity entity);
}
