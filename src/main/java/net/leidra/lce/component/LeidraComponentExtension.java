package net.leidra.lce.component;

import java.util.ArrayList;
import java.util.List;

import net.leidra.lce.component.client.ResetButtonClickRpc;

import com.vaadin.server.AbstractExtension;

public class LeidraComponentExtension extends AbstractExtension {
	private static final long serialVersionUID = -8432187835120823411L;
	private final List<ResetButtonClickListener> listeners = new ArrayList<>();

	private final ResetButtonClickRpc resetButtonClickRpc = new ResetButtonClickRpc() {
		private static final long serialVersionUID = 3587180506656583383L;

		@Override
		public void resetButtonClick() {
			for (ResetButtonClickListener listener : listeners) {
				listener.resetButtonClicked();
			}
		}
	};

	public LeidraComponentExtension() {
		registerRpc(resetButtonClickRpc);
	}

	public void addResetButtonClickedListener(ResetButtonClickListener listener) {
		listeners.add(listener);
	}

	public void removeResetButtonClickListener(ResetButtonClickListener listener) {
		listeners.remove(listener);
	}

}