package com.example.demo1.modules.controller;

import com.example.demo1.common.pojo.CommonResult;
import com.example.demo1.modules.controller.vo.LoginVO;
import com.example.demo1.modules.dao.user.SysUserDO;
import com.example.demo1.modules.mapper.user.SysUserMapper;
import com.example.demo1.modules.service.user.SysUserService;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static com.example.demo1.common.pojo.CommonResult.success;
import static com.example.demo1.utils.servlet.ServletUtils.getClientIP;
import static com.example.demo1.utils.servlet.ServletUtils.getUserAgent;


/**
 * @author cyj
 */
@Api(tags = "11111")
//该注解目前有两个属性,分别是author(作者)和order(排序)
@ApiSupport(author = "cyj.com",order = 284)
@RestController
@RequestMapping("/abc/efg")
@Validated
public class TestController {

    @Resource
    SysUserMapper sysUserMapper;

    @Resource
    SysUserService sysUserService;


//    @ApiOperationSupport(author = "cyj.com")
    @ApiOperation(value = "写文档注释我是认真的")
    @GetMapping("/test")
    public CommonResult<String> test(){
        return success("111");
    }

    @ApiOperation("登录")
    @PostMapping("login")
    public CommonResult<String> login(@RequestBody @Valid LoginVO loginVO){
        String token = sysUserService.login(loginVO, getClientIP(), getUserAgent());

        return success(token);
    }
}
