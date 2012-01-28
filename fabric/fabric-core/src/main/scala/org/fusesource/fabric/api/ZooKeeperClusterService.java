/**
 * Copyright (C) FuseSource, Inc.
 * http://fusesource.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fusesource.fabric.api;

import org.linkedin.zookeeper.client.IZKClient;

import java.util.List;

public interface ZooKeeperClusterService {

    static final String ENSEMBLE_AUTOSTART ="ensemble.auto.start";

    List<String> getClusterAgents();

    String getZooKeeperUrl();

    void createCluster(List<String> agents);

    void addToCluster(List<String> agents);

    void removeFromCluster(List<String> agents);

    void clean();

    IZKClient getZooKeeper();
}
