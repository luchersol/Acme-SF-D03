/*
 * DeveloperTrainingModuleController.java
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

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.training.Training;
import acme.roles.Developer;

@Controller
public class DeveloperTrainingController extends AbstractController<Developer, Training> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private DeveloperTrainingListMineService	listMineService;

	@Autowired
	private DeveloperTrainingShowService		showService;

	@Autowired
	private DeveloperTrainingCreateService		createService;

	@Autowired
	private DeveloperTrainingUpdateService		updateService;

	@Autowired
	private DeveloperTrainingDeleteService		deleteService;

	@Autowired
	private DeveloperTrainingPublishService		publishService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("update", this.updateService);
		super.addBasicCommand("delete", this.deleteService);

		super.addCustomCommand("publish", "perform", this.publishService);
		super.addCustomCommand("list-mine", "list", this.listMineService);
	}

}
