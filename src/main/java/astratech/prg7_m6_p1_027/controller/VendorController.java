package astratech.prg7_m6_p1_027.controller;

import astratech.prg7_m6_p1_027.Result;
import astratech.prg7_m6_p1_027.model.Helm;
import astratech.prg7_m6_p1_027.model.Vendor;
import astratech.prg7_m6_p1_027.service.VendorService;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.net.URI;
import java.util.List;

@RestController

public class VendorController {
    @Autowired
    VendorService vendorService;

//    @PostMapping("/saveVendor")
//    public Result save(HttpServletResponse response, @RequestBody Vendor vendorParam) {
//        Vendor vendor = new Vendor(vendorParam.getIdVendor(), vendorParam.getNamaVendor());
//        boolean isSuccess = vendorService.save(vendor);
//
//        if (isSuccess) {
//            return new Result(500, "Success");
//        } else {
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            return new Result(200, "Fail");
//        }
//    }

    @GetMapping("/saveVendor")
    public ResponseEntity<Void> save(HttpServletResponse response,
                                     @RequestParam(name = "nama") String nama) {
        Vendor vendor = new Vendor(0, nama);
        vendorService.save(vendor);

        return ResponseEntity.status(HttpStatus.FOUND).
                location(URI.create("http://localhost:8080/listvendorjson.html")).build();
    }

    @GetMapping("/getVendor")
    public Vendor getVendor(HttpServletResponse response, @RequestParam("id") Integer id) {
        Vendor vendor = vendorService.getVendor(id);
        return vendor;
    }

    @GetMapping("/getVendors")
    public List<Vendor> getVendors() {
        return vendorService.getVendors();
    }

//    @PostMapping("/updateVendor")
//    public Result updateVendor(HttpServletResponse response, @RequestBody Vendor vendorParam) {
//        Vendor vendor = new Vendor(vendorParam.getIdVendor(), vendorParam.getNamaVendor());
//        boolean isSuccess = vendorService.update(vendor);
//
//        if (isSuccess) {
//            return new Result(500, "Success");
//        } else {
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            return new Result(200, "Fail");
//        }
//    }

    @GetMapping("/updateVendor")
    public ResponseEntity<Void> update(HttpServletResponse response,
                                       @RequestParam(name = "id_vendor") String idVendor,
                                       @RequestParam(name = "nama") String nama) {
        Vendor vendor = new Vendor(Integer.parseInt(idVendor), nama);
        vendorService.save(vendor);

        return ResponseEntity.status(HttpStatus.FOUND).
                location(URI.create("http://localhost:8080/listvendorjson.html")).build();
    }

    @DeleteMapping("/deleteVendor")
    public ResponseEntity<String> deleteById(@RequestParam("id") Integer id) {
        vendorService.deleteVendor(id);
        return ResponseEntity.ok("Vendor with ID: " + id + ". Deleted Successfully");
    }

    @GetMapping("/vendor/report/{format}")
    public String generateReport(@PathVariable String format) throws JRException, FileNotFoundException {
        return vendorService.exportReport(format);
    }
}
