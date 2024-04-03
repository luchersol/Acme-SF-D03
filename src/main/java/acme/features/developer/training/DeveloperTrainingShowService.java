/*
 * DeveloperTrainingModuleShowService.java
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
public class DeveloperTrainingShowService extends AbstractService<Developer, Training> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private DeveloperTrainingRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Training object;
		int id;

		id = this.getRequest().getData("id", int.class);
		object = this.repository.findOneTrainingById(id);

		assert Objects.nonNull(object);
		super.getBuffer().addData(object);
	}

	@Override
	public void unbind(final Training object) {
		assert object != null;

		Dataset dataset;
		dataset = super.unbind(object, "code", "details");

		super.getResponse().addData(dataset);
	}

}
