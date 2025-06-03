package edu.hitwh.oremanagementserver.dto;

import edu.hitwh.oremanagementserver.domain.Market;
import edu.hitwh.oremanagementserver.domain.ZipPackage;
import lombok.Data;

import java.util.List;
@Data
public class ZipPackageList {
    private List<ZipPackage> packages;
}
