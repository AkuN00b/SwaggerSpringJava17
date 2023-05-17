package astratech.prg7_m6_p1_027.controller;

import astratech.prg7_m6_p1_027.Result;
import astratech.prg7_m6_p1_027.model.Helm;
import astratech.prg7_m6_p1_027.service.HelmService;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class HelmController {
    @Autowired
    HelmService helmService;

    @PostMapping("/saveHelm")
    public Result save(HttpServletResponse response, @RequestBody Helm helmParam) {
        Helm helm = new Helm(helmParam.getIdHelm(), helmParam.getMerk(), helmParam.getWarna(), helmParam.getUkuran(), helmParam.getStok(), helmParam.getJenis(), helmParam.getHarga());
        boolean isSuccess = helmService.save(helm);

        if (isSuccess) {
            return new Result(500, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(200, "Fail");
        }
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

    @PostMapping("/updateHelm")
    public Result updateHelm(HttpServletResponse response, @RequestBody Helm helmParam) {
        Helm helm = new Helm(helmParam.getIdHelm(), helmParam.getMerk(), helmParam.getWarna(), helmParam.getUkuran(), helmParam.getStok(), helmParam.getJenis(), helmParam.getHarga());
        boolean isSuccess = helmService.update(helm);

        if (isSuccess) {
            return new Result(500, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(200, "Fail");
        }
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
