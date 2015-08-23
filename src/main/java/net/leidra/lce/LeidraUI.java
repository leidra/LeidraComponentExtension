package net.leidra.lce;

import javax.servlet.annotation.WebServlet;

import net.leidra.lce.component.LeidraComponent;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("leidratheme")
@Widgetset("net.leidra.lce.component.LeidraWidgetset")
public class LeidraUI extends UI {
	private static final long serialVersionUID = -2412664185920131035L;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

		LeidraComponent lc = new LeidraComponent();
		layout.addComponent(lc);
	}

	@WebServlet(urlPatterns = "/*", name = "LeidraUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = LeidraUI.class, productionMode = false)
	public static class LeidraUIServlet extends VaadinServlet {
		private static final long serialVersionUID = 1836614939609597965L;
	}
}
