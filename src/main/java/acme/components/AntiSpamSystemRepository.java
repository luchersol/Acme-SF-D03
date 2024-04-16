
package acme.components;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.client.repositories.AbstractRepository;
import acme.entities.antiSpamSystem.Spam;

public interface AntiSpamSystemRepository extends AbstractRepository {

	@Query("select s from Spam s")
	Collection<Spam> findAllSpam();

}
