package com.pp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pp.beans.Message;

public interface MessageRepo extends JpaRepository<Message, String> {

}
