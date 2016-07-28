/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
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

package org.uberfire.ext.preferences.processors;

import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.mvp.UberView;
import org.uberfire.ext.preferences.client.mvp.WorkbenchPreferences;
import org.uberfire.lifecycle.OnStartup;
import org.uberfire.mvp.PlaceRequest;

@WorkbenchPreferences(identifier = "test13")
public class WorkbenchPreferencesTest13 {

    @WorkbenchPartView
    public UberView<WorkbenchPreferencesTest13> getView() {
        return null;
    }

    @WorkbenchPartTitle
    public String getTitle() {
        return "title";
    }

    @OnStartup
    public void onStartup() {
    }

    @OnStartup
    public void onStartup(final PlaceRequest place) {
    }

}
