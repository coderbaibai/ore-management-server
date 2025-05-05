package edu.hitwh.oremanagementserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.hitwh.oremanagementserver.controller.Code;
import edu.hitwh.oremanagementserver.controller.Result;
import edu.hitwh.oremanagementserver.dao.MarketItemMapper;
import edu.hitwh.oremanagementserver.dao.MarketMapper;
import edu.hitwh.oremanagementserver.domain.Market;
import edu.hitwh.oremanagementserver.domain.MarketItem;
import edu.hitwh.oremanagementserver.domain.User;
import edu.hitwh.oremanagementserver.dto.MarketItemList;
import edu.hitwh.oremanagementserver.dto.MarketList;
import edu.hitwh.oremanagementserver.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MarketServiceImpl implements MarketService {
    @Autowired
    MarketMapper marketMapper;
    @Autowired
    private MarketItemMapper marketItemMapper;

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
                marketItemMapper.insert(marketItemList.getMarketItems().get(i));
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
            marketItemList.setMarketItems(marketItemMapper.selectList(queryWrapper));
        }
        catch (Exception e){
            return new Result(Code.FAIL,"查询失败");
        }
        return new Result(Code.SUCCESS,marketItemList,"查询成功");
    }
}
