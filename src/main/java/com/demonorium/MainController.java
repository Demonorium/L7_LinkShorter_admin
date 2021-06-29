package com.demonorium;


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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class MainController {
    @Autowired
    Repo repo;


    UrlValidator validator = new UrlValidator();

    @GetMapping("/")
    String mainScreen(Model model) {
        model.addAttribute("urlContainer", new UrlContainer());
        return "home";
    }

    @GetMapping("/shr/{target}")
    String mainScreen(@PathVariable String target, Model model) {
        List<UrlInfo> info = repo.getByShorten(target);
        if (info.isEmpty())
            return "error";

        return "redirect:" + info.get(0).input;
    }



    @PostMapping("/")
    String shorten(@ModelAttribute("urlContainer") UrlContainer urlContainer, HttpServletRequest request, Model model) {
        List<UrlInfo> info = repo.getByInput(urlContainer.url);
        if (info.isEmpty()) {
            if (!validator.isValid(urlContainer.url)) {
                urlContainer.setError(true);
                urlContainer.setCorrect(false);
                return "home";
            }

            UrlInfo urlInfo = new UrlInfo(urlContainer.url);
            repo.save(urlInfo);
            urlInfo.setShorten(Long.toHexString(urlInfo.getId()) +"_"+ Long.toHexString(new Date().getTime()));
            repo.save(urlInfo);
            urlContainer.setShorten(request.getRequestURL().toString() + "shr/" + urlInfo.getShorten());
        } else {
            urlContainer.setShorten(request.getRequestURL().toString() + "shr/" + info.get(0).getShorten());
        }
        urlContainer.setError(false);
        urlContainer.setCorrect(true);
        return "home";
    }

}
