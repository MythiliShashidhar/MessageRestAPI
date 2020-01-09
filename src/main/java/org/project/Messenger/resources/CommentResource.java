package org.project.Messenger.resources;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.project.Messenger.DTO.CommentDTO;
import org.project.Messenger.service.CommentService;
import org.project.Messenger.service.MessageService;
import org.project.Messenger.model.Comment;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {
	final static Logger logger = Logger.getLogger(MessageService.class);
	private CommentService commentService = new CommentService();
	
	@GET
	public List<CommentDTO> getAllComments(@PathParam("messageId") long messageId) {
		try {
			return commentService.getAllComments(messageId);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("Exception while getting all comments"+e);
		}
		return null;
	}
	
	
	@GET
	@Path("/{commentId}")
	public CommentDTO getComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		try {
			return commentService.getComment(messageId, commentId);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("Exception while getting comment using commentId"+e);
		}
		return null;
	}
	
	@POST
	public CommentDTO addComment(@PathParam("messageId") long messageId, Comment comment) {
		try {
			return commentService.addComment(messageId, comment);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("Exception while adding comment"+e);
		}
		return null;
	}


	public CommentService getCommentService() {
		return commentService;
	}


	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	  
}
