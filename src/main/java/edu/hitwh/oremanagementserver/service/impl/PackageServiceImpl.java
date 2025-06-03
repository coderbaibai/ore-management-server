package edu.hitwh.oremanagementserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.hitwh.oremanagementserver.controller.Code;
import edu.hitwh.oremanagementserver.controller.Result;
import edu.hitwh.oremanagementserver.dao.PackageMapper;
import edu.hitwh.oremanagementserver.domain.ZipPackage;
import edu.hitwh.oremanagementserver.dto.ZipPackageDto;
import edu.hitwh.oremanagementserver.dto.ZipPackageList;
import edu.hitwh.oremanagementserver.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PackageServiceImpl implements PackageService {
    @Autowired
    private final PackageMapper packageMapper;

    public PackageServiceImpl(PackageMapper packageMapper) {
        this.packageMapper = packageMapper;
    }

    @Override
    public Result getTargetPackage(String name) {
        LambdaQueryWrapper<ZipPackage> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ZipPackage::getName,name);
        List<ZipPackage> zipPackage = packageMapper.selectList(queryWrapper);
        ZipPackageDto zipPackageDto = new ZipPackageDto();
        if(zipPackage.isEmpty() || zipPackage.get(0).getCls()==null || zipPackage.get(0).getCls().isEmpty()){
            zipPackageDto.setZipPackage(null);
            zipPackageDto.setValid(false);
        }else{
            zipPackageDto.setZipPackage(zipPackage.get(0));
            zipPackageDto.setValid(true);
        }
        return new Result(Code.SUCCESS,zipPackageDto);
    }

    @Override
    public Result getAllPackage() {
        List<ZipPackage> zipPackages = packageMapper.selectList(null);
        ZipPackageList zipPackageList = new ZipPackageList();
        zipPackageList.setPackages(zipPackages);
        return new Result(Code.SUCCESS,zipPackageList);
    }

    @Override
    public Result addPackage(ZipPackage zipPackage) {
        try{
            List<ZipPackage> zipPackages = packageMapper.selectList(new LambdaQueryWrapper<ZipPackage>().eq(ZipPackage::getName, zipPackage.getName()));
            if(zipPackages.isEmpty()){
                packageMapper.insert(zipPackage);
            } else{
                zipPackages.get(0).setCls(zipPackage.getCls());
                zipPackages.get(0).setVoltage(zipPackage.getVoltage());
                zipPackages.get(0).setCurrent(zipPackage.getCurrent());
                zipPackages.get(0).setCnt(zipPackage.getCnt());
                zipPackages.get(0).setDistribution(zipPackage.getDistribution());
                zipPackages.get(0).setMainSize(zipPackage.getMainSize());
                zipPackages.get(0).setAnomaly(zipPackage.isAnomaly());
                zipPackages.get(0).setAnomalyList(zipPackage.getAnomalyList());
                packageMapper.updateById(zipPackages.get(0));
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Code.FAIL,"插入失败");
        }
        return new Result(Code.SUCCESS,"添加成功");
    }
}
