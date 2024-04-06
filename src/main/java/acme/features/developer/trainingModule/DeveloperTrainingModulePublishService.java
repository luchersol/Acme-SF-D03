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

package acme.features.developer.trainingModule;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.training.DifficultyLevel;
import acme.entities.training.TrainingModule;
import acme.roles.Developer;

@Service
public class DeveloperTrainingModulePublishService extends AbstractService<Developer, TrainingModule> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private DeveloperTrainingModuleRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		// Initialize the required objects
		DifficultyLevel difficultyLevel = DifficultyLevel.ADVANCED;

		// Initialize the date objects
		Date creationMoment = new Date();
		Date updateMoment = new Date();

		// Create a new Training object
		TrainingModule object = new TrainingModule("CODE-123", creationMoment, "Details about the training", difficultyLevel, updateMoment, "http://example.com", 10.0, null, null);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final TrainingModule object) {
		Dataset dataset;

		dataset = super.unbind(object, "");

		super.getResponse().addData(dataset);
	}

	@Override
	public void validate(final TrainingModule object) {
		assert object != null;

		boolean confirmation;

		confirmation = super.getRequest().getData("confirmation", boolean.class);
		super.state(confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
	}

	@Override
	public void perform(final TrainingModule object) {
		assert object != null;

		this.repository.save(object);
	}

}
