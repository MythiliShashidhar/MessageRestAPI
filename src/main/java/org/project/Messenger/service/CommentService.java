package org.project.Messenger.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.project.Messenger.DTO.CommentDTO;
import org.project.Messenger.model.Comment;
import org.project.Messenger.model.Message;
import org.project.Messenger.util.HibernateUtil;

public class CommentService {
	
	
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
	
	public CommentDTO getComment(long messageId, long commentId) throws IllegalAccessException, InvocationTargetException {
		session =  sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Comment where message_id = :messageId and id = :commentId ");
		Comment newComment = (Comment) query.setParameter("messageId", messageId).setParameter("commentId", commentId).uniqueResult();
			
		CommentDTO cmntDTO = new CommentDTO();
		BeanUtils.copyProperties(cmntDTO, newComment);
		
		session.getTransaction().commit();
		session.close();
	
		return cmntDTO;		
	}
	
	public CommentDTO addComment(long messageId, Comment comment) throws IllegalAccessException, InvocationTargetException {
		session =  sessionFactory.openSession();
		session.beginTransaction();
		
		Message msg = new Message();
		msg.setId(messageId);
		comment.setMessageParent(msg);
		comment.setCreated(new Date());
		session.saveOrUpdate(comment);
		
		CommentDTO cmntDTO = new CommentDTO();
		BeanUtils.copyProperties(cmntDTO, comment);
		
		session.getTransaction().commit();
		session.close();
		
		return cmntDTO;
	}
	 
	
}
