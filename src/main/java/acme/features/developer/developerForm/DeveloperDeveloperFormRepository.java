
package acme.features.developer.developerForm;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface DeveloperDeveloperFormRepository extends AbstractRepository {

	@Query("select count(m) from TrainingModule m where m.updateMoment is not null")
	Integer totalNumberOfTrainingModulesWithUpdateMoment();

	@Query("select count(s) from TrainingSession s where s.link is not null and trim(s.link) != ''")
	Integer totalNumberOfTrainingSessionsWithLink();

	@Query("select avg(m.estimatedTotalTime) from TrainingModule m")
	Double averageTimeOfTrainingModules();

	@Query("select stddev(m.estimatedTotalTime) from TrainingModule m")
	Double standardDeviationTimeOfTrainingModules();

	@Query("select min(m.estimatedTotalTime) from TrainingModule m")
	Double minTimeOfTrainingModules();

	@Query("select max(m.estimatedTotalTime) from TrainingModule m")
	Double maxTimeOfTrainingModules();

}
