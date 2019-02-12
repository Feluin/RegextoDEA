import java.util.HashSet;
import java.util.Set;

public abstract class SyntaxNode
{
    public Boolean nullable;
    public final Set<Integer> firstpos = new HashSet<>();
    public final Set<Integer> lastpos = new HashSet<>();

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !obj.getClass().equals(this.getClass())) {
            return super.equals(obj);
        } else {
            Visitable otherVis = (Visitable) obj;
            return this.equals(otherVis);
        }
    }

    public abstract boolean equals(Visitable other);
}