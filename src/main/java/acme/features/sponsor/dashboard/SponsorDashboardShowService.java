
package acme.features.sponsor.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.form.SponsorForm;
import acme.roles.Sponsor;

@Service
public class SponsorDashboardShowService extends AbstractService<Sponsor, SponsorForm> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private SponsorDashboardRepository repository;

	// AbstractService interface ----------------------------------------------
}
