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

package acme.features.developer.training;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.training.Training;
import acme.roles.Developer;

@Service
public class DeveloperTrainingUpdateService extends AbstractService<Developer, Training> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private DeveloperTrainingRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int id;
		Training object;

		id = this.getRequest().getData("id", int.class);
		object = this.repository.findOneTrainingById(id);
		status = Objects.nonNull(object) && !object.getDraftMode();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Training project = new Training();

		super.getBuffer().addData(project);
	}

	@Override
	public void bind(final Training object) {
		Dataset dataset;

		dataset = super.unbind(object, "");

		super.getResponse().addData(dataset);
	}

	@Override
	public void validate(final Training object) {
		assert object != null;

		boolean confirmation;

		confirmation = super.getRequest().getData("confirmation", boolean.class);
		super.state(confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
	}

	@Override
	public void perform(final Training object) {
		assert object != null;

		this.repository.save(object);
	}

}
