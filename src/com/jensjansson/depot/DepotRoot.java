package com.jensjansson.depot;

import com.jensjansson.depot.Depot.CloseHandler;
import com.jensjansson.depot.Depot.OpenHandler;
import com.vaadin.terminal.WrappedRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Root;
import com.vaadin.ui.VerticalLayout;

public class DepotRoot extends Root {
    @Override
    public void init(WrappedRequest request) {

        VerticalLayout wrapper = new VerticalLayout();
        VerticalLayout layout = new VerticalLayout();
        Depot depot = new Depot();
        // depot.setAnimationsEnabled(true);
        depot.setWidth("600px");

        HorizontalLayout header = new HorizontalLayout();
        header.setWidth("100%");
        header.setHeight("30px");
        Label headerLabel = new Label("Click on me to reveal secrets");
        headerLabel.setWidth(null);
        header.addComponent(headerLabel);
        header.setComponentAlignment(headerLabel, Alignment.TOP_CENTER);

        Label label = new Label("Hidden panel with label");
        Panel tableLayout = new Panel();
        tableLayout.setWidth("100%");
        tableLayout.setHeight("300px");
        tableLayout.addComponent(label);
        depot.setComponent(tableLayout);

        depot.setOpenHandler(new OpenHandler() {

            public void depotOpened() {
                Root.getCurrentRoot().showNotification("Depot has been opened",
                        Notification.TYPE_TRAY_NOTIFICATION);
            }
        });

        depot.setCloseHandler(new CloseHandler() {

            public void depotClosed() {
                Root.getCurrentRoot().showNotification("Depot has been closed",
                        Notification.TYPE_TRAY_NOTIFICATION);
            }
        });

        Button button = new Button("The button that does nothing");
        CssLayout spacer = new CssLayout();
        spacer.setHeight("70px");

        depot.setHeader(header);
        layout.addComponent(spacer);
        layout.addComponent(depot);
        layout.addComponent(button);
        layout.setComponentAlignment(depot, Alignment.TOP_CENTER);
        layout.setComponentAlignment(button, Alignment.TOP_CENTER);
        layout.setSizeUndefined();
        wrapper.setSizeFull();
        wrapper.addComponent(layout);
        wrapper.setComponentAlignment(layout, Alignment.TOP_CENTER);
        setContent(wrapper);

    }

}
