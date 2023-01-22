package codes.clebermacieski.jakartaeedeepdive_buildatodoapp.resources;

import java.time.LocalDate;

import codes.clebermacieski.jakartaeedeepdive_buildatodoapp.entity.Todo;
import codes.clebermacieski.jakartaeedeepdive_buildatodoapp.entity.TodoUser;
import codes.clebermacieski.jakartaeedeepdive_buildatodoapp.service.PersistenceService;
import codes.clebermacieski.jakartaeedeepdive_buildatodoapp.service.QueryService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("todo")
public class TodoRest {

	@Inject
	private PersistenceService persistenceService;

	@Inject
	private QueryService queryService;

	@Path("create")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTodo(@NotNull @Valid Todo todo) {
		persistenceService.saveTodo(todo);

		return Response.ok(todo).build();
	}

	@GET
	@Path("list")
	public Response listTodo() {
		return Response.ok(queryService.findAllTodos()).build();
	}

	@GET
	@Path("find")
	public Response findTodoById(@NotNull @QueryParam("todo_id") Long todo_id) {
		return Response.ok(queryService.findTodoById(todo_id)).build();
	}

	@PUT
	@Path("mark")
	public Response markTodoAsCompleted(@NotNull @QueryParam("todo_id") Long todo_id) {
		queryService.markTodoAsCompleted(todo_id);
		return Response.ok().build();
	}

	@GET
	@Path("completed")
	public Response getAllCompleted() {
		return Response.ok(queryService.getAllCompletedTodos()).build();
	}

	@GET
	@Path("uncompleted")
	public Response getAllUncompleted() {
		return Response.ok(queryService.getAllUncompletedTodos()).build();
	}

	@GET
	@Path("due-date")
	public Response getTodoByDueDate(
			@NotNull @QueryParam("date") @Pattern(regexp = "^\\d{2}-\\d{2}\\d{4}$") String date) {
		LocalDate dueDate = LocalDate.parse(date);
		return Response.ok(queryService.getTodosByDueDate(dueDate)).build();
	}
	
	@PUT
	@Path("archived")
	public Response markTodoAsArchived(@NotNull @QueryParam("todo_id") Long todo_id) {
		queryService.markTodoAsArchived(todo_id);
		return Response.ok().build();
	}
	
}
