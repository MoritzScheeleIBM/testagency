package org.acme.travels;

import org.jbpm.process.core.datatype.impl.type.ObjectDataType;
import org.jbpm.ruleflow.core.RuleFlowProcessFactory;
import org.drools.core.util.KieFunctions;

@javax.enterprise.context.ApplicationScoped()
@javax.inject.Named("travels")
@io.quarkus.runtime.Startup()
public class TravelsProcess extends org.kie.kogito.process.impl.AbstractProcess<org.acme.travels.TravelsModel> {

    @javax.inject.Inject()
    org.kie.kogito.process.Process<FlightBookingModel> processflightBooking;

    @javax.inject.Inject()
    org.kie.kogito.process.Process<HotelBookingModel> processhotelBooking;

    @javax.inject.Inject()
    public TravelsProcess(org.kie.kogito.app.Application app) {
        super(app, java.util.Arrays.asList());
        activate();
    }

    public TravelsProcess() {
    }

    @Override()
    public org.acme.travels.TravelsProcessInstance createInstance(org.acme.travels.TravelsModel value) {
        return new org.acme.travels.TravelsProcessInstance(this, value, this.createProcessRuntime());
    }

    public org.acme.travels.TravelsProcessInstance createInstance(java.lang.String businessKey, org.acme.travels.TravelsModel value) {
        return new org.acme.travels.TravelsProcessInstance(this, value, businessKey, this.createProcessRuntime());
    }

    @Override()
    public org.acme.travels.TravelsModel createModel() {
        return new org.acme.travels.TravelsModel();
    }

    public org.acme.travels.TravelsProcessInstance createInstance(org.kie.kogito.Model value) {
        return this.createInstance((org.acme.travels.TravelsModel) value);
    }

    public org.acme.travels.TravelsProcessInstance createInstance(java.lang.String businessKey, org.kie.kogito.Model value) {
        return this.createInstance(businessKey, (org.acme.travels.TravelsModel) value);
    }

    public org.acme.travels.TravelsProcessInstance createInstance(org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        return new org.acme.travels.TravelsProcessInstance(this, this.createModel(), this.createProcessRuntime(), wpi);
    }

    public org.acme.travels.TravelsProcessInstance createReadOnlyInstance(org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        return new org.acme.travels.TravelsProcessInstance(this, this.createModel(), wpi);
    }

