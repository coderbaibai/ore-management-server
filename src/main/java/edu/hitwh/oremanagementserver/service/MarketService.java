package edu.hitwh.oremanagementserver.service;

import edu.hitwh.oremanagementserver.controller.Result;
import edu.hitwh.oremanagementserver.domain.Market;
import edu.hitwh.oremanagementserver.dto.MarketItemList;
import edu.hitwh.oremanagementserver.dto.MarketList;

import java.util.List;

public interface MarketService {

    Result add(Market market);

    Result delete(MarketList market);

    Result addItems(MarketItemList marketItemList);

    Result getUsersMarkets(Long id);

    Result getMarketsItems(Long id);
}
