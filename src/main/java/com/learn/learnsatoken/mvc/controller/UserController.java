package com.learn.learnsatoken.mvc.controller;

import cn.dev33.satoken.annotation.SaCheckBasic;
import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.learn.learnsatoken.mvc.domain.User;
import com.learn.learnsatoken.mvc.service.UserService;
import com.learn.learnsatoken.util.MD5Util;
import com.learn.learnsatoken.util.R;
import com.learn.learnsatoken.util.UUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 用户表(User)表控制层
 *
 * @author Peter Cheung
 * @since 2023-07-19 11:37:23
 */
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.HEAD})
@Slf4j
@RestController
@RequestMapping("api/user")
@Api(tags = "用户表(User)表控制层")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 注册
     */
    @GetMapping("register")
    public ResponseEntity<R> register(User user) {
        String password = user.getPassword();
        String salt = UUIDUtil.toUUID();
        user.setSalt(salt);
        user.setPassword(MD5Util.toMD5(password, salt));
        return R.deal(this.userService.insert(user));
    }

    @GetMapping("login")
    public ResponseEntity<R> doLogin(User user) {
        String username = user.getUsername();
        User data = (User) userService.queryById(username).getData();
        if (data == null) {
            return R.deal(R.unauthorized().data("no user"));
        }
        if (data.getPassword().equals(MD5Util.toMD5(user.getPassword(), data.getSalt()))) {
            StpUtil.login(username);
            String token = StpUtil.getTokenValueByLoginId(username);
            return R.deal(R.ok().data(token));
        }
        return R.deal(R.unauthorized().data("wrong password"));
    }

    @GetMapping("logout")
    public SaResult logout(String name, String token) {
        redisTemplate.opsForValue().set("最近五分钟访问的ip", "我是？123456", 5, TimeUnit.MINUTES);
        //StpUtil.logout(name);
        StpUtil.logoutByTokenValue(token);
        return SaResult.ok();
    }

    /**
     * 全查询
     *
     * @param user 筛选条件
     * @return 查询结果
     */
    @SaCheckLogin
    @ApiOperation("全查询")
    @GetMapping
    public ResponseEntity<R> queryAll(@ApiParam(value = "user 筛选条件") User user) {
        return R.deal(this.userService.queryAll(user));
    }

    @SaCheckBasic(account = "admin:admin")
    @GetMapping("basic")
    public ResponseEntity<R> basic(String token) {
        Object username = StpUtil.getLoginIdByToken(token);
        return R.deal(R.ok().data(username));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @SaCheckRole("admin")
    @ApiOperation("通过主键查询单条数据")
    @GetMapping("{id}")
    public ResponseEntity<R> queryById(@ApiParam(value = "id 主键") @PathVariable("id") String id) {
        return R.deal(this.userService.queryById(id));
    }
}

