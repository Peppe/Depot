package com.jensjansson.depot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.jensjansson.depot.client.ui.DepotClientRpc;
import com.jensjansson.depot.client.ui.DepotServerRpc;
import com.jensjansson.depot.client.ui.DepotState;
import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.Component;

public class Depot extends AbstractComponentContainer {

    public interface OpenHandler {
        public void depotOpened();
    }

    public interface CloseHandler {
        public void depotClosed();
    }

    private OpenHandler openHandler = null;
    private CloseHandler closeHandler = null;

    private final DepotClientRpc clientRpc;
    private DepotServerRpc serverRpc = new DepotServerRpc() {

        public void depotHasOpened() {
            if (openHandler != null) {
                openHandler.depotOpened();
                requestRepaint();
            }
        }

        public void depotHasClosed() {
            if (closeHandler != null) {
                closeHandler.depotClosed();
                requestRepaint();
            }
        }
    };

    public Depot() {
        clientRpc = getRpcProxy(DepotClientRpc.class);
        registerRpc(serverRpc);
    }

    public Depot(Component header, Component component) {
        this();
        setHeader(header);
        setComponent(component);
    }

    public void setHeader(Component header) {
        if (getHeader() != null) {
            removeComponent(getHeader());
        }
        getState().setHeader(header);
        addComponent(header);
    }

    public Component getHeader() {
        return (Component) getState().getHeader();
    }

    public void setComponent(Component component) {
        if (getComponent() != null) {
            removeComponent(getComponent());
        }
        getState().setComponent(component);
        addComponent(component);
    }

    public Component getComponent() {
        return (Component) getState().getComponent();
    }

    public OpenHandler getOpenHandler() {
        return openHandler;
    }

    public void setOpenHandler(OpenHandler openHandler) {
        this.openHandler = openHandler;
    }

    public CloseHandler getCloseHandler() {
        return closeHandler;
    }

    public void setCloseHandler(CloseHandler closeHandler) {
        this.closeHandler = closeHandler;
    }

    public void replaceComponent(Component oldComponent, Component newComponent) {
        throw new UnsupportedOperationException(
                "no replacing of components. use setComponent(Component)");
    }

    public int getComponentCount() {
        int components = 0;
        if (getComponent() != null) {
            components++;
        }
        if (getHeader() != null) {
            components++;
        }

        return components;
    }

    public Iterator<Component> getComponentIterator() {
        List<Component> components = new ArrayList<Component>();
        if (getComponent() != null) {
            components.add(getComponent());
        }
        if (getHeader() != null) {
            components.add(getHeader());
        }
        return components.iterator();
    }

    @Override
    public DepotState getState() {
        return (DepotState) super.getState();
    }

    public void setAnimationsEnabled(boolean enabled) {
        getState().setAnimationEnabled(enabled);
        requestRepaint();
    }

    public void open() {
        clientRpc.open();
    }

    public void close() {
        clientRpc.close();
    }

}
