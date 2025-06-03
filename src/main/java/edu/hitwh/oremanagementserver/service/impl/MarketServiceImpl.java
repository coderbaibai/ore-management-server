package edu.hitwh.oremanagementserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.hitwh.oremanagementserver.controller.Code;
import edu.hitwh.oremanagementserver.controller.Result;
import edu.hitwh.oremanagementserver.dao.MarketItemMapper;
import edu.hitwh.oremanagementserver.dao.MarketMapper;
import edu.hitwh.oremanagementserver.dao.PackageMapper;
import edu.hitwh.oremanagementserver.domain.*;
import edu.hitwh.oremanagementserver.dto.MarketItemList;
import edu.hitwh.oremanagementserver.dto.MarketList;
import edu.hitwh.oremanagementserver.dto.MarketWithItems;
import edu.hitwh.oremanagementserver.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MarketServiceImpl implements MarketService {
    @Autowired
    MarketMapper marketMapper;
    @Autowired
    private MarketItemMapper marketItemMapper;
    @Autowired
    private PackageMapper packageMapper;

    @Override
    public Result add(Market market) {
        try {
            marketMapper.insert(market);
        } catch (Exception e) {
            // 处理唯一键冲突
            return new Result(Code.FAIL,"插入失败");
        }
        return new Result(Code.SUCCESS,"添加成功");
    }

    @Override
    public Result delete(MarketList marketList) {
        List<Long> list = marketList.getMarkets().stream().map(Market::getId).toList();
        try {
            marketMapper.deleteBatchIds(list);
        } catch (Exception e) {
            return new Result(Code.FAIL,"删除失败");
        }
        return new Result(Code.SUCCESS,"删除成功");
    }

    @Override
    public Result addItems(MarketItemList marketItemList) {
        for(int i=0;i<marketItemList.getMarketItems().size();i++){
            try {
                LambdaQueryWrapper<ZipPackage> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(ZipPackage::getName,marketItemList.getMarketItems().get(i).getName());
                List<ZipPackage> packageList = packageMapper.selectList(queryWrapper);
                ZipPackage zipPackage = new ZipPackage();
                if(packageList.isEmpty()){
                    zipPackage.setName(marketItemList.getMarketItems().get(i).getName());
                    zipPackage.setBucketName(marketItemList.getMarketItems().get(i).getBucketName());
                    zipPackage.setPath(marketItemList.getMarketItems().get(i).getPath());
                    zipPackage.setSize(marketItemList.getMarketItems().get(i).getSize());
                    packageMapper.insert(zipPackage);
                }else{
                    zipPackage = packageList.get(0);
                    zipPackage.setBucketName(marketItemList.getMarketItems().get(i).getBucketName());
                    zipPackage.setPath(marketItemList.getMarketItems().get(i).getPath());
                    zipPackage.setSize(marketItemList.getMarketItems().get(i).getSize());
                    packageMapper.updateById(zipPackage);
                }
                MarketItem marketItem = new MarketItem();
                marketItem.setMarketId(marketItemList.getMarketItems().get(i).getMarketId());
                marketItem.setPackageId(zipPackage.getId());
                marketItemMapper.insert(marketItem);
            } catch (Exception e) {
                if(e instanceof DuplicateKeyException){
                    continue;
                }
                return new Result(Code.FAIL,"添加失败");
            }
        }
        return new Result(Code.SUCCESS,"添加成功");
    }

    @Override
    public Result getUsersMarkets(Long id) {
        LambdaQueryWrapper<Market> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Market::getUserId,id);
        List<Market> markets;
        try {
            markets = marketMapper.selectList(queryWrapper);
        } catch (Exception e) {
            return new Result(Code.FAIL,"查询失败");
        }
        MarketList marketList = new MarketList();
        marketList.setMarkets(markets);
        return new Result(Code.SUCCESS,marketList,"查询成功");
    }

    @Override
    public Result getMarketsItems(Long id) {
        LambdaQueryWrapper<MarketItem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MarketItem::getMarketId,id);
        MarketItemList marketItemList = new MarketItemList();
        try {
            List<MarketItem> marketItems = marketItemMapper.selectList(queryWrapper);
            List<MarketPathItem> marketPathItems = new ArrayList<>();
            for (MarketItem marketItem : marketItems) {
                ZipPackage zipPackage = packageMapper.selectById(marketItem.getPackageId());
                MarketPathItem marketPathItem = new MarketPathItem();
                marketPathItem.setMarketId(marketItem.getMarketId());
                marketPathItem.setName(zipPackage.getName());
                marketPathItem.setBucketName(zipPackage.getBucketName());
                marketPathItem.setPath(zipPackage.getPath());
                marketPathItem.setSize(zipPackage.getSize());
                marketPathItems.add(marketPathItem);
            }
            marketItemList.setMarketItems(marketPathItems);
        }
        catch (Exception e){
            return new Result(Code.FAIL,"查询失败");
        }
        return new Result(Code.SUCCESS,marketItemList,"查询成功");
    }

    @Override
    public Result addWithItems(MarketWithItems marketWithItems) {
        Market market = new Market();
        market.setUserId(marketWithItems.getUserId());
        market.setName(marketWithItems.getMarketName());
        try {
            marketMapper.insert(market);
        } catch (Exception e) {
            // 处理唯一键冲突
            return new Result(Code.FAIL,"插入失败,市场名重复");
        }
        for(int i=0;i<marketWithItems.getIds().size();i++){
            try {
                MarketItem marketItem = new MarketItem();
                marketItem.setMarketId(market.getId());
                marketItem.setPackageId(marketWithItems.getIds().get(i));
                marketItemMapper.insert(marketItem);
            }catch (Exception e) {
                // 处理唯一键冲突
                return new Result(Code.FAIL,"插入失败,压缩包无记录");
            }
        }
        return new Result(Code.SUCCESS,"添加成功");
    }
}
