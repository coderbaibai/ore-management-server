package edu.hitwh.oremanagementserver.dto;

import edu.hitwh.oremanagementserver.domain.MarketItem;
import lombok.Data;

import java.util.List;

@Data
public class MarketWithItems {
    private String marketName;
    private Long userId;
    private List<Long> ids;
}
