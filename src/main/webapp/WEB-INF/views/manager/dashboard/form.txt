<%--
- form.jsp
-
- Copyright (C) 2012-2024 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-integer code="manager.dashboard.form.label.total-number-project-must" path="totalNumberProjectMust"/>	
	<acme:input-integer code="manager.dashboard.form.label.total-number-project-should" path="totalNumberProjectShould"/>
	<acme:input-integer code="manager.dashboard.form.label.total-number-project-could" path="totalNumberProjectCould"/>
	<acme:input-integer code="manager.dashboard.form.label.total-number-project-wont" path="totalNumberProjectWont"/>
	
	<acme:input-double code="manager.dashboard.form.label.average-estimated-cost-user-stories" path="averageEstimatedCostUserStories"/>
	<acme:input-double code="manager.dashboard.form.label.deviation-estimated-cost-user-stories" path="deviationEstimatedCostUserStories"/>
	<acme:input-double code="manager.dashboard.form.label.minimun-estimated-cost-user-stories" path="minimumEstimatedCostUserStories"/>
	<acme:input-double code="manager.dashboard.form.label.maximun-estimated-cost-user-stories" path="maximumEstimatedCostUserStories"/>
	
	<acme:input-money code="manager.dashboard.form.label.average-estimated-cost-projects" path="averageEstimatedCostProjects"/>
	<acme:input-money code="manager.dashboard.form.label.deviation-estimated-cost-projects" path="deviationEstimatedCostProjects"/>
	<acme:input-money code="manager.dashboard.form.label.minimum-estimated-cost-projects" path="minimumEstimatedCostProjects"/>
	<acme:input-money code="manager.dashboard.form.label.maximum-estimated-cost-projects" path="maximumEstimatedCostProjects"/>	
</acme:form>


