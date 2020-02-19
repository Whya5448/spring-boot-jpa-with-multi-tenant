package com.example.demo.test;

import com.example.demo.config.Engine;
import com.example.demo.config.EngineRepository;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TestController {

    private EngineRepository engineRepository;

    public TestController(EngineRepository engineRepository) {
        this.engineRepository = engineRepository;
    }

    @GetMapping("/d1")
    public List<Engine> abc1(HttpSession session) {
        session.setAttribute("database", "DEV:8a10d8e6-204d-4f8e-bddf-9d7f4f0c8f1e");
        return engineRepository.findAll();
    }


    @GetMapping("/d2")
    public List<Engine> abc2(HttpSession session) {
        session.setAttribute("database", "PRD:8a10d8e6-204d-4f8e-bddf-9d7f4f0c8f1f");
        return engineRepository.findAll();
    }

    @GetMapping(path = {"/login", "/sign/**"})
    public ModelAndView logi2n() {
        return new ModelAndView("/WEB-INF/views/login.jsp");
    }

}