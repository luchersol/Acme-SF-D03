/*
 * AdministratorDashboardShowService.java
 *
 * Copyright (C) 2012-2024 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.manager.project;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.project.Project;
import acme.roles.Manager;

@Service
public class ManagerProjectDeleteService extends AbstractService<Manager, Project> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerProjectRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int id;
		Project object;

		id = this.getRequest().getData("id", int.class);
		object = this.repository.findOneProjectById(id);
		status = Objects.nonNull(object) && !object.getDraftMode();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Project project = new Project();

		super.getBuffer().addData(project);
	}

	@Override
	public void bind(final Project object) {
		Dataset dataset;

		dataset = super.unbind(object, "");

		super.getResponse().addData(dataset);
	}

	@Override
	public void validate(final Project object) {
		assert object != null;

		boolean confirmation;

		confirmation = super.getRequest().getData("confirmation", boolean.class);
		super.state(confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
	}

	@Override
	public void perform(final Project object) {
		assert object != null;

		this.repository.save(object);
	}

}
