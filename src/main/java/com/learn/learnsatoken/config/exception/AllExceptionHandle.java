package com.learn.learnsatoken.config.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotRoleException;
import com.learn.learnsatoken.util.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ValidationException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.learn.learnsatoken.config.constant.Constant.PACKAGE_NAME;

/**
 * 全局异常统一处理
 *
 * @author Peter Cheung
 * @since 2023-07-19 11:37:24
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class AllExceptionHandle {
    /**
     * 登录权限校验
     */
    @ExceptionHandler({NotLoginException.class, NotRoleException.class})
    public ResponseEntity<R> unauthorized(Exception e) {
        return R.deal(R.unauthorized().data(e(e)));
    }

    /**
     * 校验传参
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<R> handleBadRequest(Exception e) {
        return R.deal(R.badRequest().data(e(e)));
    }

    /**
     * 全局异常处理
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<R> exception(Exception e) {
        return R.deal(R.exp().data(e(e)));
    }

    /**
     * 异常信息处理主体方法
     *
     * @param e 异常对象
     * @return 异常解析信息
     */
    private String e(Exception e) {
        ByteArrayOutputStream printStackTrace = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(printStackTrace));
        log.error(String.valueOf(printStackTrace));
        //错误信息
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append(e);
        if (StringUtils.isBlank(e.getMessage())) {
            //处理
            log.error(String.valueOf(errorMessage));
            return String.valueOf(errorMessage);
        }
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            String className = stackTraceElement.getClassName();
            if (className.startsWith(PACKAGE_NAME)) {
                String errorName = ";" + stackTraceElement.getClassName();
                errorMessage.append(errorName);
                String errorLineNumber = ":" + stackTraceElement.getLineNumber();
                errorMessage.append(errorLineNumber);
                //处理
                log.error(String.valueOf(errorMessage));
                return String.valueOf(errorMessage);
            }
        }
        //处理
        log.error(String.valueOf(errorMessage));
        return String.valueOf(errorMessage);
    }
}
