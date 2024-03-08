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

package acme.features.manager.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.datatypes.Money;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.form.ManagerForm;
import acme.roles.Manager;

@Service
public class ManagerDashboardShowService extends AbstractService<Manager, ManagerForm> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerDashboardRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		ManagerForm dashboard = new ManagerForm();
		Integer totalNumberProjectMust = this.repository.totalNumberProjectMust();
		Integer totalNumberProjectShould = this.repository.totalNumberProjectShould();
		Integer totalNumberProjectCould = this.repository.totalNumberProjectCould();
		Integer totalNumberProjectWont = this.repository.totalNumberProjectWont();
		Double averageEstimatedCostUserStories = this.repository.averageEstimatedCostUserStories();
		Double deviationEstimatedCostUserStories = this.repository.deviationEstimatedCostUserStories();
		Double minimumEstimatedCostUserStories = this.repository.minimumEstimatedCostUserStories();
		Double maximumEstimatedCostUserStories = this.repository.maximumEstimatedCostUserStories();
		Money averageEstimatedCostProjects = this.repository.averageEstimatedCostProjects();
		Money deviationEstimatedCostProjects = this.repository.deviationEstimatedCostProjects();
		Money minimumEstimatedCostProjects = this.repository.minimumEstimatedCostProjects();
		Money maximumEstimatedCostProjects = this.repository.maximumEstimatedCostProjects();

		dashboard.setTotalNumberProjectMust(totalNumberProjectMust);
		dashboard.setTotalNumberProjectShould(totalNumberProjectShould);
		dashboard.setTotalNumberProjectCould(totalNumberProjectCould);
		dashboard.setTotalNumberProjectWont(totalNumberProjectWont);
		dashboard.setAverageEstimatedCostUserStories(averageEstimatedCostUserStories);
		dashboard.setDeviationEstimatedCostUserStories(deviationEstimatedCostUserStories);
		dashboard.setMinimumEstimatedCostUserStories(minimumEstimatedCostUserStories);
		dashboard.setMaximumEstimatedCostUserStories(maximumEstimatedCostUserStories);
		dashboard.setAverageEstimatedCostProjects(averageEstimatedCostProjects);
		dashboard.setDeviationEstimatedCostProjects(deviationEstimatedCostProjects);
		dashboard.setMinimumEstimatedCostProjects(minimumEstimatedCostProjects);
		dashboard.setMaximumEstimatedCostProjects(maximumEstimatedCostProjects);

		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final ManagerForm object) {
		Dataset dataset;

		dataset = super.unbind(object, //
			"totalNumberProjectMust", "totalNumberProjectShould", //
			"totalNumberProjectCould", "totalNumberProjectWont", //
			"averageEstimatedCostUserStories", "deviationEstimatedCostUserStories", // 
			"minimumEstimatedCostUserStories", "maximumEstimatedCostUserStories", // 
			"averageEstimatedCostProjects", "deviationEstimatedCostProjects", //
			"minimumEstimatedCostProjects", "maximumEstimatedCostProjects");

		super.getResponse().addData(dataset);
	}

}
