package cn.error0.util;

public class ResultUtil {

    //成功
    public static Integer SUCCESS_CODE=200;
    public static String SUCCESS_MSG="ok";

    //操作失败
    public static Integer FAIL_CODE=500;
    public static String FAIL_MSG="fail";

    //异常
    public static Integer Error_CODE=501;
    public static String Error_MSG="error";

    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
