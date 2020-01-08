package org.project.Messenger.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.project.Messenger.DTO.CommentDTO;
import org.project.Messenger.DTO.MessageDTO;
import org.project.Messenger.database.DatabaseClass;
import org.project.Messenger.model.Comment;
import org.project.Messenger.model.Message;
import org.project.Messenger.util.HibernateUtil;
import com.google.gson.Gson;

public class CommentService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	Session session;
	
	public List<CommentDTO> getAllComments(long messageId) throws IllegalAccessException, InvocationTargetException{
		session =  sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Comment where message_id = "+messageId);
		List<Comment> newComments = query.list();
		List<CommentDTO> responseDTO = new ArrayList<CommentDTO>();
		for (Comment comment : newComments) {
			CommentDTO cmntDTO = new CommentDTO();
			BeanUtils.copyProperties(cmntDTO, comment);
			responseDTO.add(cmntDTO);
		}
		session.getTransaction().commit();
		return responseDTO;				
	}
	
	public String getComment(long messageId, long commentId) throws IllegalAccessException, InvocationTargetException {
		System.out.println();
		session =  sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Comment where message_id = :messageId and id = :commentId ");
		Comment newComment = (Comment) query.setParameter("messageId", messageId).setParameter("commentId", commentId).uniqueResult();
		CommentDTO cmntDTO = new CommentDTO();
		BeanUtils.copyProperties(cmntDTO, newComment);
		session.getTransaction().commit();
		Gson gson = new Gson();
		String jsonInString = gson.toJson(cmntDTO);
		return jsonInString;		
	}
	  
	
	/*
	 * public Comment addComment(long messageId, Comment comment) { Map<Long,
	 * Comment> comments = messages.get(messageId).getComments();
	 * comment.setId(comments.size() + 1); comments.put(comment.getId(), comment);
	 * return comment; }
	 */
	  
	/*
	 * public Comment updateComment(long messageId, Comment comment){ Map<Long,
	 * Comment> comments = messages.get(messageId).getComments(); if(comment.getId()
	 * <= 0) { return null; } comments.put(comment.getId(), comment); return
	 * comment; }
	 * 
	 * public Comment removeMessage(long messageId, long commentId){ Map<Long,
	 * Comment> comments = messages.get(messageId).getComments(); return
	 * comments.remove(commentId); }
	 */
	 
	
}
