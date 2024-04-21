
package acme.features.administrator.objective;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Administrator;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.objective.Objective;

@Service
public class AdministratorObjectiveShowService extends AbstractService<Administrator, Objective> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorObjectiveRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int objectiveId;
		Objective objective;
		Administrator administrator;

		objectiveId = super.getRequest().getData("id", int.class);
		objective = this.repository.findOneObjectiveById(objectiveId);
		administrator = objective == null ? null : objective.getAdministrator();
		status = administrator.getId() == super.getRequest().getPrincipal().getActiveRoleId() && objective != null && super.getRequest().getPrincipal().hasRole(administrator);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Objective objective;
		int id;

		id = super.getRequest().getData("id", int.class);
		objective = this.repository.findOneObjectiveById(id);

		super.getBuffer().addData(objective);
	}

	@Override
	public void unbind(final Objective objective) {
		assert objective != null;

		Dataset dataset;

		dataset = super.unbind(objective, "instantiationMoment", "title", "description", "priority", "status", "startDate", "endDate", "link");

		super.getResponse().addData(dataset);
	}
}
