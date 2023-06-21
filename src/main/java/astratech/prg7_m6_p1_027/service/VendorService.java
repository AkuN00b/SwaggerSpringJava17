package astratech.prg7_m6_p1_027.service;

import astratech.prg7_m6_p1_027.model.Vendor;
import astratech.prg7_m6_p1_027.repository.VendorRepository;
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
public class VendorService {
    @Autowired
    VendorRepository vendorRepository;

    public boolean save(Vendor vendor) {
        Vendor result = vendorRepository.save(vendor);
        boolean isSuccess = true;

        if (result == null) {
            isSuccess = false;
        }

        return isSuccess;
    }

    public Vendor getVendor(Integer idVendor) {
        Vendor vendor = vendorRepository.getVendorByIdVendor(idVendor);
        return vendor;
    }

    public List<Vendor> getVendors() {
        return vendorRepository.findAll();
    }

    public boolean update(Vendor vendor) {
        Vendor result = vendorRepository.save(vendor);
        boolean isSuccess = true;

        if (result == null) {
            isSuccess = false;
        }

        return isSuccess;
    }

    public void deleteVendor(Integer idVendor) {
        vendorRepository.deleteById(idVendor);
    }

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\LENOVO\\Downloads";
        List<Vendor> vendors = (List<Vendor>) vendorRepository.findAll();
        File file = ResourceUtils.getFile("classpath:templates/PRG7_M6_P1_Vendor.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(vendors);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Gerlando Corputty");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\vendor.html");
        } else if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\vendor.pdf");
        }

        return "Export Report to " + reportFormat;
    }
}
