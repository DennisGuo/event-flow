package cn.geobeans.flow.db;

import cn.geobeans.flow.FlowService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 数据库初始化工具
 * Created by ghx on 2017/1/9.
 */
public class Initialize {


    /**
     * 初始化数据库表
     */
    public static void initDatabaseSchema(FlowService flowService) {
        System.out.println("初始化FlowService数据库...");
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //读取schema
            String sql = getInitialSql(flowService.getDatabaseType(), flowService.getPrefix());
            //操作数据库
            conn = flowService.getConnection();
            ps = conn.prepareStatement(sql);
            int rs = ps.executeUpdate();
            System.err.println("初始化FlowService数据库："+(rs>0 ?"成功":"失败"));
        } catch (SQLException e) {
            System.err.println("初始化数据库失败：" + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("初始化数据库失败：" + e.getLocalizedMessage());
            e.printStackTrace();
        } finally {
            try {
                if(ps != null){ ps.close();}
                if(conn != null){ conn.close();}
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取数据库建表SQL语句
     * @param type      数据库类型
     * @param prefix    数据表前缀
     * @return           数据库建SQL表语句
     * @throws IOException
     */
    public static String getInitialSql(String type,String prefix) throws IOException {
        String schemaFile = "schema." + type+".sql";

        InputStream is = Initialize.class.getResourceAsStream("/schemas/" +schemaFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String tmp ;
        while((tmp = br.readLine()) != null ){
            sb.append(tmp);
        }
        String rs = sb.toString();
        rs = rs.replaceAll("__PREFIX__",prefix);
        return rs;
    }

}
