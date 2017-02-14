import java.util.Collections;
import java.util.Stack;
import java.util.Vector;

public class Lisp {

/*
 * cette fonction permet de resoudre  une expresion Lisp   
 * le param�tre peut �tre transformer en token � l'aide de la fonction getTokens(expresion) 
 * NB une seule pile peut �tre utilis�e
 * retourn "double" le resultat de l'expresion 
*/
static public double solve(String expresion){
		Stack<String> stack = new Stack<String>();
		
		double resultat = 0;
		double operande = 0;
		double operande3 = 0;
		String tmp = "";
		
		System.out.println(expresion);
		for(int i = 0; i < expresion.length(); i++){
			if(expresion.charAt(i) == ')'){
				if(stack.peek().matches("\\s")) stack.pop();
				operande = Double.parseDouble(stack.pop());
				System.out.println("operande:" + operande);
				// remove whitespace if present
				if(stack.peek().matches("\\s")) stack.pop();
				resultat = Double.parseDouble(stack.pop());
				System.out.println("resultat:" + resultat);
				if(stack.peek().matches("\\s")) stack.pop();
				if(stack.peek().matches("^-?\\d+\\.?\\d*")) operande3 = Double.parseDouble(stack.pop());
				if(stack.peek().matches("\\s")) stack.pop();
				switch (stack.peek()){
					case "+": resultat += operande + operande3;
								operande3 = 0;
								break;
					case "-": resultat -= operande;
								break;
					case "*": resultat *= operande;
								break;
					case "/": resultat /= operande;
								break;
				}
				stack.pop();
				stack.pop();
				System.out.println("resultat pushé:" + resultat);
				System.out.println("----");
				stack.push(String.valueOf(resultat));
				stack.push(" "); // for safety, otherwise next operand could be concatenated 
				
			}
				//"^-?\\d+$"
			else if(
					String.valueOf(expresion.charAt(i)).matches("[\\d\\.]") && 
					stack.peek().matches("^-?\\d+\\.?\\d*$")
					)
			{
				tmp = stack.pop() + String.valueOf(expresion.charAt(i));
				stack.push(tmp);
			}
			else
				stack.push(String.valueOf(expresion.charAt(i)));	
		}		
	return resultat;
}			
/*
 * cette fonction v�rifier si une expression est �quilibree 
 * i.e. toutes parenth�se ouverte � une parenth�se fermante
 * N.B: une seule pile peut �tre utilis�e 
 * return true si equilibree, false sionon
 */

static public boolean isEquilibre(String expresion){		
	Stack<String> stack = new Stack<String>();

	for(int i = 0; i < expresion.length(); i++){
		if(expresion.charAt(i) == '(')
			stack.push("(");
		else if(expresion.charAt(i) == ')' && stack.empty())
			return false;
		else if(expresion.charAt(i) == ')' && stack.peek() == "(")
			stack.pop();
		
	}
	
	return stack.empty();
}

/*
 * fonction transforme une expresion (String) lisp en tokens (Vector<String>)
 */
static public Vector<String> getTokens(String expresion){
	
	Vector<String> vectorOfTokens=new Vector<String>();
	int lenght=expresion.length();
	String token="",number="";
	//==
	for(int i=lenght-1;i>=0;i--) {
		//
		token=String.valueOf(expresion.charAt(i));
		if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")){
			if(!number.isEmpty()){
				vectorOfTokens.addElement(number);
				number="";	
			}
			vectorOfTokens.addElement(token);
		}else if(token.equals(")")|| token.equals("(") ){
			if(!number.isEmpty()){
				vectorOfTokens.addElement(number);
				number="";	
			}	
			vectorOfTokens.addElement(token);				
		}else if(token.equals(" ")){
			if(!number.isEmpty()){
				vectorOfTokens.addElement(number);
				number="";	
			}	
		}else if(!token.equals(" ")){
			number=token+number;		
		}
	}
	// invert vectorOfTokens;
	 Collections.reverse(vectorOfTokens);
	
	return vectorOfTokens;
}


}
