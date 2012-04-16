package com.jensjansson.depot.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.Widget;
import com.jensjansson.depot.Depot;
import com.vaadin.terminal.gwt.client.ComponentConnector;
import com.vaadin.terminal.gwt.client.ConnectorHierarchyChangeEvent;
import com.vaadin.terminal.gwt.client.communication.RpcProxy;
import com.vaadin.terminal.gwt.client.communication.StateChangeEvent;
import com.vaadin.terminal.gwt.client.ui.AbstractComponentContainerConnector;
import com.vaadin.terminal.gwt.client.ui.Connect;

//TODO implement these:
//panel.addCloseHandler(handler);
//panel.addOpenHandler(handler);

@Connect(Depot.class)
public class DepotConnector extends AbstractComponentContainerConnector {

    public static final String CLASSNAME = "v-depot";

    DisclosurePanel panel = null;
    private DepotServerRpc rpc = RpcProxy.create(DepotServerRpc.class, this);

    @Override
    protected void init() {
        super.init();
        DisclosurePanel panel = getWidget();
        panel.setAnimationEnabled(true);
        registerRpc(DepotClientRpc.class, new DepotClientRpc() {
            public void open() {
                getWidget().setOpen(true);
                DepotConnector.this.getLayoutManager().setNeedsMeasure(
                        DepotConnector.this);
            }

            public void close() {
                getWidget().setOpen(false);
                DepotConnector.this.getLayoutManager().setNeedsMeasure(
                        DepotConnector.this);
            }
        });

        panel.addOpenHandler(new OpenHandler<DisclosurePanel>() {
            public void onOpen(OpenEvent<DisclosurePanel> event) {
                rpc.depotHasOpened();
                // void
                // com.vaadin.terminal.gwt.client.LayoutManager.setNeedsMeasure(ComponentConnector
                // component)

            }
        });
        panel.addCloseHandler(new CloseHandler<DisclosurePanel>() {
            public void onClose(CloseEvent<DisclosurePanel> event) {
                rpc.depotHasClosed();
            }
        });
        panel.setStyleName(CLASSNAME);
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);
        getWidget().setAnimationEnabled(getState().isAnimationEnabled());
    }

    @Override
    public void onConnectorHierarchyChange(ConnectorHierarchyChangeEvent event) {
        super.onConnectorHierarchyChange(event);

        ComponentConnector header = (ComponentConnector) getState().getHeader();
        ComponentConnector component = (ComponentConnector) getState()
                .getComponent();
        Widget headerWidget = null;
        Widget componentWidget = null;
        if (header != null) {
            headerWidget = header.getWidget();
        }
        if (component != null) {
            componentWidget = component.getWidget();
        }
        getWidget().setHeader(headerWidget);
        getWidget().setContent(componentWidget);
    }

    @Override
    public DepotState getState() {
        return (DepotState) super.getState();
    }

    @Override
    protected Widget createWidget() {
        return GWT.create(DisclosurePanel.class);
    }

    @Override
    public DisclosurePanel getWidget() {
        return (DisclosurePanel) super.getWidget();
    }

    public void updateCaption(ComponentConnector connector) {
    }
}
