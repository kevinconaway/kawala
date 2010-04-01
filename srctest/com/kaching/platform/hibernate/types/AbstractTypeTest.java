/**
 * Copyright 2009 KaChing Group Inc. Licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.kaching.platform.hibernate.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.junit.Before;
import org.junit.Test;

public class AbstractTypeTest {

  private AbstractImmutableType type;
  private Serializable serializable;

  @Before
  public void before() {
    type = new AbstractImmutableType() {
    };
    serializable = new Serializable() {
      private static final long serialVersionUID = 1L;
    };
  }
  
  @Test
  public void equality() {
    assertTrue(type.equals(null, null));
    assertFalse(type.equals(serializable, null));
    assertFalse(type.equals(null, serializable));
    assertTrue(type.equals(serializable, serializable));
  }
  
  @Test
  public void hashing() {
    assertEquals(serializable.hashCode(), type.hashCode(serializable));
  }

}