/*
 * AdministratorDashboardRepository.java
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

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface ManagerDashboardRepository extends AbstractRepository {

	@Query("SELECT COUNT(DISTINCT pu.project) FROM ProjectUserStory pu WHERE pu.userStory.priority = acme.entities.project.PriorityUserStory.MUST")
	Integer totalNumberProjectMust();

	@Query("SELECT COUNT(DISTINCT pu.project) FROM ProjectUserStory pu WHERE pu.userStory.priority = acme.entities.project.PriorityUserStory.SHOULD")
	Integer totalNumberProjectShould();

	@Query("SELECT COUNT(DISTINCT pu.project) FROM ProjectUserStory pu WHERE pu.userStory.priority = acme.entities.project.PriorityUserStory.COULD")
	Integer totalNumberProjectCould();

	@Query("SELECT COUNT(DISTINCT pu.project) FROM ProjectUserStory pu WHERE pu.userStory.priority = acme.entities.project.PriorityUserStory.WONT")
	Integer totalNumberProjectWont();

	@Query("SELECT avg(us.estimatedCost) FROM UserStory us")
	Double averageEstimatedCostUserStories();

	@Query("SELECT stddev(us.estimatedCost) FROM UserStory us")
	Double deviationEstimatedCostUserStories();

	@Query("SELECT min(us.estimatedCost) FROM UserStory us")
	Double minimumEstimatedCostUserStories();

	@Query("SELECT max(us.estimatedCost) FROM UserStory us")
	Double maximumEstimatedCostUserStories();

	@Query("SELECT p.cost.currency, avg(p.cost.amount) FROM Project p GROUP BY p.cost.currency")
	Collection<Object[]> averageEstimatedCostProjects();

	@Query("SELECT p.cost.currency, stddev(p.cost.amount) FROM Project p GROUP BY p.cost.currency")
	Collection<Object[]> deviationEstimatedCostProjects();

	@Query("SELECT p.cost.currency, min(p.cost.amount) FROM Project p GROUP BY p.cost.currency")
	Collection<Object[]> minimumEstimatedCostProjects();

	@Query("SELECT p.cost.currency, max(p.cost.amount) FROM Project p GROUP BY p.cost.currency")
	Collection<Object[]> maximumEstimatedCostProjects();

}
