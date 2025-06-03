package edu.hitwh.oremanagementserver.dto;

import edu.hitwh.oremanagementserver.domain.ZipPackage;
import lombok.Data;

@Data
public class ZipPackageDto {
    boolean valid;
    ZipPackage zipPackage;
}
