package com.learn.learnsatoken.mvc.service;

import com.learn.learnsatoken.mvc.domain.User;
import com.learn.learnsatoken.util.R;

/**
 * 用户表(User)表服务接口
 *
 * @author Peter Cheung
 * @since 2023-07-19 11:37:24
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param username 主键
     * @return 实例对象
     */
    R queryById(String username);

    /**
     * 全查询
     *
     * @param user 筛选条件
     * @return 查询结果
     */
    R queryAll(User user);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    R insert(User user);
}
