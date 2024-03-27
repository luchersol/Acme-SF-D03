
package acme.features.sponsor.invoice;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.data.accounts.UserAccount;
import acme.client.repositories.AbstractRepository;
import acme.entities.sponsorship.Invoice;
import acme.entities.sponsorship.Sponsorship;
import acme.roles.Sponsor;

@Repository
public interface SponsorInvoiceRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findOneUserAccountById(int id);

	@Query("select i from Invoice i where i.id = :id")
	Invoice findOneInvoiceById(int id);

	@Query("select s from Sponsor s where s.id = :id")
	Sponsor findOneSponsorById(int id);

	@Query("select ss from Sponsorship ss where ss.sponsor.id = :id")
	Collection<Sponsorship> findManySponsorshipsBySponsor(int id);

	@Query("select i from Invoice i where i.sponsor.id = :id")
	Collection<Invoice> findManyInvoicesBySponsor(int id);

}
