
package acme.features.developer.developerForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.form.DeveloperForm;
import acme.roles.Developer;

@Service
public class DeveloperDeveloperFormShowService extends AbstractService<Developer, DeveloperForm> {

	@Autowired
	private DeveloperDeveloperFormRepository repository;


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		DeveloperForm developerForm = new DeveloperForm();
		developerForm.setTotalTrainingModulesWithUpdateMoment(this.repository.totalNumberOfTrainingModulesWithUpdateMoment());
		developerForm.setTotalTrainingSessionsWithLink(this.repository.totalNumberOfTrainingSessionsWithLink());
		developerForm.setAverageTimeOfTraining(this.repository.averageTimeOfTrainingModules());
		developerForm.setDeviationTimeOfTraining(this.repository.standardDeviationTimeOfTrainingModules());
		developerForm.setMinimumTimeOfTraining(this.repository.minTimeOfTrainingModules());
		developerForm.setMaximumTimeOfTraining(this.repository.maxTimeOfTrainingModules());

		super.getBuffer().addData(developerForm);
	}

	@Override
	public void unbind(final DeveloperForm object) {
		Dataset dataset;

		dataset = super.unbind(object, //
			"totalTrainingModulesWithUpdateMoment", "totalTrainingSessionsWithLink", // 
			"averageTimeOfTraining", "deviationTimeOfTraining", //
			"minimumTimeOfTraining", "maximumTimeOfTraining");

		super.getResponse().addData(dataset);
	}

}
