package codes.clebermacieski.jakartaeedeepdive_buildatodoapp.entity;

import java.io.Serializable;
import java.time.LocalTime;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	// TODO
	private LocalTime creation;
	private LocalTime modification;
}
