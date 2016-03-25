
package org.kevoree.modeling.java2typescript.translators.expression;

import com.intellij.psi.PsiLiteralExpression;
import org.kevoree.modeling.java2typescript.context.TranslationContext;

public class LiteralTranslator {

    public static void translate(PsiLiteralExpression element, TranslationContext ctx) {
        String value = element.getText().trim();
        if (element.getType() != null && element.getType().getPresentableText().equals("char")) {
            ctx.append(value);
            ctx.append(".charCodeAt(0)");
        } else {
            if(!value.toLowerCase().equals("null") && (value.toLowerCase().endsWith("l") || value.toLowerCase().endsWith("d") || value.toLowerCase().endsWith("f"))) {
                ctx.append(value.substring(0, value.length()-1));
            } else {
                ctx.append(value);
            }
        }
    }

}
