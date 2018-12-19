package com.ats.apixpdq.impl.v2;


import ca.uhn.hl7v2.validation.Rule;
import ca.uhn.hl7v2.validation.impl.RegexPrimitiveRule;
import ca.uhn.hl7v2.validation.impl.RuleBinding;
import ca.uhn.hl7v2.validation.impl.SizeRule;
import ca.uhn.hl7v2.validation.impl.TrimLeadingWhitespace;
import ca.uhn.hl7v2.validation.impl.ValidationContextImpl;


/**
 * A <code>ValidationContext</code> with a default set of rules initially defined.
 * This can be used as-is for a reasonable level of primitive type validation.
 *
 * @author <a href="mailto:bryan.tripp@uhn.on.ca">Bryan Tripp</a>
 * @version $Revision: 1.1 $ updated on $Date: 2005/06/14 20:13:16 $ by $Author: bryan_tripp $
 */
public class MessageValidation extends ValidationContextImpl {
    public MessageValidation() {
        Rule trim = new TrimLeadingWhitespace();
        getPrimitiveRuleBindings().add(new RuleBinding("*", "FT", trim));
        getPrimitiveRuleBindings().add(new RuleBinding("*", "ST", trim));
        getPrimitiveRuleBindings().add(new RuleBinding("*", "TX", trim));

        Rule size200 = new SizeRule(200);
        Rule size32000 = new SizeRule(32000);
        getPrimitiveRuleBindings().add(new RuleBinding("*", "FT", size32000));
        getPrimitiveRuleBindings().add(new RuleBinding("*", "ID", size200));
        getPrimitiveRuleBindings().add(new RuleBinding("*", "IS", size200));

        Rule nonNegativeInteger = new RegexPrimitiveRule("\\d*", "");
        getPrimitiveRuleBindings().add(new RuleBinding("*", "SI", nonNegativeInteger));

        Rule number = new RegexPrimitiveRule("(\\+|\\-)?\\d*\\.?\\d*", "");
        getPrimitiveRuleBindings().add(new RuleBinding("*", "NM", number));

        Rule telephoneNumber
            = new RegexPrimitiveRule("((\\d{11})?((\\d{7,8})?(\\d{4}?\\d{3})-(\\d{7,8})?(\\d{4}?\\d{3})-(\\d{7,8})-(\\d{4}?\\d{3}?\\d{2}?\\d{1})?(\\d{7,8})-(\\d{4}?\\d{3}?\\d{2}?\\d{1}))?)",
                "Version 2.4 Section 2.9.45");
        //getPrimitiveRuleBindings().add(new RuleBinding("*", "TN", telephoneNumber));

        String datePattern = "(\\d{4}([01]\\d(\\d{2})?)?)?"; //YYYY[MM[DD]]
        Rule date = new RegexPrimitiveRule(datePattern, "Version 2.5 Section 2.16.24");
        getPrimitiveRuleBindings().add(new RuleBinding("*", "DT", date));

        String timePattern  //HH[MM[SS[.S[S[S[S]]]]]][+/-ZZZZ]
            = "([012]\\d([0-5]\\d([0-5]\\d(\\.\\d(\\d(\\d(\\d)?)?)?)?)?)?)?([\\+\\-]\\d{4})?";
        Rule time = new RegexPrimitiveRule(timePattern, "Version 2.5 Section 2.16.79");
        getPrimitiveRuleBindings().add(new RuleBinding("*", "TM", time));

        String datetimePattern
            = "(\\d{4}([01]\\d(\\d{2}([012]\\d([0-5]\\d([0-5]\\d(\\.\\d(\\d(\\d(\\d)?)?)?)?)?)?)?)?)?)?([\\+\\-]\\d{4})?";
        Rule datetime = new RegexPrimitiveRule(datetimePattern, "Version 2.5 Section 2.16.25");
        getPrimitiveRuleBindings().add(new RuleBinding("*", "TSComponentOne", datetime));
        getPrimitiveRuleBindings().add(new RuleBinding("*", "DTM", datetime));
    }
}
