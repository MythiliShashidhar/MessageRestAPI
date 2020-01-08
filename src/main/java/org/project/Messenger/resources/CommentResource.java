package org.project.Messenger.resources;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.project.Messenger.DTO.CommentDTO;
import org.project.Messenger.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {
	
	@Inject
	private CommentService commentService;
	
	@GET
	public List<CommentDTO> getAllComments(@PathParam("messageId") long messageId) {
		try {
			return commentService.getAllComments(messageId);
		} catch (IllegalAccessException | InvocationTargetException e) {
			System.out.println(e);
		}
		return null;
	}
	
	
	@GET

	@Path("/{commentId}")
	public String getComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		try {
			return commentService.getComment(messageId, commentId);
		} catch (IllegalAccessException | InvocationTargetException e) {
			System.out.println(e);
		}
		return null;
	}


	public CommentService getCommentService() {
		return commentService;
	}


	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	  
	/*
	 * @POST
	 * 
	 * @Path("/{commentId}") public Comment addComment(@PathParam("messageId") long
	 * messageId, Comment comment) { return commentService.addComment(messageId,
	 * comment); }
	 * 
	 * @PUT
	 * 
	 * @Path("/{commentId}") public Comment updateComment(@PathParam("messageId")
	 * long messageId, @PathParam("commentId") long commentId, Comment comment) {
	 * comment.setId(commentId); return commentService.updateComment(messageId,
	 * comment); }
	 * 
	 * @DELETE
	 * 
	 * @Path("/{commentId}") public Comment deleteComment(@PathParam("messageId")
	 * long messageId, @PathParam("commentId") long commentId) { return
	 * commentService.removeMessage(messageId, commentId); }
	 */
	 

}
