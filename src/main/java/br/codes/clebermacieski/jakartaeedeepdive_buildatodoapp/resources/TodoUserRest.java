package br.codes.clebermacieski.jakartaeedeepdive_buildatodoapp.resources;

import br.codes.clebermacieski.jakartaeedeepdive_buildatodoapp.entity.TodoUser;
import br.codes.clebermacieski.jakartaeedeepdive_buildatodoapp.service.PersistenceService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("user")
public class TodoUserRest {
	
	@Inject
	private PersistenceService persistenceService;
	
	@Path("create")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTodoUser(TodoUser todoUser) {
		persistenceService.saveTodoUser(todoUser);
		
		return Response.ok(todoUser).build();
	}
}
