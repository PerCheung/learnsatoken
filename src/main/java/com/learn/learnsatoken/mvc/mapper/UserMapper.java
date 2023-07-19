package com.learn.learnsatoken.mvc.mapper;

import com.learn.learnsatoken.mvc.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户表(User)表数据库访问层
 *
 * @author Peter Cheung
 * @since 2023-07-19 11:37:23
 */
@Mapper
public interface UserMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param username 主键
     * @return 实例对象
     */
    User queryById(String username);

    /**
     * 查询指定行数据
     *
     * @param user 查询条件
     * @return 对象列表
     */
    List<User> queryAll(User user);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

}

