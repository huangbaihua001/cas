/**
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.jasig.cas.server.authentication;

import org.jasig.services.persondir.IPersonAttributeDao;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

/**
 * JPA compliant implementation of the Principal.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.5
 */
@Embeddable
public class JpaAttributePrincipalImpl extends AbstractAttributePrincipal {

    private static IPersonAttributeDao IPERSONATTRIBUTEDAO;

    @Column(name="princ_name",nullable = false, insertable = true, length=256, updatable = false)
    private String name;

    public JpaAttributePrincipalImpl() {
        // this is for JPA
    }

    public JpaAttributePrincipalImpl(final String name) {
        this.name = name;
    }

    public final String getName() {
        return this.name;
    }

    public final Map<String, List<Object>> getAttributes() {
        return IPERSONATTRIBUTEDAO.getPerson(this.name).getAttributes();
    }

    public static void setPersonAttributeDao(final IPersonAttributeDao personAttributeDao) {
        IPERSONATTRIBUTEDAO = personAttributeDao;
    }
}