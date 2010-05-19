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
package com.kaching.platform.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Constructor;

import org.junit.Test;

public class StringConstructorConverterTest {

  @Test(expected = IllegalArgumentException.class)
  public void checksConstructorIsTakesOneString1() {
    converter(DoesNotTakeSingleString1.class);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checksConstructorIsTakesOneString2() {
    converter(DoesNotTakeSingleString2.class);
  }
  
  @Test
  public void convertsProperly() {
    StringConstructorConverter<TakesSingleString> converter = converter(TakesSingleString.class);
    
    // from string
    TakesSingleString fromString = converter.fromString("hello world");
    assertNotNull(fromString);
    assertEquals("hello world", fromString.representation);
    
    // to string
    assertEquals("hello world", converter.toString(fromString));
  }

  @SuppressWarnings("unchecked")
  private <T> StringConstructorConverter<T> converter(Class<T> klass) {
    Constructor<?>[] declaredConstructors = klass.getDeclaredConstructors();
    assertEquals(1, declaredConstructors.length);
    return (StringConstructorConverter<T>) new StringConstructorConverter<Object>(declaredConstructors[0]);
  }
  
  static class TakesSingleString {
    private final String representation;
    TakesSingleString(String representation) {
      this.representation = representation;
    }
    @Override
    public String toString() {
      return representation;
    }
  }
  
  static class DoesNotTakeSingleString1 {
  }
  
  static class DoesNotTakeSingleString2 {
    DoesNotTakeSingleString2(String a, String b) {
    }
  }
  
}