package com.hry.spring.pagehelper.ctl;

import com.hry.spring.pagehelper.dto.TestDto;
import com.hry.spring.pagehelper.qry.TestQry;
import com.hry.spring.pagehelper.service.ITestService;
import com.hry.spring.pagehelper.util.Model2DtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

public class TestCtl {
    @Autowired
    private ITestService testService;

    @RequestMapping(value = "delete-by-primary-key/{id}", method = RequestMethod.GET)
    public int deleteByPrimaryKey( @PathVariable("id") Integer id){
        // 参数验证略
        return testService.deleteByPrimaryKey(id);
    }

    @RequestMapping(value = "insert-selective", method = RequestMethod.POST)
    public int insertSelective(@RequestBody TestDto dto){
        // 参数验证略
        SimpleModel record = new SimpleModel();
        record.setId(dto.getId());
        record.setCreateTime(new Date());
        record.setEditTime(new Date());
        record.setProdCode(dto.getProdCode());
        record.setUserAccount(dto.getUserAccount());
        return testService.insertSelective(record);
    }

    @RequestMapping(value = "select-by-primary-key", method = RequestMethod.POST)
    public TestDto selectByPrimaryKey(@RequestBody TestQry qry){
        // 参数验证略
        return Model2DtoUtil.model2Dto(testService.selectByPrimaryKey(Integer.parseInt(qry.getId())), SimpleDto.class);
    }

    @RequestMapping(value = "select-all", method = {RequestMethod.POST })
    public List<TestDto> selectAll(@RequestBody TestQry qry){
        return Model2DtoUtil.model2Dto(testService.selectAll(qry), SimpleDto.class);
    }

    @RequestMapping(value = "select-all-with-page", method = {RequestMethod.POST })
    public MyPage<SimpleDto> selectAllWithPage(@RequestBody SimpleQry qry){
        MyPage<SimpleDto> page = Model2DtoUtil.model2Dto(testService.selectAllWithPage(qry), SimpleDto.class);
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
