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

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.form.Dashboard;
import acme.roles.Manager;

@Service
public class ManagerDashboardShowService extends AbstractService<Manager, Dashboard> {

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
		Dashboard dashboard = new Dashboard();

		Integer totalNumberProjectMust = this.repository.totalNumberProjectMust();
		Integer totalNumberProjectShould = this.repository.totalNumberProjectShould();
		Integer totalNumberProjectCould = this.repository.totalNumberProjectCould();
		Integer totalNumberProjectWont = this.repository.totalNumberProjectWont();
		Double averageEstimatedCostUserStories = this.repository.averageEstimatedCostUserStories();
		Double deviationEstimatedCostUserStories = this.repository.deviationEstimatedCostUserStories();
		Double minimumEstimatedCostUserStories = this.repository.minimumEstimatedCostUserStories();
		Double maximumEstimatedCostUserStories = this.repository.maximumEstimatedCostUserStories();
		System.out.println("/");
		//		System.out.println(this.repository.averageEstimatedCostProjects());
		//		System.out.println(this.repository.deviationEstimatedCostProjects());
		//		System.out.println(this.repository.minimumEstimatedCostProjects());
		System.out.println(this.repository.maximumEstimatedCostProjects());
		System.out.println("/");
		Collection<Object[]> averageEstimatedCostProjects = this.repository.averageEstimatedCostProjects();
		Collection<Object[]> deviationEstimatedCostProjects = this.repository.deviationEstimatedCostProjects();
		Collection<Object[]> minimumEstimatedCostProjects = this.repository.minimumEstimatedCostProjects();
		Collection<Object[]> maximumEstimatedCostProjects = this.repository.maximumEstimatedCostProjects();

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
	public void unbind(final Dashboard object) {
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
