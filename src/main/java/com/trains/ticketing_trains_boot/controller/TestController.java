package com.trains.ticketing_trains_boot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/test")
@RequiredArgsConstructor
public class TestController {
  @GetMapping
  public void ResponseTest(){
    int NumUno = 12;
    int NumDos = 14;
    System.out.println(NumUno + NumDos);
  }
}
