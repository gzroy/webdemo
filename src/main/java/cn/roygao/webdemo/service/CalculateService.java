package cn.roygao.webdemo.service;

import org.springframework.stereotype.Service;

@Service
public class CalculateService {
    public double doSomeCalculate(double input) {
		return input*input + 100;
	}
}
