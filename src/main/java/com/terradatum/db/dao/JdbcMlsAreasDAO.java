package com.terradatum.db.dao;

import com.terradatum.db.objects.MlsAreasObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

/**
 * Created by rbellamy on 1/1/16.
 */
@Repository
public class JdbcMlsAreasDAO implements MlsAreasDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<MlsAreasObject> findMlsAreasByMlsSid(Integer mlsSid) {
        String getMlsAreasSql = "{ :result = call METRICS.MLS_PKG.GET_MLS_AREAS(:mlsSid) }";
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(this.jdbcTemplate).withFunctionName("METRICS.MLS_PKG.GET_MLS_AREAS");
        SqlParameterSource mlsSidParameter = new MapSqlParameterSource("mlsSid", mlsSid);
        Map<String, Object> resultSet = simpleJdbcCall
                .declareParameters(new SqlParameter("mlsSid", Types.INTEGER))
                .returningResultSet("RESULT", new MlsAreasObjectRowMapper())
                .execute(mlsSidParameter);
        return (List<MlsAreasObject>)resultSet.get("RESULT");
    }


    private static final class MlsAreasObjectRowMapper implements RowMapper<MlsAreasObject> {

        @Override
        public MlsAreasObject mapRow(ResultSet resultSet, int i) throws SQLException {
            MlsAreasObject mlsAreasObject = new MlsAreasObject();
            mlsAreasObject.setMlsSid(resultSet.getInt("MLS_SID"));
            mlsAreasObject.setAreaTypeId(resultSet.getBigDecimal("AREA_TYPE_ID"));
            mlsAreasObject.setViewName(resultSet.getString("VIEW_NAME"));
            mlsAreasObject.setHasKey(resultSet.getString("HAS_KEY"));
            mlsAreasObject.setSortOrder(resultSet.getBigDecimal("SORT_ORDER"));
            return mlsAreasObject;
        }
    }
}
