package com.example.quartz.controller;

import com.example.quartz.service.QuartzTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuartzController {

    @Autowired
    QuartzTestService quartzService;

    @GetMapping(value = "/addJob")
    public void addJob(){
        quartzService.addJob();
    }

    @GetMapping(value = "/deleteJob")
    public void deleteJob(){
        quartzService.deleteJob();
    }

}
