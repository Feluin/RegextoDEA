/**
 * @author: Raphael Lawo
 */


package de.dhbw.inf17a.regextodea.parser;

import de.dhbw.inf17a.regextodea.Visitable;

public interface ITopDownParser {
    Visitable getTreeFromRegex();
}
