package astratech.prg7_m6_p1_027.controller;

import astratech.prg7_m6_p1_027.Result;
import astratech.prg7_m6_p1_027.model.Helm;
import astratech.prg7_m6_p1_027.service.HelmService;
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
public class HelmController {
    @Autowired
    HelmService helmService;

//    @PostMapping("/saveHelm")
//    public Result save(HttpServletResponse response, @RequestBody Helm helmParam) {
//        Helm helm = new Helm(helmParam.getIdHelm(), helmParam.getMerk(), helmParam.getWarna(), helmParam.getUkuran(), helmParam.getStok(), helmParam.getJenis(), helmParam.getHarga());
//        boolean isSuccess = helmService.save(helm);
//
//        if (isSuccess) {
//            return new Result(500, "Success");
//        } else {
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            return new Result(200, "Fail");
//        }
//    }

    @GetMapping("/saveHelm")
    public ResponseEntity<Void> save(HttpServletResponse response,
                                     @RequestParam(name = "merk") String merk,
                                     @RequestParam(name = "warna") String warna,
                                     @RequestParam(name = "ukuran") String ukuran,
                                     @RequestParam(name = "stok") String stok,
                                     @RequestParam(name = "jenis") String jenis,
                                     @RequestParam(name = "harga") String harga) {
        Helm helm = new Helm(0, merk, warna, ukuran, Integer.parseInt(stok), jenis, Integer.parseInt(harga));
        helmService.save(helm);

        return ResponseEntity.status(HttpStatus.FOUND).
                location(URI.create("http://localhost:8080/listhelmjson.html")).build();
    }

    @GetMapping("/getHelm")
    public Helm getHelm(HttpServletResponse response, @RequestParam("id") Integer id) {
        Helm helm = helmService.getHelm(id);
        return helm;
    }

    @GetMapping("/getHelms")
    public List<Helm> getHelms() {
        return helmService.getHelms();
    }

//    @PostMapping("/updateHelm")
//    public Result updateHelm(HttpServletResponse response, @RequestBody Helm helmParam) {
//        Helm helm = new Helm(helmParam.getIdHelm(), helmParam.getMerk(), helmParam.getWarna(), helmParam.getUkuran(), helmParam.getStok(), helmParam.getJenis(), helmParam.getHarga());
//        boolean isSuccess = helmService.update(helm);
//
//        if (isSuccess) {
//            return new Result(500, "Success");
//        } else {
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            return new Result(200, "Fail");
//        }
//    }

    @GetMapping("/updateHelm")
    public ResponseEntity<Void> update(HttpServletResponse response,
                                     @RequestParam(name = "id_helm") String idHelm,
                                     @RequestParam(name = "merk") String merk,
                                     @RequestParam(name = "warna") String warna,
                                     @RequestParam(name = "ukuran") String ukuran,
                                     @RequestParam(name = "stok") String stok,
                                     @RequestParam(name = "jenis") String jenis,
                                     @RequestParam(name = "harga") String harga) {
        Helm helm = new Helm(Integer.parseInt(idHelm), merk, warna, ukuran, Integer.parseInt(stok), jenis, Integer.parseInt(harga));
        helmService.save(helm);

        return ResponseEntity.status(HttpStatus.FOUND).
                location(URI.create("http://localhost:8080/listhelmjson.html")).build();
    }

    @DeleteMapping("/deleteHelm")
    public ResponseEntity<String> deleteById(@RequestParam("id") Integer id) {
        helmService.deleteHelm(id);
        return ResponseEntity.ok("Helm with ID: " + id + ". Deleted Successfully");
    }

    @GetMapping("/helm/report/{format}")
    public String generateReport(@PathVariable String format) throws JRException, FileNotFoundException {
        return helmService.exportReport(format);
    }
}
