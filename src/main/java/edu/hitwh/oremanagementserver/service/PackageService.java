package edu.hitwh.oremanagementserver.service;

import edu.hitwh.oremanagementserver.controller.Result;
import edu.hitwh.oremanagementserver.domain.ZipPackage;

public interface PackageService {

    Result getTargetPackage(String name);

    Result getAllPackage();

    Result addPackage(ZipPackage zipPackage);
}
