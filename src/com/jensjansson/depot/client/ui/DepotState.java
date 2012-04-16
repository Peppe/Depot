package com.jensjansson.depot.client.ui;

import com.vaadin.terminal.gwt.client.ComponentState;
import com.vaadin.terminal.gwt.client.Connector;

public class DepotState extends ComponentState {

    private Connector header;
    private Connector component;
    private boolean animationEnabled = false;

    public Connector getHeader() {
        return header;
    }

    public void setHeader(Connector header) {
        this.header = header;
    }

    public Connector getComponent() {
        return component;
    }

    public void setComponent(Connector component) {
        this.component = component;
    }

    public boolean isAnimationEnabled() {
        return animationEnabled;
    }

    public void setAnimationEnabled(boolean animationEnabled) {
        this.animationEnabled = animationEnabled;
    }
}
