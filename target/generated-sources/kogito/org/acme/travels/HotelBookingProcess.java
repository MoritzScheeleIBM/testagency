package org.acme.travels;

import org.jbpm.process.core.datatype.impl.type.ObjectDataType;
import org.jbpm.ruleflow.core.RuleFlowProcessFactory;
import org.drools.core.util.KieFunctions;

@javax.enterprise.context.ApplicationScoped()
@javax.inject.Named("hotelBooking")
@io.quarkus.runtime.Startup()
public class HotelBookingProcess extends org.kie.kogito.process.impl.AbstractProcess<org.acme.travels.HotelBookingModel> {

    public HotelBookingProcess(org.kie.kogito.app.Application app) {
        this(app, new org.kie.kogito.handlers.HotelBookingService_bookHotel_2_Handler());
    }

    @javax.inject.Inject()
    public HotelBookingProcess(org.kie.kogito.app.Application app, org.kie.kogito.handlers.HotelBookingService_bookHotel_2_Handler hotelBookingService_bookHotel_2_Handler) {
        super(app, java.util.Arrays.asList(hotelBookingService_bookHotel_2_Handler));
        activate();
    }

    public HotelBookingProcess() {
    }

    @Override()
    public org.acme.travels.HotelBookingProcessInstance createInstance(org.acme.travels.HotelBookingModel value) {
        return new org.acme.travels.HotelBookingProcessInstance(this, value, this.createProcessRuntime());
    }

    public org.acme.travels.HotelBookingProcessInstance createInstance(java.lang.String businessKey, org.acme.travels.HotelBookingModel value) {
        return new org.acme.travels.HotelBookingProcessInstance(this, value, businessKey, this.createProcessRuntime());
    }

    @Override()
    public org.acme.travels.HotelBookingModel createModel() {
        return new org.acme.travels.HotelBookingModel();
    }

    public org.acme.travels.HotelBookingProcessInstance createInstance(org.kie.kogito.Model value) {
        return this.createInstance((org.acme.travels.HotelBookingModel) value);
    }

    public org.acme.travels.HotelBookingProcessInstance createInstance(java.lang.String businessKey, org.kie.kogito.Model value) {
        return this.createInstance(businessKey, (org.acme.travels.HotelBookingModel) value);
    }

    public org.acme.travels.HotelBookingProcessInstance createInstance(org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        return new org.acme.travels.HotelBookingProcessInstance(this, this.createModel(), this.createProcessRuntime(), wpi);
    }

    public org.acme.travels.HotelBookingProcessInstance createReadOnlyInstance(org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        return new org.acme.travels.HotelBookingProcessInstance(this, this.createModel(), wpi);
    }

    public org.kie.api.definition.process.Process process() {
        RuleFlowProcessFactory factory = RuleFlowProcessFactory.createProcess("hotelBooking");
        factory.variable("traveller", new ObjectDataType("org.acme.travels.Traveller"), "customTags", null);
        factory.variable("trip", new ObjectDataType("org.acme.travels.Trip"), "customTags", null);
        factory.variable("hotel", new ObjectDataType("org.acme.travels.Hotel"), "customTags", null);
        factory.name("HotelBooking");
        factory.packageName("org.acme.travels");
        factory.dynamic(false);
        factory.version("1.0");
        factory.visibility("Private");
        factory.metaData("TargetNamespace", "http://www.jboss.org/drools");
        factory.imports("org.acme.travels.Traveller");
        factory.imports("org.acme.travels.Trip");
        factory.imports("org.acme.travels.Flight");
        factory.imports("org.acme.travels.Hotel");
        factory.imports("org.acme.travels.service.HotelBookingService");
        org.jbpm.ruleflow.core.factory.StartNodeFactory startNode1 = factory.startNode(1);
        startNode1.name("StartProcess");
        startNode1.interrupting(true);
        startNode1.metaData("UniqueId", "StartEvent_1");
        startNode1.metaData("elementname", "StartProcess");
        startNode1.metaData("x", 60);
        startNode1.metaData("width", 36);
        startNode1.metaData("y", 72);
        startNode1.metaData("height", 36);
        startNode1.done();
        org.jbpm.ruleflow.core.factory.WorkItemNodeFactory workItemNode2 = factory.workItemNode(2);
        workItemNode2.name("Book hotel");
        workItemNode2.workName("org.acme.travels.service.HotelBookingService_bookHotel_2_Handler");
        workItemNode2.workParameter("Interface", "org.acme.travels.service.HotelBookingService");
        workItemNode2.workParameter("Operation", "bookHotel");
        workItemNode2.workParameter("ParameterType", "org.acme.travels.Trip");
        workItemNode2.workParameter("interfaceImplementationRef", "org.acme.travels.service.HotelBookingService");
        workItemNode2.workParameter("implementation", "java");
        workItemNode2.inMapping("Parameter", "trip");
        workItemNode2.outMapping("Result", "hotel");
        workItemNode2.done();
        workItemNode2.metaData("UniqueId", "ServiceTask_1");
        workItemNode2.metaData("Implementation", "java");
        workItemNode2.metaData("elementname", "Book hotel");
        workItemNode2.metaData("Type", "Service Task");
        workItemNode2.metaData("OperationRef", "Operation_1");
        workItemNode2.metaData("x", 190);
        workItemNode2.metaData("width", 110);
        workItemNode2.metaData("y", 65);
        workItemNode2.metaData("height", 50);
        org.jbpm.ruleflow.core.factory.EndNodeFactory endNode3 = factory.endNode(3);
        endNode3.name("End Event 1");
        endNode3.terminate(false);
        endNode3.metaData("UniqueId", "EndEvent_1");
        endNode3.metaData("elementname", "End Event 1");
        endNode3.metaData("x", 368);
        endNode3.metaData("width", 36);
        endNode3.metaData("y", 72);
        endNode3.metaData("height", 36);
        endNode3.done();
        factory.connection(1, 2, "SequenceFlow_2");
        factory.connection(2, 3, "SequenceFlow_1");
        factory.validate();
        return factory.getProcess();
    }
}
