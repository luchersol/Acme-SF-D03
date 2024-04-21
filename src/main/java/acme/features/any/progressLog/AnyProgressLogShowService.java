
package acme.features.any.progressLog;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Any;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.contract.Contract;
import acme.entities.contract.ProgressLog;

@Service
public class AnyProgressLogShowService extends AbstractService<Any, ProgressLog> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnyProgressLogRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int masterId;
		ProgressLog progressLog;

		masterId = super.getRequest().getData("id", int.class);
		progressLog = this.repository.findProgressLogById(masterId);
		status = progressLog != null && progressLog.isDraftMode() == false;
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		ProgressLog progressLog;
		int id;

		id = super.getRequest().getData("id", int.class);
		progressLog = this.repository.findProgressLogById(id);

		super.getBuffer().addData(progressLog);
	}

	@Override
	public void unbind(final ProgressLog progressLogs) {
		assert progressLogs != null;
		Collection<Contract> contracts;
		SelectChoices choices;

		Dataset dataset;
		contracts = this.repository.findContracts();
		choices = SelectChoices.from(contracts, "code", progressLogs.getContract());

		dataset = super.unbind(progressLogs, "recordId", "completeness", "comment", "registrationMoment", "responsiblePerson", "draftMode");
		dataset.put("masterId", progressLogs.getId());
		dataset.put("draftMode", progressLogs.isDraftMode());

		dataset.put("contract", choices.getSelected().getKey());
		dataset.put("contracts", choices);

		super.getResponse().addData(dataset);
	}

}
