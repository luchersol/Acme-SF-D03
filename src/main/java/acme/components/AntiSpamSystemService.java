
package acme.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Administrator;
import acme.client.data.accounts.Any;
import acme.client.services.AbstractService;
import acme.entities.antiSpamSystem.Spam;

@Service
public class AntiSpamSystemService extends AbstractService<Any, Spam> {

	@Autowired
	AntiSpamSystemRepository repository;


	public List<String> findAllSpam() {
		boolean isAdmin;

		isAdmin = super.getRequest().getPrincipal().hasRole(Administrator.class);

		return isAdmin ? List.of() : this.repository.findAllSpam().stream().map(Spam::getWord).toList();
	}

}
