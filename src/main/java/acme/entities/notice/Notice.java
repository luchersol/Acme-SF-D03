
package acme.entities.notice;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Notice {

	private static final long	serialVersionUID	= 1L;

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date				instantiationMoment;

	@NotBlank
	@Length(max = 75)
	private String				title;

	@NotBlank
	@Pattern(regexp = "^[A-Za-z]+, [A-Za-z]+$")
	private String				name;

	@NotBlank
	@Length(max = 100)
	private String				username;

	@NotBlank
	@Length(max = 100)
	private String				message;

	@Email
	private String				contactEmail;

	@URL
	private String				link;

	// Derived attributes -------------------------------------------------------------


	@Length(max = 75)
	public String author() {
		return this.username + " - " + this.name;
	}

}
