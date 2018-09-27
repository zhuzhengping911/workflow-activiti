package com.zzp.workflow.workflow.dao;

import com.zzp.workflow.workflow.pojo.MultiDataSource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by zhengping.Zhu
 * on 2018/9/27.
 */
@Mapper
public interface EngineConfigMapper {

    List<MultiDataSource> getAll();
}
