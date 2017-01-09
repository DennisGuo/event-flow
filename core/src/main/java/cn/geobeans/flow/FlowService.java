package cn.geobeans.flow;

import cn.geobeans.flow.db.Initialize;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 业务流的服务BEAN
 * Created by ghx on 2017/1/9.
 */
public class FlowService {

    private static final String PREFIX_DEFAULT = "GFLOW_";
    /**
     * 数据源
     */
    private DataSource dataSource;
    /**
     * 数据库中流程相关表的表前缀
     */
    private String prefix = PREFIX_DEFAULT;
    /**
     * 是否默认创建表：默认：true
     */
    private boolean update = true;
    /**
     * 数据库类型：默认：h2; // h2,mysql,oracle
     */
    private String databaseType = "h2";

    public FlowService() {
    }

    public FlowService(DataSource dataSource) {
        this(dataSource, PREFIX_DEFAULT, true);
    }

    public FlowService(DataSource dataSource, String prefix) {
        this(dataSource, prefix, true);
    }

    public FlowService(DataSource dataSource, String prefix, boolean update) {
        this.dataSource = dataSource;
        this.prefix = prefix;
        this.update = update;

        init();

    }

    /**
     * 初始化
     */
    private void init() {
        System.out.println("初始化FlowServiceBean...");
        if (this.update) {
            Initialize.initDatabaseSchema(this);
        }
    }

     /**********************************************/
     /**************** 对外方法 ********************/
     /**********************************************/

    /**
     * 获取数据库连接
     *
     * @return 数据库连接
     */
    public Connection getConnection() throws SQLException {
        if (this.dataSource != null) {
            return this.dataSource.getConnection();
        } else {
            throw new SQLException("FlowServiceBean需要配置database属性");
        }
    }


    public List<Flow> getFlow(){
        List<Flow> list = new ArrayList<Flow>();
        try {
            Connection conn = this.getConnection();
            String sql = String.format("SELECT ID,NAME FROM %sFLOW",this.prefix);
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Flow f = new Flow();
                f.setId(rs.getInt(0));
                f.setName(rs.getString(1));
                list.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }





    /**********************************************/
    /**************** 对外方法结束 ****************/
    /**********************************************/





    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
