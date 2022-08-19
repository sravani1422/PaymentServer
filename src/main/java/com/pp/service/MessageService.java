package com.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pp.beans.Message;
import com.pp.repo.MessageRepo;

@Service
public class MessageService implements IMessageService {
	
	@Autowired
	MessageRepo messageRepo;
	
	@Override
	public Message findById(String id) {
		return messageRepo.findById(id).get();
	}
	

}
