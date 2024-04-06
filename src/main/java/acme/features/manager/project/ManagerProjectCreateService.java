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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.project.Project;
import acme.roles.Manager;

@Service
public class ManagerProjectCreateService extends AbstractService<Manager, Project> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerProjectRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
		System.out.println("1: " + super.getBuffer().getErrors());
	}

	@Override
	public void load() {
		Project object;
		Manager manager;
		int managerId;

		managerId = super.getRequest().getPrincipal().getActiveRoleId();
		manager = this.repository.findManagerById(managerId);

		object = new Project();
		object.setCode("");
		object.setTitle("");
		object.setAbstractProject("");
		object.setIndication(false);
		object.setCost(null);
		object.setLink("");
		object.setDraftMode(true);
		object.setManager(manager);

		System.out.println("2.1: " + super.getBuffer().getErrors());
		super.getBuffer().addData(object);
		System.out.println("2.2: " + super.getBuffer().getErrors());
	}

	@Override
	public void bind(final Project object) {
		assert object != null;
		System.out.println("3.1: " + super.getBuffer().getErrors());
		super.bind(object, "code", "title", "abstractProject", "indication", "cost", "link");
		System.out.println(object);
		System.out.println("3.2: " + super.getBuffer().getErrors());
	}

	@Override
	public void validate(final Project object) {
		assert object != null;

		boolean state;

		if (!super.getBuffer().getErrors().hasErrors("cost")) {
			state = object.getCost().getAmount() >= 0;
			super.state(state, "cost", "manager.project.form.error.cost");
		}
		System.out.println("4: " + super.getBuffer().getErrors());
	}

	@Override
	public void perform(final Project object) {
		System.out.println("PERFORM");
		assert object != null;
		System.out.println(object);
		System.out.println(object.getManager());
		this.repository.save(object);
	}

	@Override
	public void unbind(final Project object) {
		Dataset dataset;
		System.out.println("CARGA");
		dataset = super.unbind(object, "code", "title", "abstractProject", "indication", "cost", "link");

		super.getBuffer().addData(dataset);

	}

}
