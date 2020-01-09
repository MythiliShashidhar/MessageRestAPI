package org.project.Messenger.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.project.Messenger.DTO.MessageDTO;
import org.project.Messenger.model.Comment;
import org.project.Messenger.model.Message;
import org.project.Messenger.util.HibernateUtil;

public class MessageService {
	
	final static Logger logger = Logger.getLogger(MessageService.class);
	
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	Session session;
	
	public MessageService() {
	}
	
	public List<MessageDTO> getAllMessages() throws IllegalAccessException, InvocationTargetException {
		logger.info("Retrieve all messages");
		session =  sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("From Message");
		List<Message> gotMessages = query.list();		
		List<MessageDTO> responseDTOs = new ArrayList<MessageDTO>();
		
		for (Message message : gotMessages) {
			MessageDTO msgDTO = new MessageDTO();
			BeanUtils.copyProperties(msgDTO, message);
			
			//All comments for each message
			for (Comment commentResponse : message.getComments()) {
				Comment commentDTO = new Comment();
				commentDTO.setMessage(commentResponse.getMessage());
				commentDTO.setAuthor(commentResponse.getAuthor());
				msgDTO.setComment(commentDTO);
			}
			responseDTOs.add(msgDTO);
		}
		session.getTransaction().commit();
		session.close();
		
		//facilitates serialization of Java objects to JSON
		//Gson gson = new Gson();
		//String response = gson.toJson(responseDTOs);
		
		return responseDTOs;
	}
	
	public List<MessageDTO> getAllMessagesForAuthor(String author){
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("From Message where author = :author");
		query.setParameter("author", author);
		List<MessageDTO> messages = query.list();
		
		session.getTransaction().commit();
		session.close();
		return messages;	
	}
	
	public List<MessageDTO> getAllMessagesPaginated(int start, int size){
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("From Message");
		query.setFirstResult(start);
		query.setMaxResults(size);
		List<MessageDTO> messageDTOs = query.list();
		
		session.getTransaction().commit();
		session.close();
		return messageDTOs;
	}
	
	public MessageDTO getMessage(long id) throws IllegalAccessException, InvocationTargetException{
		session =  sessionFactory.openSession();
		session.beginTransaction();
		Message newMessage = session.get(Message.class, id);
		MessageDTO msgDTO = new MessageDTO();
		BeanUtils.copyProperties(msgDTO, newMessage);
		session.getTransaction().commit();
		session.close();
		return msgDTO;
	}
	
	public Message addMessage(Message message){	
		session =  sessionFactory.openSession();
		session.beginTransaction();
		session.save(message);
		session.getTransaction().commit();
		session.close();
		return message;		
	}
	
	public Message updateMessage(Message message){
		session =  sessionFactory.openSession();
		session.beginTransaction();
		session.update(message);
		session.getTransaction().commit();
		session.close();
		return message;
	}

	public void removeMessage(long id){
		session =  sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("delete from Message where id = "+id);
		query.list();
		session.getTransaction().commit();
		session.close();
	}
	
}
