package de.dhbw.inf17a.regextodea;

public interface Visitable
{
   void accept(Visitor visitor);

   boolean equals(Visitable obj);
}