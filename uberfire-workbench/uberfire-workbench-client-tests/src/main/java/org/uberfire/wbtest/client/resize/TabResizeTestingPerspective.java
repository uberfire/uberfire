/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.uberfire.wbtest.client.resize;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.client.workbench.panels.impl.MultiTabWorkbenchPanelPresenter;
import org.uberfire.wbtest.client.api.AbstractTestPerspectiveActivity;
import org.uberfire.wbtest.client.panels.docking.NestingScreen;
import org.uberfire.workbench.model.PerspectiveDefinition;
import org.uberfire.workbench.model.impl.PerspectiveDefinitionImpl;

/**
 * A perspective with a root panel of type StaticWorkbenchPanelPresenter.
 */
@Dependent
@Named( "org.uberfire.wbtest.client.resize.TabResizeTestingPerspective" )
public class TabResizeTestingPerspective extends AbstractTestPerspectiveActivity {

    @Inject
    public TabResizeTestingPerspective( PlaceManager placeManager ) {
        super( placeManager );
    }

    @Override
    public PerspectiveDefinition getDefaultPerspectiveLayout() {
        PerspectiveDefinition pdef = new PerspectiveDefinitionImpl( MultiTabWorkbenchPanelPresenter.class.getName() );
        pdef.setName( "TabResizeTestingPerspective" );
        pdef.getRoot().addPart( NestingScreen.class.getName() + "?place=One" );
        pdef.getRoot().addPart( NestingScreen.class.getName() + "?place=Two" );
        pdef.getRoot().addPart( NestingScreen.class.getName() + "?place=Three" );
        pdef.getRoot().addPart( NestingScreen.class.getName() + "?place=Four" );
        pdef.getRoot().addPart( NestingScreen.class.getName() + "?place=Five" );
        pdef.getRoot().addPart( NestingScreen.class.getName() + "?place=Six" );
        pdef.getRoot().addPart( NestingScreen.class.getName() + "?place=Seven" );
        pdef.getRoot().addPart( NestingScreen.class.getName() + "?place=Eight" );
        return pdef;
    }
}
