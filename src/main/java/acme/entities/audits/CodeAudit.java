
package acme.entities.audits;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import acme.client.data.AbstractEntity;
import acme.datatypes.AuditType;
import acme.datatypes.Mark;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CodeAudit extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	//	private Project project;

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "[A-Z]{1,3}-[0-9]{3}")
	private String				code;

	@Past
	private Date				execution;

	private AuditType			type;

	@NotBlank
	@Length(max = 101)
	private String				correctiveActions;

	@Transient
	private Mark				mark;

	private String				furtherInformation;

}
