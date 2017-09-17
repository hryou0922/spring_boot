package com.hry.spring.mybatis.ctl;

import com.hry.spring.mybatis.model.TestModel;
import com.hry.spring.mybatis.dto.TestDto;
import com.hry.spring.mybatis.page.MyPage;
import com.hry.spring.mybatis.qry.TestQry;
import com.hry.spring.mybatis.service.ITestService;
import com.hry.spring.mybatis.util.Model2DtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/simple")
@EnableSwagger2
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
        TestModel record = new TestModel();
        record.setId(dto.getId());
        record.setAge(dto.getAge());
        record.setName(dto.getName());
        return testService.insertSelective(record);
    }

    @RequestMapping(value = "select-by-primary-key/{id}", method = RequestMethod.POST)
    public TestDto selectByPrimaryKey(@PathVariable("id") String id){
        // 参数验证略
        return Model2DtoUtil.model2Dto(testService.selectByPrimaryKey(Integer.parseInt(id)), TestDto.class);
    }

    @RequestMapping(value = "select-all", method = {RequestMethod.POST })
    public List<TestDto> selectAll(@RequestBody TestQry qry){
        return Model2DtoUtil.model2Dto(testService.selectAll(qry), TestDto.class);
    }

    @RequestMapping(value = "select-all-with-page", method = {RequestMethod.POST })
    public MyPage<TestDto> selectAllWithPage(@RequestBody TestQry qry){
        MyPage<TestDto> page = Model2DtoUtil.model2Dto(testService.selectAllWithPage(qry), TestDto.class);
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
