package edu.hitwh.oremanagementserver.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName("tb_market_item")
public class MarketItem {
    @TableId(type= IdType.AUTO)
    private Long id;
    private Long marketId;
    private String name;
    private String bucketName;
    private String path;
    private Long size;
}