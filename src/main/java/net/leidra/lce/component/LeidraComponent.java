package net.leidra.lce.component;

import com.vaadin.ui.TextField;

public class LeidraComponent extends TextField {
	private static final long serialVersionUID = -7019263726188918625L;
	
	public LeidraComponent() {
		this.extend();
	}
	
	private void extend() {
		this.addExtension(new LeidraComponentExtension());
	}
}