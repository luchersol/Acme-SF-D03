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

package acme.features.manager.project;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.project.Project;

@Repository
public interface ManagerProjectRepository extends AbstractRepository {

	@Query("SELECT p FROM Project p WHERE p.id = :id")
	Project findOneProjectById(int id);

	@Query("SELECT p FROM Project p WHERE p.manager.userAccount.id = :managerId")
	Collection<Project> findProjectsByManagerId(int managerId);

	@Query("SELECT p FROM Project p WHERE p.manager.id = :managerId AND p.draftMode = FALSE")
	Collection<Project> findPublishedProjects(int managerId);

}
