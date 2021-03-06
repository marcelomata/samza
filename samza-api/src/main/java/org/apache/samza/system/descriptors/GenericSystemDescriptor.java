/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.samza.system.descriptors;


import org.apache.samza.serializers.Serde;

/**
 * A descriptor for a generic system.
 * <p>
 * If the system being used provides its own system and stream descriptor implementations, they should be used instead.
 * Otherwise, this {@link GenericSystemDescriptor} may be used to provide Samza-specific properties of the system.
 * Additional system specific properties may be provided using {@link #withSystemConfigs}
 * <p>
 * System properties provided in configuration override corresponding properties configured using a descriptor.
 */
public final class GenericSystemDescriptor extends SystemDescriptor<GenericSystemDescriptor>
    implements SimpleInputDescriptorProvider, OutputDescriptorProvider {

  /**
   * Constructs a {@link GenericSystemDescriptor} instance with no system level serde.
   * Serdes must be provided explicitly at stream level when getting input or output descriptors.
   *
   * @param systemName name of this system
   * @param factoryClassName name of the SystemFactory class for this system
   */
  public GenericSystemDescriptor(String systemName, String factoryClassName) {
    super(systemName, factoryClassName, null, null);
  }

  @Override
  public <StreamMessageType> GenericInputDescriptor<StreamMessageType> getInputDescriptor(
      String streamId, Serde<StreamMessageType> serde) {
    return new GenericInputDescriptor<>(streamId, this, serde);
  }

  @Override
  public <StreamMessageType> GenericOutputDescriptor<StreamMessageType> getOutputDescriptor(
      String streamId, Serde<StreamMessageType> serde) {
    return new GenericOutputDescriptor<>(streamId, this, serde);
  }
}
