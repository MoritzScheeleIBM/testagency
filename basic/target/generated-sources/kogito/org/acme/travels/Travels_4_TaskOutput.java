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
import org.kie.kogito.UserTask;
import org.kie.kogito.UserTaskParam.ParamType;
import org.kie.kogito.UserTaskParam;

@UserTask(taskName = "VisaApplication", processName = "travels")
public class Travels_4_TaskOutput implements org.kie.kogito.MapOutput {

    public Map<String, Object> toMap() {
        Map<String, Object> params = new HashMap<>();
        params.put("visaApplication", this.visaApplication);
        return params;
    }

    public static Travels_4_TaskOutput fromMap(Map<String, Object> params) {
        org.acme.travels.Travels_4_TaskOutput result = new Travels_4_TaskOutput();
        result.visaApplication = (String) params.get("visaApplication");
        return result;
    }

    @UserTaskParam(value = ParamType.OUTPUT)
    private String visaApplication;

    public String getVisaApplication() {
        return visaApplication;
    }

    public void setVisaApplication(String visaApplication) {
        this.visaApplication = visaApplication;
    }
}
// Task output model for user task 'Apply for visa' in process 'travels'
