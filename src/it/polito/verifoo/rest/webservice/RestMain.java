package it.polito.verifoo.rest.webservice;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.appender.FileAppender;

import io.swagger.annotations.*;
import it.polito.verifoo.rest.common.LogReader;
import it.polito.verifoo.rest.jaxb.Hyperlinks;
/**
 * This class is the root resources that will provide to the client the links for the other resources
 *
 */

@Path("/")
@Api(value = "/")

public class RestMain {		
	    @GET
	    @ApiOperation(value = "Get Verifoo Webservice Links", notes = "Get Links for Hateoas compliancy"
	    		)
   	    @ApiResponses(value = {
	    		@ApiResponse(code = 200, message = "OK"),
	    		})

		@Produces(MediaType.APPLICATION_XML)
	    public Hyperlinks get(@Context UriInfo uri){
	    	String baseUrl = uri.getBaseUri().toString();
	    	Hyperlinks h = new Hyperlinks();
	    	Hyperlinks.Link l1 = new Hyperlinks.Link();
	    	l1.setRel("self");
	    	l1.setHref(baseUrl);
	    	l1.setType("application/xml");
	    	l1.setMethod("GET");
	    	Hyperlinks.Link l2 = new Hyperlinks.Link();
	    	l2.setRel("deployment");
	    	l2.setHref(baseUrl+"deployment");
	    	l2.setType("application/xml");
	    	l2.setMethod("POST");
	    	Hyperlinks.Link l3 = new Hyperlinks.Link();
	    	l3.setRel("converter");
	    	l3.setHref(baseUrl+"converter");
	    	l3.setType("application/xml");
	    	l3.setMethod("POST");
	    	Hyperlinks.Link l4 = new Hyperlinks.Link();
	    	l4.setRel("log");
	    	l4.setHref(baseUrl+"log");
	    	l4.setType("text/html");
	    	l4.setMethod("GET");
	    	h.getLink().add(l1);
	    	h.getLink().add(l2);
	    	h.getLink().add(l3);
	    	h.getLink().add(l4);
			return h;
	    }
}