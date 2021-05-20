/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
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
import java.util.HashMap;

public class HotelBookingModelInput implements org.kie.kogito.Model {

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> params = new HashMap<>();
        params.put("trip", this.trip);
        params.put("hotel", this.hotel);
        params.put("traveller", this.traveller);
        return params;
    }

    @Override
    public void fromMap(Map<String, Object> params) {
        fromMap(null, params);
    }

    @Override
    public void update(Map<String, Object> params) {
        fromMap(params);
    }

    public void fromMap(String id, Map<String, Object> params) {
        this.trip = (org.acme.travels.Trip) params.get("trip");
        this.hotel = (org.acme.travels.Hotel) params.get("hotel");
        this.traveller = (org.acme.travels.Traveller) params.get("traveller");
    }

    @org.kie.kogito.codegen.VariableInfo(tags = "")
    @com.fasterxml.jackson.annotation.JsonProperty(value = "trip")
    @javax.validation.Valid()
    private org.acme.travels.Trip trip;

    public org.acme.travels.Trip getTrip() {
        return trip;
    }

    public void setTrip(org.acme.travels.Trip trip) {
        this.trip = trip;
    }

    @org.kie.kogito.codegen.VariableInfo(tags = "")
    @com.fasterxml.jackson.annotation.JsonProperty(value = "hotel")
    @javax.validation.Valid()
    private org.acme.travels.Hotel hotel;

    public org.acme.travels.Hotel getHotel() {
        return hotel;
    }

    public void setHotel(org.acme.travels.Hotel hotel) {
        this.hotel = hotel;
    }

    @org.kie.kogito.codegen.VariableInfo(tags = "")
    @com.fasterxml.jackson.annotation.JsonProperty(value = "traveller")
    @javax.validation.Valid()
    private org.acme.travels.Traveller traveller;

    public org.acme.travels.Traveller getTraveller() {
        return traveller;
    }

    public void setTraveller(org.acme.travels.Traveller traveller) {
        this.traveller = traveller;
    }

    public HotelBookingModel toModel() {
        HotelBookingModel result = new HotelBookingModel();
        result.setTrip(getTrip());
        result.setHotel(getHotel());
        result.setTraveller(getTraveller());
        return result;
    }
}
