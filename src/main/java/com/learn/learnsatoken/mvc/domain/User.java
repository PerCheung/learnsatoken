package com.learn.learnsatoken.mvc.domain;

import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表(User)实体类
 *
 * @author Peter Cheung
 * @since 2023-07-19 11:37:23
 */
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户表(User)实体类")
public class User implements Serializable {
    private static final long serialVersionUID = -64489555055315064L;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;
    /**
     * 盐
     */
    @ApiModelProperty(value = "盐")
    private String salt;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
    /**
     * 逻辑删除
     */
    @ApiModelProperty(value = "逻辑删除")
    private Integer deleted;
    /**
     * 用此变量排序
     */
    @ApiModelProperty(value = "用此变量排序")
    private String orderByMe;
    /**
     * 升降序
     * <p>
     * ASC：表示按升序排序
     * <p>
     * DESC：表示按降序排序
     */
    @ApiModelProperty(value = "升降序 ASC：表示按升序排序 DESC：表示按降序排序")
    private String orderByMeSort;
}

