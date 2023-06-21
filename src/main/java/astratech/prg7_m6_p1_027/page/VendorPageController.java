package astratech.prg7_m6_p1_027.page;

import astratech.prg7_m6_p1_027.model.Vendor;
import astratech.prg7_m6_p1_027.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class VendorPageController {
    @Autowired
    VendorService vendorService;

    @RequestMapping("/listvendorth")
    public String getVendors(Model model) {
        List<Vendor> vendors = vendorService.getVendors();
        model.addAttribute("listVendor", vendors);
        return "listvendorth";
    }

    @RequestMapping("/viewaddvendorth")
    public String viewAddVendor(Model model) {
        model.addAttribute("vendorObject",  new Vendor());
        return "addvendorth";
    }

    @PostMapping("/saveVendorth")
    public String addVendor(Vendor vendor) {
        vendorService.save(vendor);
        return "redirect:/listvendorth";
    }

    @RequestMapping("/vieweditvendorth/{id}")
    public String viewEditVendor(@PathVariable("id") Integer id, Model model) {
        Vendor vendor = vendorService.getVendor(id);
        model.addAttribute("vendorObject", vendor);
        return "editvendorth";
    }

    @PostMapping("/updateVendorth")
    public String updateVendor(Vendor vendor) {
        vendorService.save(vendor);
        return "redirect:/listvendorth";
    }

    @RequestMapping("/deleteVendorth/{id}")
    public String deleteVendor(@PathVariable("id") Integer id) {
        vendorService.deleteVendor(id);
        return "redirect:/listvendorth";
    }
}
