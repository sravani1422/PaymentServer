package com.pp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pp.service.IMessageService;
import com.pp.beans.Message;

@RestController
@CrossOrigin(origins="*")
public class MessageController {
	
	@Autowired
	IMessageService messageService;
	
	@GetMapping(value="message/{id}")
	public ResponseEntity<Message> getMessage(@PathVariable("id") String id) {
		Message message = messageService.findById(id);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

}
