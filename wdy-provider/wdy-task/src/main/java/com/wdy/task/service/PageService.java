package com.wdy.task.service;

import com.alibaba.fastjson.JSONArray;

/**
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/3/11
 */
public interface PageService {
    /**
     * 静态页面
     * @param jsonArray
     * @return
     */
    boolean ListenerPageHtml(JSONArray jsonArray);
}
