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

@UserTask(taskName = "ConfirmTravel", processName = "travels")
public class Travels_10_TaskInput {

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

    public static Travels_10_TaskInput from(org.kie.kogito.process.WorkItem workItem) {
        Travels_10_TaskInput item = new Travels_10_TaskInput();
        item._id = workItem.getId();
        item._name = workItem.getName();
        Map<String, Object> params = workItem.getParameters();
        item.flight = (org.acme.travels.Flight) params.get("flight");
        item.hotel = (org.acme.travels.Hotel) params.get("hotel");
        return item;
    }

    @UserTaskParam(value = ParamType.INPUT)
    private org.acme.travels.Flight flight;

    public org.acme.travels.Flight getFlight() {
        return flight;
    }

    public void setFlight(org.acme.travels.Flight flight) {
        this.flight = flight;
    }

    @UserTaskParam(value = ParamType.INPUT)
    private org.acme.travels.Hotel hotel;

    public org.acme.travels.Hotel getHotel() {
        return hotel;
    }

    public void setHotel(org.acme.travels.Hotel hotel) {
        this.hotel = hotel;
    }
}
// Task input model for user task 'Confirm travel' in process 'travels'
