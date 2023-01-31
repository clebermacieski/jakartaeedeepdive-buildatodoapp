package codes.clebermacieski.jakartaeedeepdive_buildatodoapp.config;

import java.util.HashMap;
import java.util.Map;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
	final Map<String, String> myViolationMap = new HashMap<>();

	@Override
	public Response toResponse(ConstraintViolationException exception) {

		for (ConstraintViolation constraintViolation : exception.getConstraintViolations()) {
			String property = constraintViolation.getPropertyPath().toString().split("\\.")[2];
			String message = constraintViolation.getMessage();

			myViolationMap.put(property, message);
		}

		return Response.status(Response.Status.EXPECTATION_FAILED).entity(myViolationMap).build();
	}

}
