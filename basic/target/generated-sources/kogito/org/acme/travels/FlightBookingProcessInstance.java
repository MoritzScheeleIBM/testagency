package org.acme.travels;

public class FlightBookingProcessInstance extends org.kie.kogito.process.impl.AbstractProcessInstance<FlightBookingModel> {

    public FlightBookingProcessInstance(org.acme.travels.FlightBookingProcess process, FlightBookingModel value, org.kie.api.runtime.process.ProcessRuntime processRuntime) {
        super(process, value, processRuntime);
    }

    public FlightBookingProcessInstance(org.acme.travels.FlightBookingProcess process, FlightBookingModel value, java.lang.String businessKey, org.kie.api.runtime.process.ProcessRuntime processRuntime) {
        super(process, value, businessKey, processRuntime);
    }

    public FlightBookingProcessInstance(org.acme.travels.FlightBookingProcess process, FlightBookingModel value, org.kie.api.runtime.process.ProcessRuntime processRuntime, org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        super(process, value, processRuntime, wpi);
    }

    public FlightBookingProcessInstance(org.acme.travels.FlightBookingProcess process, FlightBookingModel value, org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        super(process, value, wpi);
    }

    protected java.util.Map<String, Object> bind(FlightBookingModel variables) {
        return variables.toMap();
    }

    protected void unbind(FlightBookingModel variables, java.util.Map<String, Object> vmap) {
        variables.fromMap(this.id(), vmap);
    }
}
