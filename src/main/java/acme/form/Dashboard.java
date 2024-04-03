
package acme.form;

import java.util.Collection;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard extends AbstractForm {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer						totalNumberProjectMust;
	Integer						totalNumberProjectShould;
	Integer						totalNumberProjectCould;
	Integer						totalNumberProjectWont;

	Double						averageEstimatedCostUserStories;
	Double						deviationEstimatedCostUserStories;
	Double						minimumEstimatedCostUserStories;
	Double						maximumEstimatedCostUserStories;

	Collection<Object[]>		averageEstimatedCostProjects;
	Collection<Object[]>		deviationEstimatedCostProjects;
	Collection<Object[]>		minimumEstimatedCostProjects;
	Collection<Object[]>		maximumEstimatedCostProjects;

}
