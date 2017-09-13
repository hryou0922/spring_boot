package com.hry.spring.pagehelper.ctl;

import im.yixin.icc.database.pagehelper.Model2DtoUtil;
import im.yixin.icc.demoservice.model.SimpleModel;
import im.yixin.icc.demoservice.service.ISimpleService;
import im.yixin.icc.domain.common.page.MyPage;
import im.yixin.icc.domain.demoservice.dto.SimpleDto;
import im.yixin.icc.domain.demoservice.qry.SimpleQry;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/4.
 */
@RestController
@RequestMapping(value = "/simple")
@EnableSwagger2
public class SimpleCtl {
    @Autowired
    private ISimpleService simpleService;

    @ApiOperation(value="delete-by-primaryKey" )
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="path",name="id",dataType="int",required=true,value="主键",defaultValue="-1")
    })
    @RequestMapping(value = "delete-by-primary-key/{id}", method = RequestMethod.GET)
    public int deleteByPrimaryKey( @PathVariable("id") Integer id){
        // 参数验证略
        return simpleService.deleteByPrimaryKey(id);
    }

    @RequestMapping(value = "insert-selective", method = RequestMethod.POST)
    public int insertSelective(@RequestBody SimpleDto dto){
        // 参数验证略
        SimpleModel record = new SimpleModel();
        record.setId(dto.getId());
        record.setCreateTime(new Date());
        record.setEditTime(new Date());
        record.setProdCode(dto.getProdCode());
        record.setUserAccount(dto.getUserAccount());
        return simpleService.insertSelective(record);
    }

    @RequestMapping(value = "select-by-primary-key", method = RequestMethod.POST)
    public SimpleDto selectByPrimaryKey(@RequestBody SimpleQry qry){
        // 参数验证略
        return Model2DtoUtil.model2Dto(simpleService.selectByPrimaryKey(Integer.parseInt(qry.getId())), SimpleDto.class);
    }

    @RequestMapping(value = "select-all", method = {RequestMethod.POST })
    public List<SimpleDto> selectAll(@RequestBody SimpleQry qry){
        return Model2DtoUtil.model2Dto(simpleService.selectAll(qry), SimpleDto.class);
    }

    @RequestMapping(value = "select-all-with-page", method = {RequestMethod.POST })
    public MyPage<SimpleDto> selectAllWithPage(@RequestBody SimpleQry qry){
        MyPage<SimpleDto> page = Model2DtoUtil.model2Dto(simpleService.selectAllWithPage(qry), SimpleDto.class);
        page.setMessage(getLocalInfo());
        return page;
    }

    private String getLocalInfo(){
        StringBuilder sb = new StringBuilder();
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            sb.append("server info :")
                    .append("[ip:").append(inetAddress.getHostAddress()).append(",hostname:").append(inetAddress.getHostName())
                    .append("]");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
