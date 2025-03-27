package edu.hitwh.oremanagementserver.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName("tb_user")
public class User {
    @TableId(type= IdType.AUTO)
    private Long id;
    private String username;
    private String password;
}