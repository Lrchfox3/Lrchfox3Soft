/* A token consists of a position in the source and a reference to a symbol
(which contains the spelling and kind).  The kinds are constants. */

package com.gmail.lrchfox3.utilitarios.calculadora;

public class Token
{  int start, ref;

   Token(int s, int r)
   {  start = s;
      ref = r;
   }
}
