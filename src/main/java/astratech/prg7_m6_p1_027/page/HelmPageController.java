package astratech.prg7_m6_p1_027.page;

import astratech.prg7_m6_p1_027.model.Helm;
import astratech.prg7_m6_p1_027.service.HelmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HelmPageController {
    @Autowired
    HelmService helmService;

    @RequestMapping("/listhelmth")
    public String getHelms(Model model) {
        List<Helm> helms = helmService.getHelms();
        model.addAttribute("listHelm", helms);
        return "listhelmth";
    }

    @RequestMapping("/viewaddhelmth")
    public String viewAddHelm(Model model) {
        model.addAttribute("helmObject",  new Helm());
        return "addhelmth";
    }

    @PostMapping("/saveHelmth")
    public String addHelm(Helm helm) {
        helmService.save(helm);
        return "redirect:/listhelmth";
    }

    @RequestMapping("/viewedithelmth/{id}")
    public String viewEditHelm(@PathVariable("id") Integer id, Model model) {
        Helm helm = helmService.getHelm(id);
        model.addAttribute("helmObject", helm);
        return "edithelmth";
    }

    @PostMapping("/updateHelmth")
    public String updateHelm(Helm helm) {
        helmService.save(helm);
        return "redirect:/listhelmth";
    }

    @RequestMapping("/deleteHelmth/{id}")
    public String deleteHelm(@PathVariable("id") Integer id) {
        helmService.deleteHelm(id);
        return "redirect:/listhelmth";
    }
}
