package angerona.fw.gui.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import angerona.fw.gui.base.ViewComponent;
import angerona.fw.gui.controller.TreeController;

public class ResourcenView extends JPanel implements ViewComponent {

	/** kick warning */
	private static final long serialVersionUID = -8021405489946274962L;

	private TreeController controller;
	
	private JTree tree;
	
	public ResourcenView(TreeController controller) {
		setController(controller);
		if(controller == null) {
			throw new IllegalArgumentException("The controller has to be set before initilization");
		}
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(tree), BorderLayout.CENTER);
		tree.setRootVisible(false);
	}
	
	public void setController(TreeController controller) {
		if(controller == null)
			throw new IllegalArgumentException("Controller must not be null.");
		this.controller = controller;
		tree = controller.getTree();
	}

	@Override
	public JPanel getPanel() {
		return this;
	}

	@Override
	public String getDefaultTitle() {
		return "Resourcen";
	}
}
