package astratech.prg7_m6_p1_027.service;

import astratech.prg7_m6_p1_027.model.Helm;
import astratech.prg7_m6_p1_027.repository.HelmRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HelmService {
    @Autowired
    HelmRepository helmRepository;

    public boolean save(Helm helm) {
        Helm result = helmRepository.save(helm);
        boolean isSuccess = true;

        if (result == null) {
            isSuccess = false;
        }

        return isSuccess;
    }

    public Helm getHelm(Integer idHelm) {
        Helm helm = helmRepository.getHelmByIdHelm(idHelm);
        return helm;
    }

    public List<Helm> getHelms() {
        return helmRepository.findAll();
    }

    public boolean update(Helm helm) {
        Helm result = helmRepository.save(helm);
        boolean isSuccess = true;

        if (result == null) {
            isSuccess = false;
        }

        return isSuccess;
    }

    public void deleteHelm(Integer idHelm) {
        helmRepository.deleteById(idHelm);
    }

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\LENOVO\\Downloads";
        List<Helm> helms = (List<Helm>) helmRepository.findAll();
        File file = ResourceUtils.getFile("classpath:templates/PRG7_M6_P1_027.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(helms);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Gerlando Corputty");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\helm.html");
        } else if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\helm.pdf");
        }

        return "Export Report to " + reportFormat;
    }
}
