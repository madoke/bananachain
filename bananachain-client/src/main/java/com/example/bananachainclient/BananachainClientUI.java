package com.example.bananachainclient;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("BananachainClient")
public class BananachainClientUI extends UI{
	
	@Override
	protected void init(VaadinRequest request){
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(false);
		layout.addComponent(new Button("Component 1"));
		layout.addComponent(new Button("Component 2"));
		layout.addComponent(new Button("Component 3"));
		setContent(layout);
	}
}
