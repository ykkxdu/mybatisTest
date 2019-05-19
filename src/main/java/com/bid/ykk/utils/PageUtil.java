package com.bid.ykk.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @Author:Yankaikai
 * @Description:
 * @Date:Created in 2019/4/16
 */
public class PageUtil {

    public static Page pageImplent(Page page, int current, int size, List list){

        if(current*size<list.size()) {
            page.setRecords(list.subList((current - 1) * size, current * size));
            return page;
        }else if((current - 1) * size>list.size()){
            page.setRecords(null);
            return page;
        } else{
            page.setRecords(list.subList((current - 1) * size, list.size()));
            return page;
        }
    }
}
