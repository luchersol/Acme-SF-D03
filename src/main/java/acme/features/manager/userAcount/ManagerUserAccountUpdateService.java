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

package acme.features.manager.userAcount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.UserAccount;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.roles.Manager;

@Service
public class ManagerUserAccountUpdateService extends AbstractService<Manager, UserAccount> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerUserAccountRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		UserAccount object = null;

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final UserAccount object) {
		assert object != null;

		super.bind(object, "name", "description", "moreInfo");
	}

	@Override
	public void validate(final UserAccount object) {
		assert object != null;
	}

	@Override
	public void perform(final UserAccount object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final UserAccount object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "name", "description", "moreInfo");

		super.getResponse().addData(dataset);
	}

}
