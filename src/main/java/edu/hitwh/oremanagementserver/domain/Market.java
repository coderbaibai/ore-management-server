package edu.hitwh.oremanagementserver.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName("tb_market")
public class Market {
    @TableId(type= IdType.AUTO)
    private Long id;
    private Long userId;
    private String name;
}