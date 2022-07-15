package ru.job4j.cars.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.model.Advert;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.AdvertService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class AdvertController {

    static final Logger log = LoggerFactory.getLogger(AdvertController.class);
    private final AdvertService advertService;

    public AdvertController(AdvertService advertService) {
        this.advertService = advertService;
    }

    @GetMapping("/")
    public String getGreeting() {
        log.info("Method {} run", "getGreeting");
        return "index";
    }

    @GetMapping("/ads")
    public String getAdverts(Model model, HttpSession session) {
        List<Advert> adverts = advertService.findAllAdverts();
        model.addAttribute("adverts", adverts);
        model.addAttribute("user", session.getAttribute("user"));

        log.info("Method {} run", "getAdverts");
        return "ads";
    }

    @GetMapping("/add")
    public String addAdvert(@ModelAttribute("advert") Advert advert,
                            Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));

        log.info("Method {} run", "addAdvert");
        return "add";
    }

    @GetMapping("/details{advertId}")
    public String getDetails(@RequestParam(value = "advertId") Integer advertId,
                             Model model, HttpSession session) {
        Advert advert = advertService.findAdvertById(advertId);
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("advert", advert);

        log.info("Method {} run", "getDetails");
        return "details";
    }

    @GetMapping("/myAds")
    public String getMyAds(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Advert> adverts = advertService.findAdvertsByUserId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("adverts", adverts);

        log.info("Method {} run", "getMyAds");
        return "myAds";
    }

    @GetMapping("/del{advertId}")
    public String deleteMyAds(@RequestParam(value = "advertId") Integer advertId) {
        advertService.deleteAdvertById(advertId);

        log.info("Method {} run", "deleteMyAds");
        return "redirect:/myAds";
    }

    @GetMapping("/editAds{advertId}")
    public String getEditAds(@RequestParam(value = "advertId") Integer advertId,
                             Model model,
                             HttpSession session
    ) {
        Advert advert = advertService.findAdvertById(advertId);
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("advert", advert);

        log.info("Method {} run", "getEditAds");
        return "editAds";
    }

    @PostMapping("/saveAds")
    public String editAds(Model model,
                          @ModelAttribute Advert advert,
                          @RequestParam("file") MultipartFile file,
                          HttpServletRequest request) throws IOException {
        HttpSession sc = request.getSession();
        User user = (User) sc.getAttribute("user");
        model.addAttribute("user", user);
        advert.setUser(user);
        advertService.save(advert, file);

        log.info("Method {} run", "editAds");
        return "redirect:/myAds";
    }

    @PostMapping("/find")
    public String filterAdverts(@RequestParam("bodyType") String bodyType,
                                @RequestParam("transmissionname") String transmissionname,
                                @RequestParam("drive") String drive,
                                @RequestParam("fuel") String fuel,
                                Model model, HttpSession session) {
        System.out.println(bodyType);
        List<Advert> adverts = advertService.filterAdverts(bodyType, transmissionname,
                drive, fuel);
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("adverts", adverts);

        log.info("Method {} run", "filterAdverts");
        return "ads";
    }
}
