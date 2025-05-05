package edu.hitwh.oremanagementserver.dto;

import edu.hitwh.oremanagementserver.domain.Market;
import lombok.Data;

import java.util.List;

@Data
public class MarketList {
    private List<Market> markets;
}
