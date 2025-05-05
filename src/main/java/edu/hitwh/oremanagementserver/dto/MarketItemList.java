package edu.hitwh.oremanagementserver.dto;

import edu.hitwh.oremanagementserver.domain.MarketItem;
import lombok.Data;

import java.util.List;

@Data
public class MarketItemList {
    private List<MarketItem> marketItems;
}
