package edu.hitwh.oremanagementserver.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName("tb_package")
public class ZipPackage {
    @TableId(type= IdType.AUTO)
    private Long id;
    private String name;
    private String cls;
    private String voltage;
    private String current;
    private int cnt;
    private String distribution;
    private int mainSize;
    private boolean anomaly;
    private String anomalyList;
    private String bucketName;
    private String path;
    private Long size;
}
