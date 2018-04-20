package com.jztey.demo.tools;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${output.message}")
    private String isoutput;

    @ExceptionHandler(value = FileNotFoundException.class)
    @ResponseBody
    public Map<String, Object> FileNotFoundError(HttpServletRequest req, Exception e) {
        outputLogger(req, e, "FileNotFoundException");

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        result.put("code", 2);
        if (isoutput.equals("true")) {
            result.put("message", e.getMessage());
        } else {
            result.put("message", "哎呀，您的操作不能进行了,你的文件找不着了！");
        }
        return result;
    }

    @ExceptionHandler(value = IOException.class)
    @ResponseBody
    public Map<String, Object> ioError(HttpServletRequest req, Exception e) {
        outputLogger(req, e, "IOException");

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        result.put("code", 2);
        if (isoutput.equals("true")) {
            result.put("message", e.getMessage());
        } else {
            result.put("message", "哎呀，您的操作不能进行了，我们的硬盘可能有问题了！");
        }
        return result;
    }

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    @ResponseBody
    public Map<String, Object> databaseError(HttpServletRequest req, Exception e) {
        outputLogger(req, e, "DataAccessException");

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        result.put("code", 2);
        if (isoutput.equals("true")) {
            result.put("message", e.getMessage());
        } else {
            result.put("message", "哎呀，您的操作不能进行了，系统数据库有问题了查不了数据了：(");
        }
        return result;
    }

    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public Map<String, Object> nullpointerError(HttpServletRequest req, Exception e) {
        outputLogger(req, e, "NullPointerException");

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        result.put("code", 2);
        if (isoutput.equals("true")) {
            result.put("message", e.getMessage());
        } else {
            result.put("message", "哎呀，您的操作不能进行了，有些数据好像是空的没有初始化啊！");
        }
        return result;
    }

    @ExceptionHandler(value = NumberFormatException.class)
    @ResponseBody
    public Map<String, Object> NumberFormatError(HttpServletRequest req, Exception e) {
        outputLogger(req, e, "NumberFormatException");

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        result.put("code", 2);
        if (isoutput.equals("true")) {
            result.put("message", e.getMessage());
        } else {
            result.put("message", "哎呀，您的操作不能进行了，字符串不能转换为数字啊！");
        }
        return result;
    }

    @ExceptionHandler(value = IndexOutOfBoundsException.class)
    @ResponseBody
    public Map<String, Object> IndexOutOfBoundsError(HttpServletRequest req, Exception e) {
        outputLogger(req, e, "IndexOutOfBoundsException");

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        result.put("code", 2);
        if (isoutput.equals("true")) {
            result.put("message", e.getMessage());
        } else {
            result.put("message", "哎呀，您的操作不能进行了，数组或者字符串的下标值是不是超出了范围呀！");
        }
        return result;
    }

    @ExceptionHandler(value = ArithmeticException.class)
    @ResponseBody
    public Map<String, Object> ArithmeticError(HttpServletRequest req, Exception e) {
        outputLogger(req, e, "ArithmeticException");

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        result.put("code", 2);
        if (isoutput.equals("true")) {
            result.put("message", e.getMessage());
        } else {
            result.put("message", "哎呀，您的操作不能进行了，这道题太难了算不出来了！");
        }
        return result;
    }

    @ExceptionHandler(value = IncompatibleClassChangeError.class)
    @ResponseBody
    public Map<String, Object> IncompatibleClassChangeError(HttpServletRequest req, Exception e) {
        outputLogger(req, e, "IncompatibleClassChangeError");

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        result.put("code", 2);
        if (isoutput.equals("true")) {
            result.put("message", e.getMessage());
        } else {
            result.put("message", "哎呀，您的操作不能进行了，声明变了没重新定义啊！");
        }
        return result;
    }

    @ExceptionHandler(value = StackOverflowError.class)
    @ResponseBody
    public Map<String, Object> StackOverflowError(HttpServletRequest req, Exception e) {
        outputLogger(req, e, "StackOverflowError");

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        result.put("code", 2);
        if (isoutput.equals("true")) {
            result.put("message", e.getMessage());
        } else {
            result.put("message", "哎呀，您的操作不能进行了，堆栈溢出了！");
        }
        return result;
    }

    @ExceptionHandler(value = OutOfMemoryError.class)
    @ResponseBody
    public Map<String, Object> OutOfMemoryError(HttpServletRequest req, Exception e) {
        outputLogger(req, e, "OutOfMemoryError");

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        result.put("code", 2);
        if (isoutput.equals("true")) {
            result.put("message", e.getMessage());
        } else {
            result.put("message", "哎呀，您的操作不能进行了，完了没那么多内存了！");
        }
        return result;
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public Map<String, Object> RuntimeError(HttpServletRequest req, Exception e) {
        outputLogger(req, e, "RuntimeException");

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        result.put("code", 2);
        if (isoutput.equals("true")) {
            result.put("message", e.getMessage());
        } else {
            result.put("message", "哎呀，您的操作不能进行了，运行过程中出错了！");
        }
        return result;
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public Map<String, Object> BindError(HttpServletRequest req, Exception e) {
        outputLogger(req, e, "BindException");

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        result.put("code", 2);
        if (isoutput.equals("true")) {
            result.put("message", e.getMessage());
        } else {
            result.put("message", "哎呀，您的操作不能进行了，参数绑定格式错误：(");
        }
        return result;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map<String, Object> defaultError(HttpServletRequest req, Exception e) {
        outputLogger(req, e, "Exception");

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        result.put("code", 2);
        if (isoutput.equals("true")) {
            result.put("message", e.getMessage());
        } else {
            result.put("message", "哎呀，您的操作不能进行了，系统出错了：(");
        }
        return result;
    }

    private void outputLogger(HttpServletRequest req, Exception e, String name) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        logger.error("----------------------CatchExceptio:" + name + "--------------------");
        logger.error("Url:" + req.getRequestURL().toString());
        logger.error("Time:" + df.format(new Date()));
        logger.error("Add:" + req.getHeader("x-real-ip"));
        logger.error(e.getMessage());
    }
}