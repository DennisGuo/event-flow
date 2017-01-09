package cn.geobeans.example.test;

import cn.geobeans.example.flow.AppFlowService;
import cn.geobeans.flow.Flow;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by ghx on 2017/1/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {
        "classpath:application-context.xml"
})
public class FlowServiceTest {

    @Autowired
    AppFlowService service;

    @Test
    public void getFlow(){
        List<Flow> rs = service.getFlow();
        System.out.println(JSON.toJSONString(rs));
    }
}
