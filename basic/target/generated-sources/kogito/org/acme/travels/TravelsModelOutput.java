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
import java.util.HashMap;

@org.kie.kogito.codegen.Generated(value = "kogito-codegen", reference = "travels", name = "Travels", hidden = true)
public class TravelsModelOutput implements org.kie.kogito.Model {

    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> params = new HashMap<>();
        params.put("flight", this.flight);
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
        fromMap(getId(), params);
    }

    public void fromMap(String id, Map<String, Object> params) {
        this.id = id;
        this.flight = (org.acme.travels.Flight) params.get("flight");
        this.trip = (org.acme.travels.Trip) params.get("trip");
        this.hotel = (org.acme.travels.Hotel) params.get("hotel");
        this.traveller = (org.acme.travels.Traveller) params.get("traveller");
    }

    @org.kie.kogito.codegen.VariableInfo(tags = "")
    @com.fasterxml.jackson.annotation.JsonProperty(value = "flight")
    @javax.validation.Valid()
    private org.acme.travels.Flight flight;

    public org.acme.travels.Flight getFlight() {
        return flight;
    }

    public void setFlight(org.acme.travels.Flight flight) {
        this.flight = flight;
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
}
