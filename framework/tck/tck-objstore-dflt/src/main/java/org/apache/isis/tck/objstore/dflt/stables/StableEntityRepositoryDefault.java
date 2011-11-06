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

package org.apache.isis.tck.objstore.dflt.stables;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.annotation.QueryOnly;
import org.apache.isis.tck.dom.stables.StableEntity;
import org.apache.isis.tck.dom.stables.StableEntityRepository;

public class StableEntityRepositoryDefault extends AbstractFactoryAndRepository implements StableEntityRepository {

    @Override
    public String getId() {
        return "stables";
    }

    @Override
    public StableEntity newTransientEntity() {
        return newTransientInstance(StableEntity.class);
    }

    @Override
    public StableEntity newPersistentEntity(String name) {
        StableEntity entity = newTransientEntity();
        entity.setName(name);
        getContainer().persist(entity);
        return entity;
    }

    @QueryOnly
    public List<StableEntity> list() {
        return allInstances(StableEntity.class);
    }

}