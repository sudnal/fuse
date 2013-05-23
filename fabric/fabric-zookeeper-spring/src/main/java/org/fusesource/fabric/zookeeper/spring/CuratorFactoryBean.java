package org.fusesource.fabric.zookeeper.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.apache.zookeeper.Watcher;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;


public class CuratorFactoryBean implements FactoryBean<CuratorFramework>, DisposableBean {

    private static final transient Log LOG = LogFactory.getLog(CuratorFactoryBean.class);

    private String connectString = "localhost:2181";
    private String password;
    private int timeout = 30000;
    private Watcher watcher;
    protected CuratorFramework curator;


    public String getConnectString() {
        return connectString;
    }

    public void setConnectString(String connectString) {
        this.connectString = connectString;
    }

    public Watcher getWatcher() {
        return watcher;
    }

    public void setWatcher(Watcher watcher) {
        this.watcher = watcher;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    // FactoryBean interface
    //-------------------------------------------------------------------------
    public CuratorFramework getObject() throws Exception {
        LOG.debug("Connecting to ZooKeeper on " + connectString);

        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .retryPolicy(new RetryOneTime(1000))
                .connectionTimeoutMs(getTimeout());

        if (password != null && !password.isEmpty()) {
            builder.authorization("digest", ("fabric:"+password).getBytes("UTF-8"));
        }

        this.curator = builder.build();
        LOG.debug("Starting curator " + curator);
        curator.start();
        return curator;
    }

    public Class<?> getObjectType() {
        return CuratorFramework.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void destroy() throws Exception {
        if (curator != null) {
            // Note we cannot use zkClient.close()
            // since you cannot currently close a client which is not connected
            curator.close();
            curator = null;
        }

    }
}