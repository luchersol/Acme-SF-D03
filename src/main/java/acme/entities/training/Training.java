
package acme.entities.training;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import acme.entities.project.Project;
import acme.roles.Developer;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Training extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "[A-Z]{1,3}-[0-9]{3}")
	private String				code;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@Past
	private Date				creationMoment;

	@NotBlank
	@Length(max = 100)
	private String				details;

	@NotNull
	private DifficultyLevel		difficultyLevel;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				updateMoment;

	@URL
	private String				link;

	@NotNull
	@PositiveOrZero
	private Double				estimatedTotalTime;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Project				project;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Developer			developer;

	@NotNull
	private Boolean				draftMode;


	public Training() {
	}

	public Training(final String code, final Date creationMoment, final String details, final DifficultyLevel difficultyLevel, final Date updateMoment, final String link, final Double estimatedTotalTime, final Project project, final Developer developer) {
		this.code = code;
		this.creationMoment = creationMoment;
		this.details = details;
		this.difficultyLevel = difficultyLevel;
		this.updateMoment = updateMoment;
		this.link = link;
		this.estimatedTotalTime = estimatedTotalTime;
		this.project = project;
		this.developer = developer;
	}

}
