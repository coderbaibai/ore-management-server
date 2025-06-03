package edu.hitwh.oremanagementserver.domain;

import lombok.Data;

@Data
public class MarketPathItem {
    private Long marketId;
    private String name;
    private String bucketName;
    private String path;
    private Long size;
}
