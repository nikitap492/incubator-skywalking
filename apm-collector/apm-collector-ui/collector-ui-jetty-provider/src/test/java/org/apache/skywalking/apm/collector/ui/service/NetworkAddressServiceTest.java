/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.skywalking.apm.collector.ui.service;

import org.apache.skywalking.apm.collector.core.module.MockModule;
import org.apache.skywalking.apm.collector.core.module.ModuleManager;
import org.apache.skywalking.apm.collector.storage.dao.ui.INetworkAddressUIDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * @author lican
 */
public class NetworkAddressServiceTest {

    private INetworkAddressUIDAO networkAddressUIDAO;
    private NetworkAddressService networkAddressService;

    @Before
    public void setUp() throws Exception {
        ModuleManager moduleManager = mock(ModuleManager.class);
        when(moduleManager.find(anyString())).then(invocation -> new MockModule());
        networkAddressService = new NetworkAddressService(moduleManager);
        networkAddressUIDAO = mock(INetworkAddressUIDAO.class);
        Whitebox.setInternalState(networkAddressService, "networkAddressUIDAO", networkAddressUIDAO);
    }

    @Test
    public void getNumOfDatabase() {
        int numOfDatabase = networkAddressService.getNumOfDatabase();
        Assert.assertEquals(numOfDatabase, 0);
    }

    @Test
    public void getNumOfCache() {
        int numOfCache = networkAddressService.getNumOfCache();
        Assert.assertEquals(numOfCache, 0);
    }

    @Test
    public void getNumOfMQ() {
        int numOfMQ = networkAddressService.getNumOfMQ();
        Assert.assertEquals(numOfMQ, 0);
    }
}