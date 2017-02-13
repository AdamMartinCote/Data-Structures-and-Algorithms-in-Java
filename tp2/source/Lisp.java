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
static public  double solve(String expresion){
		Stack<String> stack = new Stack<String>();
		
		
	
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
		else if(expresion.charAt(i) == ')' && stack.peek() == "(")
			stack.pop();
		else if(expresion.charAt(i) == ')' && stack.peek() != "(")
			return false;
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
