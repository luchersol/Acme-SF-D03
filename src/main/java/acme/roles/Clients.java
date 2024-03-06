
package acme.roles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractRole;
import acme.entities.contract.Contract;
import acme.entities.contract.ProgressLogs;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Clients extends AbstractRole {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "CLI-[0-9]{4}")
	private String				identification;

	@NotBlank
	@Length(max = 75)
	private String				companyName;

	@NotNull
	private TypeClients			type;

	@NotBlank
	@Email
	private String				email;

	@URL
	private String				link;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	private Contract			contract;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	private ProgressLogs		progressLogs;
}
