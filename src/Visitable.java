interface Visitable
{
    void accept(Visitor visitor);

    boolean equals(Visitable obj);
}