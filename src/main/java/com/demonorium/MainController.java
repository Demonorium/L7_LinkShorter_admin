package com.demonorium;


import com.demonorium.database.UrlRepository;
import com.demonorium.database.UrlEntity;
import com.demonorium.view.UrlPageView;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    UrlRepository urlRepository;


    UrlValidator validator = new UrlValidator();

    @GetMapping("/")
    String mainScreen(Model model) {
        model.addAttribute("urlPageView", new UrlPageView());
        return "home";
    }

    @GetMapping("/admin")
    String admin(Model model) {
        model.addAttribute("refs", urlRepository.findAll());
        return "admin_console";
    }

    @GetMapping("/admin/remove/{id}")
    String adminRemove(@PathVariable Long id, Model model) {
        if (id == null)
            return "redirect:/admin";

        urlRepository.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/shr/{target}")
    String mainScreen(@PathVariable String target, Model model) {
        int index = target.indexOf('_');
        if (index > 0) {
            try {
                Long id = Long.parseLong(target.substring(0, index), 16);
                Optional<UrlEntity> info = urlRepository.findById(id);
                if (info.isPresent()) {
                    if (info.get().getTime().equals(target.substring(index+1)))
                        return "redirect:" + info.get().getInput();
                }
            } catch (NumberFormatException ignored) {
            }
        }
        return "error";
    }

    @GetMapping("/error")
    String err() {
        return "error";
    }

    @PostMapping("/")
    String shorten(@ModelAttribute("urlPageView") UrlPageView urlPageView, HttpServletRequest request, Model model) {
        List<UrlEntity> info = urlRepository.getByInput(urlPageView.getUrl());
        if (info.isEmpty()) {
            if ((urlPageView.getUrl().length() > 4095) || !validator.isValid(urlPageView.getUrl())) {
                urlPageView.setBadUrl(true);
                urlPageView.setCorrect(false);
                return "home";
            }

            UrlEntity urlEntity = new UrlEntity(urlPageView.getUrl(), Long.toHexString(new Date().getTime()));
            urlRepository.save(urlEntity);
            urlPageView.setShorten(request.getRequestURL().toString() + "shr/" + Long.toHexString(urlEntity.getId()) + '_' + urlEntity.getTime());
        } else {
            urlPageView.setShorten(request.getRequestURL().toString() + "shr/" + Long.toHexString(info.get(0).getId()) + '_' + info.get(0).getTime());
        }
        urlPageView.setBadUrl(false);
        urlPageView.setCorrect(true);
        return "home";
    }

}
