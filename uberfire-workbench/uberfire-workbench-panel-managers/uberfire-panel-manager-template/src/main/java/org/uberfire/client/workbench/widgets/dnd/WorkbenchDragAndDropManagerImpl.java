/*
 * Copyright 2012 JBoss Inc
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.uberfire.client.workbench.widgets.dnd;

import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.IsWidget;
import org.uberfire.client.workbench.BeanFactory;
import org.uberfire.client.workbench.panels.WorkbenchPanelView;

/**
 * A Manager of drag and drop operations within the Workbench.
 */
@ApplicationScoped
public class WorkbenchDragAndDropManagerImpl implements WorkbenchDragAndDropManager {

    //The context of the drag operation
    private WorkbenchDragContext workbenchContext = null;

    public WorkbenchDragAndDropManagerImpl(){};

    @Override
    public void makeDraggable( IsWidget draggable,
                               IsWidget dragHandle ) {
    }

    @Override
    public void registerDropController( final WorkbenchPanelView owner,
                                        final DropController dropController ) {
    }

    @Override
    public void unregisterDropController( final WorkbenchPanelView view ) {
    }

    @Override
    public void unregisterDropControllers() {
    }

    @Override
    public void setWorkbenchContext( final WorkbenchDragContext workbenchContext ) {
        this.workbenchContext = workbenchContext;
    }

    @Override
    public WorkbenchDragContext getWorkbenchContext() {
        return this.workbenchContext;
    }

}
