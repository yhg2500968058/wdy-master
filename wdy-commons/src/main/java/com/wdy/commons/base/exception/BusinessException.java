package com.wdy.commons.base.exception;

import com.wdy.commons.base.enums.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * へ　　　　／|
 * 　　/＼7　　　∠＿/
 * 　 /　│　　 ／　／
 * 　│　Z ＿,＜　／　　 /`ヽ
 * 　│　　　　　ヽ　　 /　　〉
 * 　 Y　　　　　`　 /　　/
 * 　ｲ●　､　●　　⊂⊃〈　　/
 * 　()　 へ　　　　|　＼〈
 * 　　>ｰ ､_　 ィ　 │ ／／
 * 　 / へ　　 /　ﾉ＜| ＼＼
 * 　 ヽ_ﾉ　　(_／　 │／／
 * 　　7　　　　　　　|／
 * 　　＞―r￣￣`ｰ―＿6
 *
 * @program: wdy
 * @description:
 * @author: yhg
 * @create: 2019-12-21 12:32
 **/
@Slf4j
public class BusinessException extends RuntimeException {

    /*
    * 异常码
    * */
    protected int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public BusinessException(){
        super();
    }

    public BusinessException(String message){
        super(message);
    }

    public BusinessException(String message,Throwable cause){
        super(message,cause);
    }

    public BusinessException(int code,String message){
        super(message);
        this.code=code;
    }
    public BusinessException(ErrorCodeEnum codeEnum, String message) {
        super(new StringBuilder(codeEnum.msg()).append(":" + message).toString());
        this.code = codeEnum.code();
    }

    public BusinessException(int code,String msgFormat , Object... args){
        super(String.format(msgFormat,args));
        this.code = code;
    }

}
