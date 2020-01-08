package org.project.Messenger.test;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/paramDemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class ParamsTest {

	@GET
	@Path("annotations")
	public String getAllAnnotations(@MatrixParam("param") String matrixParam,
									@HeaderParam("headerParam") String headerParam,
									@CookieParam("cookieParam") String cookieParam) {
		return "MatrixParam = "+matrixParam +
				"HeaderParam = " + headerParam +
				"CookieParam = " + cookieParam;
	}
}
