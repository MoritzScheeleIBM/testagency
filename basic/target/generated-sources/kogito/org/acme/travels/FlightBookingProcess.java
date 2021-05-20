package org.acme.travels;

import org.jbpm.process.core.datatype.impl.type.ObjectDataType;
import org.jbpm.ruleflow.core.RuleFlowProcessFactory;
import org.drools.core.util.KieFunctions;

@javax.enterprise.context.ApplicationScoped()
@javax.inject.Named("flightBooking")
@io.quarkus.runtime.Startup()
public class FlightBookingProcess extends org.kie.kogito.process.impl.AbstractProcess<org.acme.travels.FlightBookingModel> {

    public FlightBookingProcess(org.kie.kogito.app.Application app) {
        this(app, new org.kie.kogito.handlers.FlightBookingService_bookFlight_2_Handler());
    }

    @javax.inject.Inject()
    public FlightBookingProcess(org.kie.kogito.app.Application app, org.kie.kogito.handlers.FlightBookingService_bookFlight_2_Handler flightBookingService_bookFlight_2_Handler) {
        super(app, java.util.Arrays.asList(flightBookingService_bookFlight_2_Handler));
        activate();
    }

    public FlightBookingProcess() {
    }

    @Override()
    public org.acme.travels.FlightBookingProcessInstance createInstance(org.acme.travels.FlightBookingModel value) {
        return new org.acme.travels.FlightBookingProcessInstance(this, value, this.createProcessRuntime());
    }

    public org.acme.travels.FlightBookingProcessInstance createInstance(java.lang.String businessKey, org.acme.travels.FlightBookingModel value) {
        return new org.acme.travels.FlightBookingProcessInstance(this, value, businessKey, this.createProcessRuntime());
    }

    @Override()
    public org.acme.travels.FlightBookingModel createModel() {
        return new org.acme.travels.FlightBookingModel();
    }

    public org.acme.travels.FlightBookingProcessInstance createInstance(org.kie.kogito.Model value) {
        return this.createInstance((org.acme.travels.FlightBookingModel) value);
    }

    public org.acme.travels.FlightBookingProcessInstance createInstance(java.lang.String businessKey, org.kie.kogito.Model value) {
        return this.createInstance(businessKey, (org.acme.travels.FlightBookingModel) value);
    }

    public org.acme.travels.FlightBookingProcessInstance createInstance(org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        return new org.acme.travels.FlightBookingProcessInstance(this, this.createModel(), this.createProcessRuntime(), wpi);
    }

    public org.acme.travels.FlightBookingProcessInstance createReadOnlyInstance(org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        return new org.acme.travels.FlightBookingProcessInstance(this, this.createModel(), wpi);
    }

    public org.kie.api.definition.process.Process process() {
        RuleFlowProcessFactory factory = RuleFlowProcessFactory.createProcess("flightBooking");
        factory.variable("trip", new ObjectDataType("org.acme.travels.Trip"), "customTags", null);
        factory.variable("traveller", new ObjectDataType("org.acme.travels.Traveller"), "customTags", null);
        factory.variable("flight", new ObjectDataType("org.acme.travels.Flight"), "customTags", null);
        factory.name("FlightBooking");
        factory.packageName("org.acme.travels");
        factory.dynamic(false);
        factory.version("1.0");
        factory.visibility("Private");
        factory.metaData("TargetNamespace", "http://www.jboss.org/drools");
        factory.imports("org.acme.travels.Traveller");
        factory.imports("org.acme.travels.Trip");
        factory.imports("org.acme.travels.Flight");
        factory.imports("org.acme.travels.service.FlightBookingService");
        org.jbpm.ruleflow.core.factory.StartNodeFactory startNode1 = factory.startNode(1);
        startNode1.name("StartProcess");
        startNode1.interrupting(true);
        startNode1.metaData("UniqueId", "StartEvent_1");
        startNode1.metaData("elementname", "StartProcess");
        startNode1.metaData("x", 70);
        startNode1.metaData("width", 36);
        startNode1.metaData("y", 91);
        startNode1.metaData("height", 36);
        startNode1.done();
        org.jbpm.ruleflow.core.factory.WorkItemNodeFactory workItemNode2 = factory.workItemNode(2);
        workItemNode2.name("Book flight");
        workItemNode2.workName("org.acme.travels.service.FlightBookingService_bookFlight_2_Handler");
        workItemNode2.workParameter("Interface", "org.acme.travels.service.FlightBookingService");
        workItemNode2.workParameter("Operation", "bookFlight");
        workItemNode2.workParameter("ParameterType", "org.acme.travels.Trip");
        workItemNode2.workParameter("interfaceImplementationRef", "org.acme.travels.service.FlightBookingService");
        workItemNode2.workParameter("implementation", "java");
        workItemNode2.inMapping("Parameter", "trip");
        workItemNode2.outMapping("Result", "flight");
        workItemNode2.done();
        workItemNode2.metaData("UniqueId", "ServiceTask_1");
        workItemNode2.metaData("Implementation", "java");
        workItemNode2.metaData("elementname", "Book flight");
        workItemNode2.metaData("Type", "Service Task");
        workItemNode2.metaData("OperationRef", "Operation_1");
        workItemNode2.metaData("x", 165);
        workItemNode2.metaData("width", 110);
        workItemNode2.metaData("y", 84);
        workItemNode2.metaData("height", 50);
        org.jbpm.ruleflow.core.factory.EndNodeFactory endNode3 = factory.endNode(3);
        endNode3.name("End Event 1");
        endNode3.terminate(false);
        endNode3.metaData("UniqueId", "EndEvent_1");
        endNode3.metaData("elementname", "End Event 1");
        endNode3.metaData("x", 343);
        endNode3.metaData("width", 36);
        endNode3.metaData("y", 91);
        endNode3.metaData("height", 36);
        endNode3.done();
        factory.connection(1, 2, "SequenceFlow_2");
        factory.connection(2, 3, "SequenceFlow_1");
        factory.validate();
        return factory.getProcess();
    }
}
