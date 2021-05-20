/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.acme.travels;

import java.util.Map;
import org.kie.kogito.UserTask;
import org.kie.kogito.UserTaskParam.ParamType;
import org.kie.kogito.UserTaskParam;

@UserTask(taskName = "VisaApplication", processName = "travels")
public class Travels_4_TaskInput {

    private String _id;

    private String _name;

    public void setId(String id) {
        this._id = id;
    }

    public String getId() {
        return this._id;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getName() {
        return this._name;
    }

    public static Travels_4_TaskInput from(org.kie.kogito.process.WorkItem workItem) {
        Travels_4_TaskInput item = new Travels_4_TaskInput();
        item._id = workItem.getId();
        item._name = workItem.getName();
        Map<String, Object> params = workItem.getParameters();
        item.trip = (org.acme.travels.Trip) params.get("trip");
        item.traveller = (org.acme.travels.Traveller) params.get("traveller");
        return item;
    }

    @UserTaskParam(value = ParamType.INPUT)
    private org.acme.travels.Trip trip;

    public org.acme.travels.Trip getTrip() {
        return trip;
    }

    public void setTrip(org.acme.travels.Trip trip) {
        this.trip = trip;
    }

    @UserTaskParam(value = ParamType.INPUT)
    private org.acme.travels.Traveller traveller;

    public org.acme.travels.Traveller getTraveller() {
        return traveller;
    }

    public void setTraveller(org.acme.travels.Traveller traveller) {
        this.traveller = traveller;
    }
}
// Task input model for user task 'Apply for visa' in process 'travels'
