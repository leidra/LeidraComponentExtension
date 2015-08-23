package net.leidra.lce.component.client;

import net.leidra.lce.component.LeidraComponentExtension;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.DOM;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.ui.VTextField;
import com.vaadin.shared.ui.Connect;

@Connect(LeidraComponentExtension.class)
public class LeidraComponentExtensionConnector extends AbstractExtensionConnector implements AttachEvent.Handler, StateChangeEvent.StateChangeHandler {
	private static final long serialVersionUID = -6807841178859114935L;
	private static final String CLASSNAME = "lce";

	private VTextField textField;
	private Element resetButtonElement;
	private final ResetButtonClickRpc resetButtonClickRpc = RpcProxy.create(ResetButtonClickRpc.class, this);

	@Override
	protected void extend(ServerConnector target) {
		textField = (VTextField) ((ComponentConnector) target).getWidget();
		textField.addStyleName(CLASSNAME + "-textfield");

		resetButtonElement = DOM.createElement("i");
		resetButtonElement.addClassName("fa fa-times");
		resetButtonElement.addClassName(CLASSNAME + "-resetbutton");

		textField.addAttachHandler(this);
	}

	public native void addResetButtonClickListener(Element el)
	/*-{
	    var self = this;
	    el.onclick = $entry(function () {
	        self.@net.leidra.lce.component.client.LeidraComponentExtensionConnector::clearTextField()();
	    });
	}-*/;

	public native void removeResetButtonClickListener(Element el)
	/*-{
	    el.onclick = null;
	}-*/;

	@Override
	public void onAttachOrDetach(AttachEvent event) {
		if (event.isAttached()) {
			textField.getElement().getParentElement().insertAfter(resetButtonElement, textField.getElement());
			addResetButtonClickListener(resetButtonElement);
		} else {
			Element parentElement = resetButtonElement.getParentElement();
			if (parentElement != null) {
				parentElement.removeChild(resetButtonElement);
			}
			removeResetButtonClickListener(resetButtonElement);
		}
	}

	private void clearTextField() {
		resetButtonClickRpc.resetButtonClick();
		textField.setValue("");
		textField.valueChange(true);
		textField.getElement().focus();
	}
}