package com.zzp.workflow.workflow.DataSource.dao;

import com.zzp.workflow.workflow.DataSource.domin.MultiDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhengping.Zhu
 * on 2018/9/27.
 */
@Mapper
public interface EngineConfigMapper {

    List<MultiDataSource> getAll();

    /**
     * 保存数据
     *
     * @param multiDataSource
     * @throws Exception
     */
    void insert(MultiDataSource multiDataSource) throws Exception;

    /**
     * 数据源启用
     *
     * @param dataSourceName
     * @throws Exception
     */
    void enable(String dataSourceName) throws Exception;

    /**
     * 数据源禁用
     *
     * @param dataSourceName
     * @throws Exception
     */
    void disable(String dataSourceName) throws Exception;

    /**
     * 获取数据源数据
     *
     * @param dataSourceName
     * @return
     * @throws Exception
     */
    MultiDataSource findByDataSourceName(@Param("dataSourceName") String dataSourceName,@Param("current_id")  String current_id) throws Exception;


    /**
     * 获取数据源数据
     * @param clientId
     * @return
     * @throws Exception
     */
    MultiDataSource findBycurrentId(String clientId) throws Exception;
}
