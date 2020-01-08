package org.project.Messenger.resources;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.project.Messenger.DTO.MessageDTO;
import org.project.Messenger.model.Message;
import org.project.Messenger.service.MessageService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	
	//private MessageService messageService = new MessageService();
	
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private MessageService messageService = context.getBean("messageService",MessageService.class);
	final static Logger logger = Logger.getLogger(MessageService.class);
	
	@GET
	public List<MessageDTO> getMessages(@QueryParam("author") String author,
							  @QueryParam("start") int start,
							  @QueryParam("size") int size) {
		try {
			if(!author.isEmpty()) {
				return messageService.getAllMessagesForAuthor(author);
			}
			if(start>0&&size>0) {
				return messageService.getAllMessagesPaginated(start, size);
			}
			return messageService.getAllMessages();
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("Exception while copying properties during getMessages"+e);
		}
		return null;
	}	
	
	@GET
	@Path("/{messageId}")
	public MessageDTO getMessage(@PathParam("messageId") long messageId) {
		try {
			return messageService.getMessage(messageId);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("Exception while copying properties during getMessage "+e);
		}
		return null;
	}
	
	@POST
	public Response addMessage(Message message) {
		Message updatedMessage = messageService.addMessage(message);
		return Response.status(Status.CREATED).entity(updatedMessage).build();
	}

	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long messageId, Message message) {
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long messageId) {
		messageService.removeMessage(messageId);
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResouce() {
		return new CommentResource();
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	

}