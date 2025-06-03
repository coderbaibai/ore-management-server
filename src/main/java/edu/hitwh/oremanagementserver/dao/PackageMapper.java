package edu.hitwh.oremanagementserver.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.hitwh.oremanagementserver.domain.ZipPackage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PackageMapper extends BaseMapper<ZipPackage> {
}