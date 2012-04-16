package com.jensjansson.depot.client.ui;

import com.vaadin.terminal.gwt.client.communication.ServerRpc;

public interface DepotServerRpc extends ServerRpc {

    public void depotHasOpened();

    public void depotHasClosed();
}
