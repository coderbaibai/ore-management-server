package edu.hitwh.oremanagementserver.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.hitwh.oremanagementserver.domain.MarketItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MarketItemMapper extends BaseMapper<MarketItem> {
}
