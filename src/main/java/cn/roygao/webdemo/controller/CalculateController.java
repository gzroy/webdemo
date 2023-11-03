package cn.roygao.webdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.roygao.webdemo.service.CalculateService;

@Controller
public class CalculateController {
    private final CalculateService service;

    public CalculateController(CalculateService service) {
        this.service = service;
    }

    @GetMapping("/calculate")
	public @ResponseBody double calculate(@RequestParam(value = "input") double input) {
		double result = service.doSomeCalculate(input);
		return result;
	}
}
