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

package org.uberfire.ext.layout.editor.client.components.columns;

import org.uberfire.client.mvp.UberElement;
import org.uberfire.ext.layout.editor.api.editor.LayoutComponent;

public interface Column<T> {

    UberElement<T> getView();

    String getParentId();

    Integer getSize();

    void incrementSize();

    void reduzeSize();

    void setupResize( boolean canResizeLeft, boolean canResizeRight );

    void setSize( Integer size );

    LayoutComponent getLayoutComponent();

    boolean hasInnerRows();

    void calculateSize();

    String getId();
}
