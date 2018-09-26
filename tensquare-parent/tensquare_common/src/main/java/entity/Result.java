package entity;

import java.io.Serializable;

/**
 * @Author: yangfei
 * @Date: 2018/9/23 17:09
 * @Description:
 */

public class Result implements Serializable {
    private Boolean flag ;  // 是否成功
    private String message ;   // 响应信息
    private Integer code  ;   // 响应状态
    private Object data ;    // 响应内容

    public Result() {
    }

    public Result(Boolean flag,Integer code , String message) {
        this.flag = flag;
        this.message = message;
        this.code = code;
    }

    public Result(Boolean flag, Integer code, String message,Object data) {
        this.flag = flag;
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public Boolean getFlag() {

        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
