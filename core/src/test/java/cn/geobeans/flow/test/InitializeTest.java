package cn.geobeans.flow.test;

import cn.geobeans.flow.db.Initialize;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

/**
 * Created by ghx on 2017/1/9.
 */
public class InitializeTest {

    @Test
    public void readSchema(){
        try {
            String rs = Initialize.getInitialSql("h2","LOVE_");
            System.out.println(rs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
