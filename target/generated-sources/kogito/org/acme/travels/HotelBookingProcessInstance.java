package org.acme.travels;

public class HotelBookingProcessInstance extends org.kie.kogito.process.impl.AbstractProcessInstance<HotelBookingModel> {

    public HotelBookingProcessInstance(org.acme.travels.HotelBookingProcess process, HotelBookingModel value, org.kie.api.runtime.process.ProcessRuntime processRuntime) {
        super(process, value, processRuntime);
    }

    public HotelBookingProcessInstance(org.acme.travels.HotelBookingProcess process, HotelBookingModel value, java.lang.String businessKey, org.kie.api.runtime.process.ProcessRuntime processRuntime) {
        super(process, value, businessKey, processRuntime);
    }

    public HotelBookingProcessInstance(org.acme.travels.HotelBookingProcess process, HotelBookingModel value, org.kie.api.runtime.process.ProcessRuntime processRuntime, org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        super(process, value, processRuntime, wpi);
    }

    public HotelBookingProcessInstance(org.acme.travels.HotelBookingProcess process, HotelBookingModel value, org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        super(process, value, wpi);
    }

    protected java.util.Map<String, Object> bind(HotelBookingModel variables) {
        return variables.toMap();
    }

    protected void unbind(HotelBookingModel variables, java.util.Map<String, Object> vmap) {
        variables.fromMap(this.id(), vmap);
    }
}
