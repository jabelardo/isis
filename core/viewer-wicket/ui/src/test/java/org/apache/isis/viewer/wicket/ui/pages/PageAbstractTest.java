/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.apache.isis.viewer.wicket.ui.pages;

import org.junit.Test;

import org.apache.isis.viewer.wicket.ui.util.CssClassAppender;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public abstract class PageAbstractTest {

    public static class AsCssStyle extends PageAbstractTest {

        @Test
        public void withSpacesAndCapitals() throws Exception {
            assertThat(CssClassAppender.asCssStyle("Simple App"), is("simple-app"));
        }

        @Test
        public void withOtherCharacters() throws Exception {
            assertThat(CssClassAppender.asCssStyle("Kitchen Sink (Demo) App"), is("kitchen-sink-demo-app"));
        }

    }
}