/*
 *   Copyright 2015 JBoss Inc
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package org.uberfire.preferences;

import org.uberfire.mvp.ParameterizedCommand;

public interface PreferenceStorage {

    <T> void read( final Scope store,
                   final String key,
                   final ScopeType[] resolutionOrder,
                   final ParameterizedCommand<T> value );

    <T> void read( final Scope scope,
                   final String key,
                   final ParameterizedCommand<T> callback );

    void write( final Scope store,
                final String key,
                final Object value );

    void delete( final Scope store,
                 final String key );

    void delete( final String key );
}
