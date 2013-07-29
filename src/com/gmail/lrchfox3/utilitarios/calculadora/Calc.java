/*
 * Calc.java
 *
 * Created on April 29, 2007, 1:11 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.gmail.lrchfox3.utilitarios.calculadora;

/**
 *
 * @author Luis R. Chinchilla H.
 */
public class Calc {
    
    /** Creates a new instance of Calc */
    public Calc() {
    }
    
    public double calc(String expresion) throws Exception    {  
      Program program = new Program(expresion);
      Scanner scanner = new Scanner();
      Parser parser = new Parser();
      Interpreter interpreter = new Interpreter();
      program = scanner.scan(program);
      program = parser.parse(program);
      double result = interpreter.interpret(program);

      return result;
   }
}
