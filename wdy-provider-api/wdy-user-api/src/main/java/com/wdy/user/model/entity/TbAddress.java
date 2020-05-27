package com.wdy.user.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/*
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
 * @create: 2019-12-18 17:25
 */

@Data
@Accessors
@TableName("tb_address")
public class TbAddress extends Model<TbAddress> {

    private static final long serialVersionUID = 4180010799796096193L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

/*
     *用户ID
 */

    @TableField("user_id")
    private String userId;
/*
     *省
 */

    @TableField("province_id")
    private String provinceId;
    /* 市
    *
     */
    @TableField("city_id")
    private String cityId;
    /* 县/市
    **/

    @TableField("town_id")
    private String townId;
    /* 手机
    **/

    private String mobile;
   /* 详细地址
    */

    private String address;
    /* 联系人
    **/

    private String contace;
    /* 是否默认 1默认 0否
    **/

    @TableField("is_default")
    private String idDefault;
    /* 备注
    **/

    private String ontes;
    /** 创建时间
    * */

    @TableField("create_date")
    private LocalDateTime createDate;
    /* 别名
    **/

    private String alias;


    @Override
    protected Serializable pkVal() {
        return null;
    }
}
