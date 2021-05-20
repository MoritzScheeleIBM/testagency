package org.acme.travels;

public class TravelsProcessInstance extends org.kie.kogito.process.impl.AbstractProcessInstance<TravelsModel> {

    public TravelsProcessInstance(org.acme.travels.TravelsProcess process, TravelsModel value, org.kie.api.runtime.process.ProcessRuntime processRuntime) {
        super(process, value, processRuntime);
    }

    public TravelsProcessInstance(org.acme.travels.TravelsProcess process, TravelsModel value, java.lang.String businessKey, org.kie.api.runtime.process.ProcessRuntime processRuntime) {
        super(process, value, businessKey, processRuntime);
    }

    public TravelsProcessInstance(org.acme.travels.TravelsProcess process, TravelsModel value, org.kie.api.runtime.process.ProcessRuntime processRuntime, org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        super(process, value, processRuntime, wpi);
    }

    public TravelsProcessInstance(org.acme.travels.TravelsProcess process, TravelsModel value, org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        super(process, value, wpi);
    }

    protected java.util.Map<String, Object> bind(TravelsModel variables) {
        return variables.toMap();
    }

    protected void unbind(TravelsModel variables, java.util.Map<String, Object> vmap) {
        variables.fromMap(this.id(), vmap);
    }
}
