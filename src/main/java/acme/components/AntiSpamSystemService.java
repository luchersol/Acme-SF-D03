
package acme.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Any;
import acme.client.services.AbstractService;
import acme.entities.antiSpamSystem.Spam;

@Service
public class AntiSpamSystemService extends AbstractService<Any, Spam> {

	@Autowired
	AntiSpamSystemRepository repository;


	public List<String> findAllSpam() {
		//		try {
		//			if (super.getRequest() != null && super.getRequest().getPrincipal() != null && super.getRequest().getPrincipal().hasRole(Administrator.class))
		//				return new ArrayList<>();
		//		} catch (Exception e) {
		//			System.out.println("REQUEST PRINCIPAL ERROR");
		//		}
		return this.repository.findAllSpam().stream().map(Spam::getWord).toList();
	}

}
