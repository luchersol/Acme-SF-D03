
package acme.features.administrator.objective;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Administrator;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.objective.Objective;

@Service
public class AdministratorObjectiveListMineService extends AbstractService<Administrator, Objective> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorObjectiveRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Collection<Objective> objectives;
		int administratorId;
		administratorId = this.getRequest().getPrincipal().getAccountId();
		objectives = this.repository.findObjectivesByAdministratorId(administratorId);
		super.getBuffer().addData(objectives);
	}

	@Override
	public void unbind(final Objective objective) {
		assert objective != null;

		Dataset dataset;
		dataset = super.unbind(objective, "instantiationMoment", "title", "description", "priority", "status", "startDate", "endDate", "link");
		super.getResponse().addData(dataset);
	}

}
