package com.hias.apps.service;

import java.util.List;

import com.hias.apps.repository.NotifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hias.apps.domain.Notification;

@Service
public class NotifService {

	@Autowired
	private NotifRepository notifRepository;
	
	/**List*/
//	public List<Notification> getListNotif(){
//		return notifRepository.findAll();
//	}
	
	public List<Notification> getListNotif() {
		List<Notification> result = notifRepository.findAll();
		return result;
	}
}

