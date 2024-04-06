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

package acme.features.manager.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.project.Project;
import acme.roles.Manager;

@Service
public class ManagerProjectPublishService extends AbstractService<Manager, Project> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerProjectRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Project object = new Project();

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Project object) {
		assert object != null;

		super.bind(object, "code", "title", "abstractProject", "indication", "cost", "link", "manager");
	}

	@Override
	public void validate(final Project object) {
		assert object != null;

		boolean state;

		if (!super.getBuffer().getErrors().hasErrors("publishedUserStories")) {
			state = this.repository.allUserStoriesPublishedByProjecId(object.getId());
			super.state(state, "publishedUserStories", "manager.project.form.error.publishedUserStories");
		}
		if (!super.getBuffer().getErrors().hasErrors("withoutUserStories")) {
			state = this.repository.anyUserStoryByProjectId(object.getId());
			super.state(state, "withoutUserStories", "manager.project.form.error.withoutUserStories");
		}
		if (!super.getBuffer().getErrors().hasErrors("fatalError")) {
			state = object.getIndication();
			super.state(state, "fatalError", "manager.project.form.error.fatalError");
		}

	}

	@Override
	public void perform(final Project object) {
		assert object != null;

		object.setDraftMode(false);

		this.repository.save(object);
	}

	@Override
	public void unbind(final Project object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "title", "abstractProject", "indication", "cost", "link", "draftMode", "manager");

		super.getBuffer().addData(dataset);
	}

}
