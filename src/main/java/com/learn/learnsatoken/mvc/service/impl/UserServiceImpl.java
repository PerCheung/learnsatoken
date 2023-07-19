package com.learn.learnsatoken.mvc.service.impl;

import com.learn.learnsatoken.mvc.domain.User;
import com.learn.learnsatoken.mvc.mapper.UserMapper;
import com.learn.learnsatoken.mvc.service.UserService;
import com.learn.learnsatoken.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户表(User)表服务实现类
 *
 * @author Peter Cheung
 * @since 2023-07-19 11:37:24
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param username 主键
     * @return 实例对象
     */
    @Override
    public R queryById(String username) {
        return R.ok().data(this.userMapper.queryById(username));
    }

    /**
     * 全查询
     *
     * @param user 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(User user) {
        return R.ok().data(this.userMapper.queryAll(user));
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(User user) {
        this.userMapper.insert(user);
        return R.ok().data(user);
    }
}
