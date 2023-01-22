package codes.clebermacieski.jakartaeedeepdive_buildatodoapp.resources;

import codes.clebermacieski.jakartaeedeepdive_buildatodoapp.entity.TodoUser;
import codes.clebermacieski.jakartaeedeepdive_buildatodoapp.service.PersistenceService;
import codes.clebermacieski.jakartaeedeepdive_buildatodoapp.service.QueryService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("user")
public class TodoUserRest {

	@Inject
	private PersistenceService persistenceService;
	@Inject
	private QueryService queryService;

	@Path("create")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTodoUser(@NotNull @Valid TodoUser todoUser) {
		persistenceService.saveTodoUser(todoUser);

		return Response.ok(todoUser).build();
	}

	@PUT
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateTodoUser(@NotNull @Valid TodoUser todoUser) {
		persistenceService.updateTodoUser(todoUser);

		return Response.ok(todoUser).build();
	}

	@GET
	@Path("find/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public TodoUser findTodoUserByEmail(@NotNull @PathParam("email") String email) {
		return queryService.findTodoUserByEmail(email);
	}

	@GET
	@Path("search")
	@Produces(MediaType.APPLICATION_JSON)
	public TodoUser findTodoUserByEmailQueryParam(@NotNull @QueryParam("email") @DefaultValue("") String email) {
		return queryService.findTodoUserByEmail(email);
	}

	@GET
	@Path("find")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findTodoUserByName(@NotNull @PathParam("name") String name) {
		return Response.ok(queryService.findTodoUsersByName(name)).build();
	}

	@GET
	@Path("count")
	public Response countToUserByEmail(@QueryParam("email") @NotNull String email) {
		return Response.ok(queryService.countTodoUserByEmail(email)).build();
	}

	@GET
	@Path("list")
	public Response listAllTodoUsers() {
		return Response.ok(queryService.findAllTodoUsers()).build();
	}

	@PUT
	@Path("update-email")
	public Response updateEmail(@NotNull @QueryParam("id") Long id, @NotNull @QueryParam("email") String email) {
		TodoUser todoUser = persistenceService.updateTodoUserEmail(id, email);
		return Response.ok(todoUser).build();
	}
}
