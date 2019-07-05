package com.myrest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myrest.model.WorldModel;

@RestController
public class HelloWorld {

	@GetMapping(path = "/hello-world")
	public WorldModel helloWorld() {

		return new WorldModel("Hellow World");
	}

}
