package edu.hitwh.oremanagementserver.controller;

import edu.hitwh.oremanagementserver.domain.ZipPackage;
import edu.hitwh.oremanagementserver.service.PackageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/package")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PackageController {
    @Autowired
    private PackageService packageService;

    @GetMapping("/target")
    public Result getTarget(@RequestParam String name,HttpServletRequest request, HttpServletResponse response){
        return packageService.getTargetPackage(name);
    }

    @PostMapping("/add")
    public Result add(@RequestBody ZipPackage zipPackage){
        return packageService.addPackage(zipPackage);
    }
    @GetMapping("/all")
    public Result getAll(HttpServletRequest request, HttpServletResponse response){
        return packageService.getAllPackage();
    }
}
