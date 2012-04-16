package com.jensjansson.depot.client.ui;

import com.vaadin.terminal.gwt.client.communication.ClientRpc;

public interface DepotClientRpc extends ClientRpc {

    public void open();

    public void close();
}
