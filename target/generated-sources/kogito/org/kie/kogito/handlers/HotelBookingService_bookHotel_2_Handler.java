package org.kie.kogito.handlers;

import org.kie.kogito.process.workitem.WorkItemExecutionException;

@javax.enterprise.context.ApplicationScoped()
public class HotelBookingService_bookHotel_2_Handler implements org.kie.kogito.internal.process.runtime.KogitoWorkItemHandler {

    org.acme.travels.service.HotelBookingService service;

    public HotelBookingService_bookHotel_2_Handler() {
        this(new org.acme.travels.service.HotelBookingService());
    }

    @javax.inject.Inject()
    public HotelBookingService_bookHotel_2_Handler(org.acme.travels.service.HotelBookingService service) {
        this.service = service;
    }

    public void executeWorkItem(org.kie.kogito.internal.process.runtime.KogitoWorkItem workItem, org.kie.kogito.internal.process.runtime.KogitoWorkItemManager workItemManager) {
        java.lang.Object result;
        result = service.bookHotel((org.acme.travels.Trip) workItem.getParameter("Parameter"));
        workItemManager.completeWorkItem(workItem.getStringId(), java.util.Collections.singletonMap("Result", result));
    }

    public void abortWorkItem(org.kie.kogito.internal.process.runtime.KogitoWorkItem workItem, org.kie.kogito.internal.process.runtime.KogitoWorkItemManager workItemManager) {
    }

    public String getName() {
        return "org.acme.travels.service.HotelBookingService_bookHotel_2_Handler";
    }
}
