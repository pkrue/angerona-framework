package angerona.fw.asml;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import angerona.fw.error.InvokeException;
import angerona.fw.reflection.BooleanExpression;
import angerona.fw.reflection.Condition;
import angerona.fw.reflection.Context;
import angerona.fw.reflection.Value;
import angerona.fw.serialize.SerializeHelper;

/**
 * An ASML conditional represents the if, elseif, else construct. Every if
 * and else if part consists of a condition and a sequence of commands which is 
 * executed if the condition is true. The else part is a sequence of commands
 * which is executed if no previous condition is true. The else if part are executed
 * in the ordering the appear in the XML file.
 * 
 * @author Tim Janus
 */
@Root(name="conditional")
public class Conditional extends ASMLCommand {

	/**
	 * An inner class representing a conditional sequence like a if or
	 * an else if construct. It extends the CommandSequence to support
	 * its XML syntax but adds an attribute condition to the command
	 * sequence.
	 * 
	 * @author Tim Janus
	 */
	@Root
	private static class ConditionalSequence extends CommandSequence {

		/** the condition which decides if the command sequence is executed */
		@Attribute(name="condition", required=true)
		public Condition condition;
		
		/**
		 * Sets the context for the command sequence and the condition.
		 * @param context	The context used for execution.
		 */
		@Override
		public void setContext(Context context) {
			super.setContext(context);
			condition.setContext(context);
		}
		
	}
	
	/** A conditional sequence representing the if part of the conditional */
	@Element(name="if", required=true)
	ConditionalSequence ifPart;
	
	/** An ordered list of conditional sequences representing the else if parts of the conditional */
	@ElementList(entry="elseif", inline=true, required=false, empty=true)
	List<ConditionalSequence> elseIfPart;
	
	/** The command sequence for the else block of the conditional */
	@Element(name="else", required=false)
	CommandSequence elseCommandos;
	
	/**
	 * Executes the if, elseif, else construct.
	 */
	@Override
	protected void executeInternal() throws InvokeException {
		boolean done = false;
		ifPart.setContext(getContext());
		if(ifPart.condition.evaluate()) {
			ifPart.execute(getContext());
			done = true;
		} else if(elseIfPart != null) {
			for(ConditionalSequence cs : elseIfPart) {
				cs.setContext(getContext());
				if(cs.condition.evaluate()) {
					cs.execute(getContext());
					done = true;
					break;
				}
			}
		}
		
		if(!done && elseCommandos != null) {
			elseCommandos.execute(getContext());
		}
	}
	
	/**
	 * Method to test the xml de-serialization of the ASML conditional.
	 * @param args
	 * @throws ClassNotFoundException
	 */
	public static void main(String [] args) throws ClassNotFoundException {
		Conditional test = new Conditional();
		test.ifPart = new ConditionalSequence();
		test.ifPart.condition = new BooleanExpression(new Value("1"), BooleanExpression.Operator.LESS, new Value("2"));
		test.ifPart.addCommando(new Assign("1k", new Value("1000")));
		
		SerializeHelper.outputXml(test, System.out);
	}
}
