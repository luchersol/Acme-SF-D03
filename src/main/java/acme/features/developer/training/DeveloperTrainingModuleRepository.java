/*
 * DeveloperTrainingModuleRepository.java
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

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.training.TrainingModule;

@Repository
public interface DeveloperTrainingModuleRepository extends AbstractRepository {

	@Query("select a from TrainingModule a where a.id = :id")
	TrainingModule findOneTrainingModuleById(int id);

	@Query("select a from TrainingModule a")
	Collection<TrainingModule> findAllTrainingModules();

	@Query("select a from TrainingModule a where a.moment > :deadline")
	Collection<TrainingModule> findRecentTrainingModules(Date deadline);

}
