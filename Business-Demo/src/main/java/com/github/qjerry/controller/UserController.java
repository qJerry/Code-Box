package com.github.qjerry.controller;

import com.github.qjerry.exception.CodeBoxGlobalException;
import com.github.qjerry.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: </p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/13
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getUser")
    public ResultVO getUser(Long code) {
        return ResultVO.err(code);
    }

    @GetMapping("/getCode")
    public ResultVO getCode(Long code) throws CodeBoxGlobalException {
        throw new CodeBoxGlobalException(code);
    }
}
