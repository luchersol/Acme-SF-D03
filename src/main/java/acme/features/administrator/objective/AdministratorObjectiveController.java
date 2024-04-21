
package acme.features.administrator.objective;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.client.data.accounts.Administrator;
import acme.entities.objective.Objective;

@Controller
public class AdministratorObjectiveController extends AbstractController<Administrator, Objective> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorObjectiveListMineService	listMineService;

	@Autowired
	private AdministratorObjectiveShowService		showService;

	//	@Autowired
	//	private AdministratorObjectiveCreateService		createService;
	//
	//	@Autowired
	//	private AdministratorObjectiveUpdateService		updateService;
	//
	//	@Autowired
	//	private AdministratorObjectiveDeleteService		deleteService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("show", this.showService);
		//		super.addBasicCommand("create", this.createService);
		//		super.addBasicCommand("update", this.updateService);
		//		super.addBasicCommand("delete", this.deleteService);

		super.addCustomCommand("list-mine", "list", this.listMineService);
	}

}
