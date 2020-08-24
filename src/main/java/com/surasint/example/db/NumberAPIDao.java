package com.surasint.example.db;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class NumberAPIDao {

    @Qualifier("datasource1")
    @Autowired
    private DataSource dataSource;

    public Integer addNumbers(NumberBean item){
        Connection conn =  DataSourceUtils.getConnection(dataSource);
        Handle handle = DBI.open(conn);
        UserSQLs userSQLs = handle.attach(UserSQLs.class);
        return userSQLs.insert(item);
    }

    @RegisterMapper(NumberAPIDao.UserMapper.class)
    interface UserSQLs {

        @SqlUpdate("insert into numbers (number)" +
                " values(:numbers) ")
        @GetGeneratedKeys
        Integer insert(@BindBean NumberBean test);
    }

    public static class UserMapper implements ResultSetMapper<NumberBean> {
        @Override
        public NumberBean map(int i, ResultSet r, StatementContext statementContext) throws SQLException {
            NumberBean bean = new NumberBean();
            bean.setId((Integer) r.getObject("id"));
            return bean;
        }
    }
}
