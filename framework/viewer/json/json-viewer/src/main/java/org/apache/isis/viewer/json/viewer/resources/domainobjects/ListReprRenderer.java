/**
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.isis.viewer.json.viewer.resources.domainobjects;

import java.util.Collection;

import org.apache.isis.core.metamodel.adapter.ObjectAdapter;
import org.apache.isis.core.metamodel.spec.ObjectSpecification;
import org.apache.isis.viewer.json.applib.JsonRepresentation;
import org.apache.isis.viewer.json.applib.RepresentationType;
import org.apache.isis.viewer.json.applib.blocks.LinkRepresentation;
import org.apache.isis.viewer.json.viewer.ResourceContext;
import org.apache.isis.viewer.json.viewer.representations.LinkBuilder;
import org.apache.isis.viewer.json.viewer.representations.LinkFollower;
import org.apache.isis.viewer.json.viewer.representations.Rel;
import org.apache.isis.viewer.json.viewer.representations.RendererFactory;
import org.apache.isis.viewer.json.viewer.representations.RendererFactoryRegistry;
import org.apache.isis.viewer.json.viewer.representations.ReprRenderer;
import org.apache.isis.viewer.json.viewer.representations.ReprRendererAbstract;
import org.apache.isis.viewer.json.viewer.representations.ReprRendererFactoryAbstract;
import org.apache.isis.viewer.json.viewer.resources.domaintypes.DomainTypeReprRenderer;

public class ListReprRenderer extends ReprRendererAbstract<ListReprRenderer, Collection<ObjectAdapter>> {

    public static class Factory extends ReprRendererFactoryAbstract {
        public Factory() {
            super(RepresentationType.LIST);
        }

        @Override
        public ReprRenderer<?, ?> newRenderer(ResourceContext resourceContext, LinkFollower linkFollower, JsonRepresentation representation) {
            return new ListReprRenderer(resourceContext, linkFollower, getRepresentationType(), representation);
        }
    }

    private ObjectAdapterLinkTo linkToBuilder;
    private Collection<ObjectAdapter> objectAdapters;
    private ObjectSpecification elementType;
    private ObjectSpecification returnType;

    private ListReprRenderer(ResourceContext resourceContext, LinkFollower linkFollower, RepresentationType representationType, JsonRepresentation representation) {
        super(resourceContext, linkFollower, representationType, representation);
        usingLinkToBuilder(new DomainObjectLinkTo());
    }
    
    public ListReprRenderer usingLinkToBuilder(ObjectAdapterLinkTo objectAdapterLinkToBuilder) {
        this.linkToBuilder = objectAdapterLinkToBuilder.usingResourceContext(resourceContext);
        return this;
    }

    @Override
    public ListReprRenderer with(Collection<ObjectAdapter> objectAdapters) {
        this.objectAdapters = objectAdapters;
        return this;
    }

    public ListReprRenderer withReturnType(ObjectSpecification returnType) {
        this.returnType = returnType;
        return this;
    }

    public ListReprRenderer withElementType(ObjectSpecification elementType) {
        this.elementType = elementType;
        return this;
    }

    public JsonRepresentation render() {
        addValues();
        
        addLink(Rel.RETURN_TYPE, returnType);
        addLink(Rel.ELEMENT_TYPE, elementType);
        
        getExtensions();

        return representation;
    }

    private void addValues() {
        if(objectAdapters == null) {
            return;
        }
        
        JsonRepresentation values = JsonRepresentation.newArray();
        final LinkFollower linkFollower = getLinkFollower().follow("values");

        for(ObjectAdapter adapter: objectAdapters) {
            JsonRepresentation linkToObject = linkToBuilder.with(adapter).builder().build();
            values.arrayAdd(linkToObject);

            if(linkFollower.matches(linkToObject)) {
                final RendererFactory factory = RendererFactoryRegistry.instance.find(RepresentationType.DOMAIN_OBJECT);
                final DomainObjectReprRenderer renderer = 
                        (DomainObjectReprRenderer) factory.newRenderer(getResourceContext(), linkFollower, JsonRepresentation.newMap());
                JsonRepresentation domainObject = renderer.with(adapter).render();
                linkToObject.mapPut("value", domainObject);
            }
        }
        representation.mapPut("values", values);
    }

    private void addLinkToReturnType() {
        addLink(Rel.RETURN_TYPE, returnType);
    }

    private void addLinkToElementType() {
        addLink(Rel.ELEMENT_TYPE, elementType);
    }


}