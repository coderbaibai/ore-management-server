package edu.hitwh.oremanagementserver.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.hitwh.oremanagementserver.domain.Market;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MarketMapper extends BaseMapper<Market> {
}
