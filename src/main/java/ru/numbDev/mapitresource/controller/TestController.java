package ru.numbDev.mapitresource.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.Map;

@Controller
public class TestController {

    @GetMapping(path = "/test", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String test(@RequestParam Map<String, Object> params) {
        var code =  (String) params.get("code");
        System.out.println(code);
//        return code;
//        return "return";
        return """
                <!DOCTYPE html>
                <html lang="en">
                                

//                <body>
//                </body>
                                
                </html>
                """;
    }
}
