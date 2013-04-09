package angerona.fw.knowhow;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.xeoh.plugins.base.annotations.PluginImplementation;
import angerona.fw.AgentComponent;
import angerona.fw.AngeronaPluginAdapter;
import angerona.fw.gui.UIPlugin;
import angerona.fw.gui.base.ViewComponent;
import angerona.fw.knowhow.gui.KnowhowView;
import angerona.fw.operators.BaseOperator;

@PluginImplementation
public class KnowhowPlugin extends AngeronaPluginAdapter 
	implements UIPlugin {

	@Override
	public Map<String, Class<? extends ViewComponent>> getUIComponents() {
		Map<String, Class<? extends ViewComponent>> reval = new HashMap<String, Class<? extends ViewComponent>>();
		reval.put("Knowhow", KnowhowView.class);
		return reval;
	}
	
	@Override
	public List<Class<? extends AgentComponent>> getAgentComponentImpl() {
		List<Class<? extends AgentComponent>> reval = new LinkedList<Class<? extends AgentComponent>>();
		reval.add(KnowhowBase.class);
		return reval;
	}
	
	@Override
	public List<Class<? extends BaseOperator>> getOperators() {
		List<Class<? extends BaseOperator>> reval = new LinkedList<>();
		reval.add(KnowhowSubgoal.class);
		return reval;
	}



}
