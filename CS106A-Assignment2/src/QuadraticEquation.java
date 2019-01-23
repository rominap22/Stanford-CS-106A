/* Name: Romina Parimi
 * Section Leader: Belce Dogru
 * Purpose: This program will find the real roots of a quadratic equation
 * (ax^2 + bx + c = 0) by using the quadratic formula: x = (-b +/- (b^2 - 4ac))/(2a)
 * The program will take user input for the values of a, b, and c. It will then
 * compute the real roots, if any, when the quadratic formula is computed in terms
 * of a, b, and c.
 * Citations: The Art and Science of Java
 */
import acm.program.*;

public class QuadraticEquation extends ConsoleProgram {
	public void run() {
		println("CS 106A Quadratic Solver!");
		//reads user input for a, b, and c
		int a = readInt("Enter a: ");
		int b = readInt("Enter b: ");
		int c = readInt("Enter c: ");
		
		//discriminant(b^2 - 4ac)
		double d = Math.sqrt((b*b)-(4*a*c));
		//quadratic formula using +
		double x = (-b + d)/(2*a);
		//quadratic formula using -
		double x1 = (-b - d)/(2*a);
		
		//discriminant is postiive
		if(d > 0) {
			print("Two roots: ");
			if(x == x1) {
				print(x);
			} else {
				print(x + " and " + x1);
			}
		}
		//discriminant is zero
		else if(d == 0) {
			print("One root: ");
			if(x == x1) {
				print(x);
			} else {
				print(x + " and " + x1);
			}
		}
		//discriminant is negative
		else {
			println("No real roots");
		}
	}
}