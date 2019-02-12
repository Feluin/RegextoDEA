package de.dhbw.inf17a.regextodea.parser;

import de.dhbw.inf17a.regextodea.Visitable;
import de.dhbw.inf17a.regextodea.treenodes.BinOpNode;
import de.dhbw.inf17a.regextodea.treenodes.OperandNode;
import de.dhbw.inf17a.regextodea.treenodes.UnaryOpNode;
/**
 * Matrikelnummer: 9176689
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TopDownParser
{
    private static String concat = "°";
    private static String endSymbol = "#";
    private static String or = "|";
    private static String kleen = "*";
    private static String pos = "+";
    private static String opt = "?";
    private static String closeBrack = ")";
    private static String openBrack = "(";
    private static List<String> multOps = Arrays.asList(kleen, pos, opt);
    private static List<String> alphaNums = getAlphaNums();

    private static List<String> getAlphaNums()
    {
        List<Character> ret = new LinkedList<>();
        IntStream.range(48, 58).mapToObj(i -> (char) i).forEach(ret::add);
        IntStream.range(65, 91).mapToObj(i -> (char) i).forEach(ret::add);
        IntStream.range(97, 123).mapToObj(i -> (char) i).forEach(ret::add);
        return ret.stream().map(Objects::toString).collect(Collectors.toList());
    }

    private int curIndex = 0;
    private String regEx;

    public TopDownParser(String regEx,
                         boolean hasEndSymbol)
    {
        this.regEx = String.copyValueOf(regEx.toCharArray());
        if (!hasEndSymbol)
        {
            this.regEx = this.regEx + endSymbol;
        }
    }

    public TopDownParser(String regEx)
    {
        this(regEx, regEx.endsWith("#"));
    }

    public Visitable getTreeFromRegex()
    {
        int regexLength = regEx.length();
        if (regexLength < 1 || this.regEx.lastIndexOf(endSymbol) != regexLength - 1)
        {
            throw getIllArgException("Expression must end with " + getNameAndValue("endSymbol", endSymbol) + "(even is emtpy)");
        } else if (regEx.equals(endSymbol))
        {
            return new OperandNode(endSymbol);
        } else if (getFirstFromRegex().equals(openBrack))
        {
            Visitable regExp = regExpWrapper();
            Visitable leaf = new OperandNode(endSymbol);
            Visitable ret = new BinOpNode(concat, regExp, leaf);
            removeFirstFromRegex();
            if (this.regEx.length() > 0)
            {
                throw getIllArgException("Fail in compiler, regEx should have no more symbols");
            }
            return ret;
        } else
        {
            throw getIllArgException("Expression needs to start with " + getNameAndValue("opening brackets", openBrack));
        }
    }

    private Visitable regExpWrapper()
    {
        removeFirstFromRegex();
        Visitable ret = regExp();
        if (!getFirstFromRegex().equals(closeBrack))
        {
            throw getIllArgException("Expression must have " + getNameAndValue("closing brackets", closeBrack));
        }
        removeFirstFromRegex();
        return ret;
    }


    private String getNameAndValue(String name,
        String value)
    {
        return name + "('" + value + "')";
    }


    private IllegalArgumentException getIllArgException(String message)
    {
        return new IllegalArgumentException(message + " " + getEndText());
    }


    private String getEndText()
    {
        return "At Index: " + curIndex;
    }

    private Visitable regExp()
    {
        return re(term(null));
    }

    private Visitable re(Visitable param)
    {
        if (!getFirstFromRegex().equals(or))
        {
            return param;
        } else
        {
            removeFirstFromRegex();
            return re(new BinOpNode(or, param, term(null)));
        }
    }

    private void removeFirstFromRegex()
    {
        this.regEx = this.regEx.substring(1);
        curIndex++;
    }


    private String getFirstFromRegex()
    {
        return this.regEx.substring(0, 1);
    }

    private Visitable term(Visitable param)
    {
        String firstFromRegex = getFirstFromRegex();
        if (!isAlphaNum(firstFromRegex) && !firstFromRegex.startsWith(openBrack))
        {
            return param;
        } else
        {
            Visitable termRekParam = factor();
            if (param != null)
            {
                termRekParam = new BinOpNode(concat, param, termRekParam);
            }
            return term(termRekParam);
        }
    }

    private Visitable factor()
    {
        return hop(elem());
    }

    private Visitable elem()
    {
        if (getFirstFromRegex().equals(openBrack))
        {
            return regExpWrapper();
        } else
        {
            return alphaNum();
        }
    }

    private Visitable alphaNum()
    {
        String startRegEx = getFirstFromRegex();
        if (isAlphaNum(startRegEx))
        {
            removeFirstFromRegex();
            return new OperandNode(startRegEx);
        } else
        {
            throw getIllArgException("Alpha numeric symbol expected");
        }

    }

    private boolean isAlphaNum(String startRegEx)
    {
        return alphaNums.stream().anyMatch(a -> a.equals(startRegEx));
    }

    private Visitable hop(Visitable param)
    {
        String firstRegEx = getFirstFromRegex();
        if (this.regEx.length() < 1 || multOps.stream().noneMatch(firstRegEx::equals))
        {
            return param;
        } else
        {
            removeFirstFromRegex();
            return new UnaryOpNode(firstRegEx, param);
        }
    }
}
