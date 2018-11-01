package postfix;

import java.util.Stack;

/**
 * 
 * @author Sathish Gopalakrishnan
 * 
 * This class contains a method to evaluate an arithmetic expression
 * that is in Postfix notation (or Reverse Polish Notation).
 * See <a href="https://en.wikipedia.org/wiki/Reverse_Polish_notation">Wikipedia</a>
 * for details on the notation.
 *
 */
public class PostfixEvaluator {
	
	private String arithmeticExpr;
	
	/**
	 * This is the only constructor for this class.
	 * It takes a string that represents an arithmetic expression
	 * as input argument.
	 * 
	 * @param expr is a string that represents an arithmetic expression 
	 * <strong>in Postfix notation</strong>.
	 */
	public PostfixEvaluator( String expr ) {
		arithmeticExpr = expr;
	}
	
	/**
	 * This method evaluates the arithmetic expression that 
	 * was passed as a string to the constructor for this class.
	 * 
	 * @return the value of the arithmetic expression
	 * @throws MalformedExpressionException if the provided expression is not
	 * 	a valid expression in Postfix notation
	 */
	double eval( ) throws MalformedExpressionException {
		// TODO: Implement this method.
		// The code provided here is for illustration only, and
		// can be deleted when you write your implementation.

		// Using a stack makes it very simple to evaluate the
		// arithmetic expression.
		// See http://docs.oracle.com/javase/8/docs/api/java/util/Stack.html

		// Use the Scanner to get the elements (tokens) in the
		// arithmetic expression.
		double x, y, result=0;

		Scanner scanner = new Scanner(arithmeticExpr);
		Stack<Token> exp = new Stack<>();
		while(!scanner.isEmpty()) {
            Token currToken = scanner.getToken();
		    if(currToken.isDouble()){
		        exp.push(currToken);
            }
            else { // if the token is an operator
                //check is there is a number to subtract from
                if(exp.isEmpty()) { throw new MalformedExpressionException("hello???"); }
                else {
                    y = exp.pop().getValue();
                    if(exp.isEmpty()) {throw new MalformedExpressionException("hello again??"); }
                    else {
                        x = exp.pop().getValue();
                    }
                }
                if(currToken.equals("+")) {
                    exp.push(new Token(x + y));
                }
                else if(currToken.equals("-")) {
                    exp.push(new Token(x - y));
                }
                else if(currToken.equals("*")) {
                    exp.push(new Token(x * y));
                }
                else if(currToken.equals("/")) {
                    exp.push(new Token(x / y));
                }
                else {
                    throw new MalformedExpressionException("invalid operator fam");
                }
            }
            scanner.eatToken();
		}
		result = exp.pop().getValue();
		if (!exp.isEmpty()) { throw new MalformedExpressionException("too many operators dude"); }
		else {
		    return result;
        }
		// now process the token, etc.
		// You should read the implementation of the Token class
		// to determine what methods you can and should use.
		
		// It is sufficient to support the four basic operations:
		// addition, subtraction, multiplication & division.
	}
	
}