package de.dhbw.inf17a.regextodea;

public interface Visitable
{
   public void accept(Visitor visitor);

   public boolean equals(Visitable obj);
}