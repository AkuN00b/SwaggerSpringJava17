package astratech.prg7_m6_p1_027.controller;

import astratech.prg7_m6_p1_027.Result;
import astratech.prg7_m6_p1_027.model.Pembelian;
import astratech.prg7_m6_p1_027.service.PembelianService;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
public class PembelianController {
    @Autowired
    PembelianService pembelianService;

    @PostMapping("/savePembelian")
    public Result save(HttpServletResponse response, @RequestBody Pembelian pembelian) {
        LocalDate now = LocalDate.now();
        Date date = java.sql.Date.valueOf(now);

        Pembelian pembelian1 = new Pembelian(pembelian.getIdPembelian(), pembelian.getNama(), date, 0);
        boolean isSuccess = pembelianService.save(pembelian1);

        if (isSuccess) {
            return new Result(500, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(200, "Fail");
        }
    }

    @GetMapping("/getPembelian")
    public Pembelian getPembelian(HttpServletResponse response, @RequestParam("id") Integer id) {
        Pembelian pembelian = pembelianService.getPembelian(id);
        return pembelian;
    }

    @GetMapping("/listPembelian")
    public List<Pembelian> getPembelians() {
        return pembelianService.getPembelians();
    }
}
