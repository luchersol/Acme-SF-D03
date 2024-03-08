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

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.data.datatypes.Money;
import acme.client.repositories.AbstractRepository;

@Repository
public interface ManagerDashboardRepository extends AbstractRepository {

	@Query("SELECT COUNT(DISTINCT us.project) FROM UserStory us WHERE us.priority = MUST")
	Integer totalNumberProjectMust();

	@Query("SELECT COUNT(DISTINCT us.project) FROM UserStory us WHERE us.priority = SHOULD")
	Integer totalNumberProjectShould();

	@Query("SELECT COUNT(DISTINCT us.project) FROM UserStory us WHERE us.priority = COULD")
	Integer totalNumberProjectCould();

	@Query("SELECT COUNT(DISTINCT us.project) FROM UserStory us WHERE us.priority = WONT")
	Integer totalNumberProjectWont();

	@Query("SELECT avg(us.estimatedCost.amount) FROM UserStory us")
	Double averageEstimatedCostUserStories();

	@Query("SELECT stddev(us.estimatedCost.amount) FROM UserStory us")
	Double deviationEstimatedCostUserStories();

	@Query("SELECT min(us.estimatedCost.amount) FROM UserStory us")
	Double minimumEstimatedCostUserStories();

	@Query("SELECT max(us.estimatedCost.amount) FROM UserStory us")
	Double maximumEstimatedCostUserStories();

	@Query("SELECT avg(p.cost.amount) FROM Project p")
	Money averageEstimatedCostProjects();

	@Query("SELECT stddev(p.cost.amount) FROM Project p")
	Money deviationEstimatedCostProjects();

	@Query("SELECT min(p.cost.amount) FROM Project p")
	Money minimumEstimatedCostProjects();

	@Query("SELECT max(p.cost.amount) FROM Project p")
	Money maximumEstimatedCostProjects();

}