    public org.kie.api.definition.process.Process process() {
        RuleFlowProcessFactory factory = RuleFlowProcessFactory.createProcess("travels");
        factory.variable("traveller", new ObjectDataType("org.acme.travels.Traveller"), "customTags", null);
        factory.variable("trip", new ObjectDataType("org.acme.travels.Trip"), "customTags", null);
        factory.variable("hotel", new ObjectDataType("org.acme.travels.Hotel"), "customTags", null);
        factory.variable("flight", new ObjectDataType("org.acme.travels.Flight"), "customTags", null);
        factory.name("travels");
        factory.packageName("org.acme.travels");
        factory.dynamic(false);
        factory.version("1.0");
        factory.visibility("Public");
        factory.metaData("TargetNamespace", "http://www.omg.org/bpmn20");
        factory.imports("org.acme.travels.Traveller");
        factory.imports("org.acme.travels.Trip");
        factory.imports("org.acme.travels.Flight");
        factory.imports("org.acme.travels.Hotel");
        org.jbpm.ruleflow.core.factory.StartNodeFactory startNode1 = factory.startNode(1);
        startNode1.name("StartProcess");
        startNode1.interrupting(false);
        startNode1.metaData("UniqueId", "StartEvent_1");
        startNode1.metaData("elementname", "StartProcess");
        startNode1.metaData("x", 40);
        startNode1.metaData("width", 56);
        startNode1.metaData("y", 267);
        startNode1.metaData("height", 56);
        startNode1.done();
        org.jbpm.ruleflow.core.factory.RuleSetNodeFactory ruleSetNode2 = factory.ruleSetNode(2);
        ruleSetNode2.name("Visa check");
        ruleSetNode2.inMapping("trip", "trip");
        ruleSetNode2.inMapping("traveller", "traveller");
        ruleSetNode2.outMapping("trip", "trip");
        ruleSetNode2.ruleFlowGroup("visas", () -> {
            return org.drools.project.model.ProjectRuntime.INSTANCE.newKieSession("defaultStatelessKieSession", app.config().get(org.kie.kogito.rules.RuleConfig.class));
        });
        ruleSetNode2.metaData("UniqueId", "BusinessRuleTask_1");
        ruleSetNode2.metaData("elementname", "Visa check");
        ruleSetNode2.metaData("x", 140);
        ruleSetNode2.metaData("width", 110);
        ruleSetNode2.metaData("y", 270);
        ruleSetNode2.metaData("height", 50);
        ruleSetNode2.done();
        org.jbpm.ruleflow.core.factory.SplitFactory splitNode3 = factory.splitNode(3);
        splitNode3.name("is visa required");
        splitNode3.type(2);
        splitNode3.metaData("UniqueId", "ExclusiveGateway_1");
        splitNode3.metaData("elementname", "is visa required");
        splitNode3.metaData("x", 305);
        splitNode3.metaData("width", 56);
        splitNode3.metaData("y", 267);
        splitNode3.metaData("height", 56);
        splitNode3.constraint(4, "SequenceFlow_3", "DROOLS_DEFAULT", "java", kcontext -> {
            org.acme.travels.Traveller traveller = (org.acme.travels.Traveller) kcontext.getVariable("traveller");
            org.acme.travels.Trip trip = (org.acme.travels.Trip) kcontext.getVariable("trip");
            org.acme.travels.Hotel hotel = (org.acme.travels.Hotel) kcontext.getVariable("hotel");
            org.acme.travels.Flight flight = (org.acme.travels.Flight) kcontext.getVariable("flight");
            return trip.isVisaRequired();
        }, 0);
        splitNode3.constraint(5, "SequenceFlow_4", "DROOLS_DEFAULT", "java", kcontext -> {
            org.acme.travels.Traveller traveller = (org.acme.travels.Traveller) kcontext.getVariable("traveller");
            org.acme.travels.Trip trip = (org.acme.travels.Trip) kcontext.getVariable("trip");
            org.acme.travels.Hotel hotel = (org.acme.travels.Hotel) kcontext.getVariable("hotel");
            org.acme.travels.Flight flight = (org.acme.travels.Flight) kcontext.getVariable("flight");
            return !trip.isVisaRequired();
        }, 0);
        splitNode3.done();
        org.jbpm.ruleflow.core.factory.HumanTaskNodeFactory humanTaskNode4 = factory.humanTaskNode(4);
        humanTaskNode4.name("Apply for visa");
        humanTaskNode4.workParameter("TaskName", "VisaApplication");
        humanTaskNode4.workParameter("Skippable", "true");
        humanTaskNode4.workParameter("Priority", "1");
        humanTaskNode4.workParameter("NodeName", "Apply for visa");
        humanTaskNode4.inMapping("trip", "trip");
        humanTaskNode4.inMapping("traveller", "traveller");
        humanTaskNode4.outMapping("visaApplication", "UserTask_1_visaApplicationOutputX#{traveller.visaApplication}");
        humanTaskNode4.done();
        humanTaskNode4.metaData("UniqueId", "UserTask_1");
        humanTaskNode4.metaData("elementname", "Apply for visa");
        humanTaskNode4.metaData("x", 410);
        humanTaskNode4.metaData("width", 110);
        humanTaskNode4.metaData("y", 200);
        humanTaskNode4.metaData("height", 50);
        org.jbpm.ruleflow.core.factory.JoinFactory joinNode5 = factory.joinNode(5);
        joinNode5.name("Join");
        joinNode5.type(2);
        joinNode5.metaData("UniqueId", "ExclusiveGateway_2");
        joinNode5.metaData("x", 565);
        joinNode5.metaData("width", 56);
        joinNode5.metaData("y", 267);
        joinNode5.metaData("height", 56);
        joinNode5.done();
        org.jbpm.ruleflow.core.factory.SplitFactory splitNode6 = factory.splitNode(6);
        splitNode6.name("Book");
        splitNode6.type(1);
        splitNode6.metaData("UniqueId", "ParallelGateway_1");
        splitNode6.metaData("elementname", "Book");
        splitNode6.metaData("x", 690);
        splitNode6.metaData("width", 56);
        splitNode6.metaData("y", 267);
        splitNode6.metaData("height", 56);
        splitNode6.done();
        org.jbpm.ruleflow.core.factory.SubProcessNodeFactory subProcessNode7 = factory.subProcessNode(7);
        subProcessNode7.name("Book Hotel");
        subProcessNode7.processId("hotelBooking");
        subProcessNode7.processName("");
        subProcessNode7.waitForCompletion(true);
        subProcessNode7.independent(true);
        subProcessNode7.subProcessNode(new org.jbpm.workflow.core.node.SubProcessFactory<HotelBookingModel>() {

            public HotelBookingModel bind(org.kie.api.runtime.process.ProcessContext kcontext) {
                org.acme.travels.HotelBookingModel model = new org.acme.travels.HotelBookingModel();
                org.acme.travels.Trip trip = (org.acme.travels.Trip) kcontext.getVariable("trip");
                model.setTrip((org.acme.travels.Trip) (trip));
                org.acme.travels.Traveller traveller = (org.acme.travels.Traveller) kcontext.getVariable("traveller");
                model.setTraveller((org.acme.travels.Traveller) (traveller));
                return model;
            }

            public org.kie.kogito.process.ProcessInstance<HotelBookingModel> createInstance(HotelBookingModel model) {
                return processhotelBooking.createInstance(model);
            }

            public void unbind(org.kie.api.runtime.process.ProcessContext kcontext, HotelBookingModel model) {
                org.acme.travels.Hotel hotel = (org.acme.travels.Hotel) model.getHotel();
                kcontext.setVariable("hotel", hotel);
            }
        });
        subProcessNode7.metaData("UniqueId", "CallActivity_1");
        subProcessNode7.metaData("elementname", "Book Hotel");
        subProcessNode7.metaData("x", 783);
        subProcessNode7.metaData("width", 110);
        subProcessNode7.metaData("y", 180);
        subProcessNode7.metaData("height", 50);
        subProcessNode7.done();
        org.jbpm.ruleflow.core.factory.SubProcessNodeFactory subProcessNode8 = factory.subProcessNode(8);
        subProcessNode8.name("Book Flight");
        subProcessNode8.processId("flightBooking");
        subProcessNode8.processName("");
        subProcessNode8.waitForCompletion(true);
        subProcessNode8.independent(true);
        subProcessNode8.subProcessNode(new org.jbpm.workflow.core.node.SubProcessFactory<FlightBookingModel>() {

            public FlightBookingModel bind(org.kie.api.runtime.process.ProcessContext kcontext) {
                org.acme.travels.FlightBookingModel model = new org.acme.travels.FlightBookingModel();
                org.acme.travels.Trip trip = (org.acme.travels.Trip) kcontext.getVariable("trip");
                model.setTrip((org.acme.travels.Trip) (trip));
                org.acme.travels.Traveller traveller = (org.acme.travels.Traveller) kcontext.getVariable("traveller");
                model.setTraveller((org.acme.travels.Traveller) (traveller));
                return model;
            }

            public org.kie.kogito.process.ProcessInstance<FlightBookingModel> createInstance(FlightBookingModel model) {
                return processflightBooking.createInstance(model);
            }

            public void unbind(org.kie.api.runtime.process.ProcessContext kcontext, FlightBookingModel model) {
                org.acme.travels.Flight flight = (org.acme.travels.Flight) model.getFlight();
                kcontext.setVariable("flight", flight);
            }
        });
        subProcessNode8.metaData("UniqueId", "CallActivity_2");
        subProcessNode8.metaData("elementname", "Book Flight");
        subProcessNode8.metaData("x", 783);
        subProcessNode8.metaData("width", 110);
        subProcessNode8.metaData("y", 345);
        subProcessNode8.metaData("height", 50);
        subProcessNode8.done();
        org.jbpm.ruleflow.core.factory.JoinFactory joinNode9 = factory.joinNode(9);
        joinNode9.name("Join");
        joinNode9.type(1);
        joinNode9.metaData("UniqueId", "ParallelGateway_2");
        joinNode9.metaData("x", 952);
        joinNode9.metaData("width", 56);
        joinNode9.metaData("y", 267);
        joinNode9.metaData("height", 56);
        joinNode9.done();
        org.jbpm.ruleflow.core.factory.HumanTaskNodeFactory humanTaskNode10 = factory.humanTaskNode(10);
        humanTaskNode10.name("Confirm travel");
        humanTaskNode10.workParameter("TaskName", "ConfirmTravel");
        humanTaskNode10.workParameter("Skippable", "true");
        humanTaskNode10.workParameter("Priority", "1");
        humanTaskNode10.workParameter("NodeName", "Confirm travel");
        humanTaskNode10.inMapping("flight", "flight");
        humanTaskNode10.inMapping("hotel", "hotel");
        humanTaskNode10.done();
        humanTaskNode10.metaData("UniqueId", "UserTask_2");
        humanTaskNode10.metaData("elementname", "Confirm travel");
        humanTaskNode10.metaData("x", 1066);
        humanTaskNode10.metaData("width", 110);
        humanTaskNode10.metaData("y", 270);
        humanTaskNode10.metaData("height", 50);
        org.jbpm.ruleflow.core.factory.EndNodeFactory endNode11 = factory.endNode(11);
        endNode11.name("End Event 1");
        endNode11.terminate(false);
        endNode11.metaData("UniqueId", "EndEvent_1");
        endNode11.metaData("elementname", "End Event 1");
        endNode11.metaData("x", 1250);
        endNode11.metaData("width", 56);
        endNode11.metaData("y", 267);
        endNode11.metaData("height", 56);
        endNode11.done();
        factory.connection(1, 2, "SequenceFlow_1");
        factory.connection(2, 3, "SequenceFlow_2");
        factory.connection(3, 4, "SequenceFlow_3");
        factory.connection(4, 5, "SequenceFlow_5");
        factory.connection(3, 5, "SequenceFlow_4");
        factory.connection(5, 6, "SequenceFlow_6");
        factory.connection(6, 7, "SequenceFlow_7");
        factory.connection(6, 8, "SequenceFlow_8");
        factory.connection(7, 9, "SequenceFlow_10");
        factory.connection(8, 9, "SequenceFlow_9");
        factory.connection(9, 10, "SequenceFlow_11");
        factory.connection(10, 11, "SequenceFlow_12");
        factory.validate();
        return factory.getProcess();
    }

    protected void registerListeners() {
        services.getSignalManager().addEventListener("flightBooking", completionEventListener);
        services.getSignalManager().addEventListener("hotelBooking", completionEventListener);
    }
}
