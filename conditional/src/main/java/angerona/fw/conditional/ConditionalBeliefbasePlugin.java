package angerona.fw.conditional;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.xeoh.plugins.base.annotations.PluginImplementation;
import angerona.fw.AngeronaPluginAdapter;
import angerona.fw.BaseBeliefbase;
import angerona.fw.conditional.gui.OCFMVPComponent;
import angerona.fw.gui.UIPlugin;
import angerona.fw.logic.BaseChangeBeliefs;
import angerona.fw.logic.BaseReasoner;
import angerona.fw.logic.BaseTranslator;
import angerona.fw.logic.conditional.ConditionalBeliefbase;
import angerona.fw.logic.conditional.ConditionalExpansion;
import angerona.fw.logic.conditional.ConditionalReasoner;
import angerona.fw.logic.conditional.ConditionalRevision;
import angerona.fw.logic.conditional.ConditionalTranslator;

@PluginImplementation
public class ConditionalBeliefbasePlugin extends AngeronaPluginAdapter implements UIPlugin {

	@Override
	public List<Class<? extends BaseBeliefbase>> getBeliefbaseImpl() {
		List<Class<? extends BaseBeliefbase>> reval = new LinkedList<Class<? extends BaseBeliefbase>>();
		reval.add(ConditionalBeliefbase.class);
		return reval;
	}

	@Override
	public List<Class<? extends BaseReasoner>> getReasonerImpl() {
		List<Class<? extends BaseReasoner>> reval = new LinkedList<Class<? extends BaseReasoner>>();
		reval.add(ConditionalReasoner.class);
		return reval;
	}

	@Override
	public List<Class<? extends BaseChangeBeliefs>> getChangeImpl() {
		List<Class<? extends BaseChangeBeliefs>> reval = new LinkedList<Class<? extends BaseChangeBeliefs>>();
		reval.add(ConditionalExpansion.class);
		reval.add(ConditionalRevision.class);
		return reval;
	}

	@Override
	public List<Class<? extends BaseTranslator>> getTranslatorImpl() {
		List<Class<? extends BaseTranslator>> reval = new LinkedList<Class<? extends BaseTranslator>>();
		reval.add(ConditionalTranslator.class);
		return reval;
	}
	
	@Override
	public Map<String, Class<? extends angerona.fw.gui.base.ViewComponent>> getUIComponents() {
		Map<String, Class<? extends angerona.fw.gui.base.ViewComponent>> reval = new HashMap<String, Class<? extends angerona.fw.gui.base.ViewComponent>>();
		reval.put("OCF-Calculator", OCFMVPComponent.class);
		return reval;
	}

}
