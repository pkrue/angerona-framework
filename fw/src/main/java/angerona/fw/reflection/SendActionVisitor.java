package angerona.fw.reflection;

import angerona.fw.Action;
import angerona.fw.error.InvokeException;
import angerona.fw.internal.PerceptionFactory;
import angerona.fw.logic.Beliefs;
import angerona.fw.serialize.Statement;
import angerona.fw.serialize.perception.PerceptionDO;

/**
 * This visitor implements the send action operation for agents using
 * the SendAction xml element.
 * @author Tim Janus
 */
public class SendActionVisitor extends ContextVisitor {
	
	private Beliefs beliefs;
	
	private PerceptionFactory factory;
	
	private boolean realRun;
	
	private boolean violates = false;
	
	public SendActionVisitor(PerceptionFactory factory, boolean realRun, Beliefs beliefs) {
		if(factory == null)
			throw new IllegalArgumentException("factory must not null!");
		
		this.factory = factory;
		this.realRun = realRun;
	}
	
	public boolean violates() {
		return violates;
	}
	
	@Override
	protected void runImpl(Statement statement) throws InvokeException {
		// TODO: implement inner element of actions in skill.
		//Action reval = (Action) factory.generateFromElement(statement.getInnerElement(), context);
		Action reval = (Action) factory.generateFromDataObject((PerceptionDO)statement.getComplexInfo(), context);
		
		if(realRun)
			getSelf().performAction(reval);
		else
			violates = getSelf().performThought(beliefs, reval);
		this.setReturnValueIdentifier(statement.getReturnValueIdentifier(), reval);
	}

}
