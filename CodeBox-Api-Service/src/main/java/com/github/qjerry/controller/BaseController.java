package com.github.qjerry.controller;

import com.github.qjerry.vo.CommonVO;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: </p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/2
 */
@Slf4j
public class BaseController {

    public CommonVO success() {
        return CommonVO.suc();
    }

    public CommonVO success(Object data) {
        return CommonVO.suc(data);
    }

    public CommonVO error(String message) {
        return CommonVO.err(message);
    }
}
