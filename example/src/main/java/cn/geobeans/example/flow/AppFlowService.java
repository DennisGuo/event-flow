package cn.geobeans.example.flow;

import cn.geobeans.flow.Flow;
import cn.geobeans.flow.FlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ghx on 2017/1/9.
 */
@Service
public class AppFlowService {

    @Autowired
    FlowService flowService;

    public List<Flow> getFlow(){
        return flowService.getFlow();
    }
}
