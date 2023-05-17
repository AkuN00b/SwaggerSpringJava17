package astratech.prg7_m6_p1_027.controller;

import astratech.prg7_m6_p1_027.Result;
import astratech.prg7_m6_p1_027.model.Detail;
import astratech.prg7_m6_p1_027.model.Helm;
import astratech.prg7_m6_p1_027.model.Pembelian;
import astratech.prg7_m6_p1_027.service.DetailService;
import astratech.prg7_m6_p1_027.service.HelmService;
import astratech.prg7_m6_p1_027.service.PembelianService;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DetailPembelianController {
    @Autowired
    DetailService detailService;

    @Autowired
    HelmService helmService;

    @Autowired
    PembelianService pembelianService;

    @PostMapping("/saveDetail")
    public Result save(HttpServletResponse response, @RequestBody Detail detail) {
        Helm helm = helmService.getHelm(detail.getIdHelm().getIdHelm());
        helm.setStok(helm.getStok() - detail.getJumlah());
        helmService.save(helm);

        Pembelian pembelian = pembelianService.getPembelian(detail.getIdPembelian().getIdPembelian());
        pembelian.setTotal(pembelian.getTotal() + detail.getJumlah() * helm.getHarga());
        pembelianService.save(pembelian);

        Detail detail1 = new Detail(detail.getIdDetail(), detail.getIdPembelian(), detail.getIdHelm(), detail.getJumlah(), detail.getJumlah() * helm.getHarga());

        boolean isSuccess = detailService.save(detail1);
        if (isSuccess) {
            return new Result(500, "Result");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(200, "Fail");
        }
    }

    @GetMapping("/getDetail")
    public List<Detail> getDetails(@RequestParam("id") Pembelian idPembelian) {
        return detailService.getPembelianByID(idPembelian);
    }
}
