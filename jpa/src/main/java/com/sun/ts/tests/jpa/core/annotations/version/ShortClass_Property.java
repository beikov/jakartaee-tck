/*
 * Copyright (c) 2008, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package com.sun.ts.tests.jpa.core.annotations.version;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "A_BASIC")
public class ShortClass_Property implements java.io.Serializable {

	// ===========================================================
	// instance variables

	protected String id;

	protected Short basicShort;

	protected String name;

	// ===========================================================
	// constructors
	public ShortClass_Property() {
	}

	public ShortClass_Property(String id) {
		this.id = id;
	}

	public ShortClass_Property(String id, Short value) {

		this.id = id;
		this.basicShort = value;
	}

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Basic
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Version
	public Short getBasicShort() {
		return this.basicShort;
	}

	public void setBasicShort(Short value) {
		this.basicShort = value;
	}

	public boolean equals(Object o) {
		ShortClass_Property other;
		boolean result = false;

		if (!(o instanceof ShortClass_Property)) {
			return result;
		}
		other = (ShortClass_Property) o;

		if (this.getId().equals(other.getId()) && this.basicShort.equals(other.basicShort)
				&& this.name.equals(other.getName())) {
			result = true;
		}

		return result;
	}

	public int hashCode() {
		int myHash;

		myHash = this.getId().hashCode() + this.basicShort + this.name.hashCode();

		return myHash;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getSimpleName() + "[");
		result.append("id: " + getId());
		result.append(", version: " + basicShort);
		result.append(", name: " + name);
		result.append("]");
		return result.toString();
	}
}
