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

package org.uberfire.client.authz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.jboss.errai.ioc.client.container.SyncBeanDef;
import org.jboss.errai.ioc.client.container.SyncBeanManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.uberfire.client.mvp.PerspectiveActivity;
import org.uberfire.client.resources.i18n.PermissionTreeI18n;
import org.uberfire.security.Resource;
import org.uberfire.security.ResourceAction;
import org.uberfire.security.ResourceType;
import org.uberfire.security.authz.PermissionManager;
import org.uberfire.security.client.authz.tree.PermissionNode;
import org.uberfire.security.client.authz.tree.impl.DefaultLoadOptions;
import org.uberfire.security.impl.authz.DotNamedPermission;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PerspectiveTreeProviderTest {

    @Mock
    SyncBeanManager beanManager;

    @Mock
    PermissionManager permissionManager;

    @Mock
    PermissionTreeI18n i18n;

    PerspectiveTreeProvider provider;
    PermissionNode root;

    @Before
    public void setUp() {
        Collection<SyncBeanDef<PerspectiveActivity>> beanDefs = new ArrayList<>();
        SyncBeanDef<PerspectiveActivity> bean1 = mock(SyncBeanDef.class);
        SyncBeanDef<PerspectiveActivity> bean2 = mock(SyncBeanDef.class);
        PerspectiveActivity perspective1 = mock(PerspectiveActivity.class);
        PerspectiveActivity perspective2 = mock(PerspectiveActivity.class);
        when(bean1.getInstance()).thenReturn(perspective1);
        when(bean2.getInstance()).thenReturn(perspective2);
        when(perspective1.getIdentifier()).thenReturn("Perspective1");
        when(perspective2.getIdentifier()).thenReturn("Perspective2");
        beanDefs.add(bean1);
        beanDefs.add(bean2);
        when(beanManager.lookupBeans(PerspectiveActivity.class)).thenReturn(beanDefs);

        when(permissionManager.createPermission(any(ResourceType.class), any(ResourceAction.class), anyBoolean()))
                .thenReturn(new DotNamedPermission("name", true));

        when(permissionManager.createPermission(any(Resource.class), any(ResourceAction.class), anyBoolean()))
                .thenReturn(new DotNamedPermission("name", true));

        provider = new PerspectiveTreeProvider(beanManager, permissionManager, i18n);
        provider.setRootNodeName("root");
        provider.setPerspectiveName("Perspective1", "A nice perspective");
        provider.setPerspectiveName("Perspective2", "Another nice perspective");
        root = provider.buildRootNode();
    }

    @Test
    public void testEmpty() {
        DefaultLoadOptions options = new DefaultLoadOptions();
        provider.loadChildren(root, options, children -> {
            assertEquals(children.size(), 0);
        });
    }

    @Test
    public void testIncludedResourceIds() {
        DefaultLoadOptions options = new DefaultLoadOptions();
        options.setResourceIds(Arrays.asList("Perspective1"));
        provider.loadChildren(root, options, children -> {
            assertEquals(children.size(), 1);
        });
    }

    @Test
    public void testExcludedResourceIds() {
        DefaultLoadOptions options = new DefaultLoadOptions();
        options.setResourceIds(Arrays.asList("Perspective1"));
        provider.loadChildren(root, options, children -> {
            assertEquals(children.size(), 1);
        });
    }

    @Test
    public void testExcludedPerspectiveIds() {
        provider.excludePerspectiveId("Perspective1");
        DefaultLoadOptions options = new DefaultLoadOptions();
        options.setNodeNamePattern("");
        provider.loadChildren(root, options, children -> {
            assertEquals(children.size(), 1);
        });
    }

    @Test
    public void testNameSearch1() {
        DefaultLoadOptions options = new DefaultLoadOptions();
        options.setNodeNamePattern("nice");
        provider.loadChildren(root, options, children -> {
            assertEquals(children.size(), 2);
        });
    }

    @Test
    public void testNameSearch2() {
        DefaultLoadOptions options = new DefaultLoadOptions();
        options.setNodeNamePattern("another");
        provider.loadChildren(root, options, children -> {
            assertEquals(children.size(), 1);
        });
    }

    @Test
    public void testNameSearch3() {
        DefaultLoadOptions options = new DefaultLoadOptions();
        options.setNodeNamePattern("another");
        provider.loadChildren(root, options, children -> {
            assertEquals(children.size(), 1);
        });
    }
}