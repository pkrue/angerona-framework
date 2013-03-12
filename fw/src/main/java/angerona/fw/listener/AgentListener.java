package angerona.fw.listener;

import angerona.fw.BaseAgentComponent;
import angerona.fw.BaseBeliefbase;
import angerona.fw.Perception;
import angerona.fw.logic.Beliefs;

/**
 * The agent listener gets informed if something of an agent changes like the
 * update of its beliefs or of a specific belief base or even if the content of
 * an agent's component changes.
 * 
 * @author Tim Janus
 */
public interface AgentListener {
	
	/** the identifier used to name a world belief base */
	public static final String WORLD = "_WORLD_";
	
	/** 
	 * is called when the agent updates it's beliefs with the given perception.
	 * @param percept	Reference to the perception used for the belief update.
	 */
	void updateBeliefs(Perception percept, Beliefs oldBeliefs, Beliefs newBeliefs);
	
	/**
	 * is called when a belief base of an agent changes.
	 * @param bb 		reference to the changed belief base.
	 * @param percept	The perception causing the change of the belief-base.
	 * @param space 	a string explaining the space of the beliefbase
	 * 					this might be "WORLD" for the beliefs of the 
	 * 					agent himself or "alice" indicating this as an 
	 * 					view on alices beliefs.
	 */
	void beliefbaseChanged(BaseBeliefbase bb, Perception percept, String space);
	
	/**
	 * is called when the given component is added to list of components of the agent
	 * @param comp	the added component.
	 */
	void componentAdded(BaseAgentComponent comp);
	
	/**
	 * is called when the given component is removed from the the agent
	 * @param comp	the removed component.
	 */
	void componentRemoved(BaseAgentComponent comp);
}
