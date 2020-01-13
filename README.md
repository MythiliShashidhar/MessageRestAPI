# MessageRestAPI
Simple integration project

This is a simple Rest API project with connection to DB using hibernate.

Here there are Messages and for each message we can have multiple comments. 
We can perform multiple operations on messages and comments using the below details.

Resources URI:

for Message operations

1. Get all messages(with all the comments for the message) 
	HTTP Method	GET
	
	//all messages
	http://<server-url>/Messenger/webapi/messages/
	
	//all messages from a particular author
	http://<server-url>/Messenger/webapi/messages?author={authorname}
	
	//messages for a particular range
	http://<server-url>/Messenger/webapi/messages?start=1&size=1
	
2. Get a message by messageId
	HTTP Method	GET
	http://<server-url>/Messenger/webapi/messages/{messageId}

3. Add a message
	HTTP Method	POST
	http://<server-url>/Messenger/webapi/messages/
	pass message in body

4. Update a message
	HTTP Method	PUT
	http://<server-url>/Messenger/webapi/messages/
 
5. Delete message
	HTTP Method	DELETE
	http://<server-url>/Messenger/webapi/messages/{messageId}
	
	
for Comments operations

1. Get all comments
	HTTP Method	GET
	http://<server-url>/Messenger/webapi/messages/{messageId}/comments/
	
2. Get a comment by commentId
	HTTP Method	GET
	http://<server-url>/Messenger/webapi/messages/{messageId}/comments/{commentId}

3. Add a comment
	HTTP Method	POST
	http://<server-url>/Messenger/webapi/messages/{messageId}/comments/
	pass Comment in body
