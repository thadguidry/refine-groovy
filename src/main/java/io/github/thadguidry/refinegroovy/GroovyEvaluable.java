package io.github.thadguidry.refinegroovy;

import java.util.Properties;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.codehaus.groovy.control.CompilerConfiguration;

import com.google.refine.expr.EvalError;
import com.google.refine.expr.Evaluable;
import com.google.refine.expr.HasFields;
import com.google.refine.expr.LanguageSpecificParser;
import com.google.refine.expr.ParsingException;

/**
 * A parser for expressions written in Groovy.
 * Based on {@link com.google.refine.jython.JythonEvaluable}
 */
public class GroovyEvaluable implements Evaluable{

    private final String scriptText;

    public static LanguageSpecificParser createParser() {
        return new LanguageSpecificParser() {

            @Override
            public Evaluable parse(String scriptText) throws ParsingException {
                return new GroovyEvaluable(scriptText);
            }
        };
    }

    public GroovyEvaluable(String scriptText) {
        this.scriptText = scriptText;
    }

    @Override
    public Object evaluate(Properties bindings) {
        try {
          
            // Set data to be shared between OpenRefine Java <--> Groovy) for our Objects [value, cell, cells, row, and rowIndex].
            // Additionally, in order for the script (s) to properly access those Java objects also having fields,
            // like cell, cells, row, we first have to convert them in GroovyHasFieldsWrapper.java
            
            groovy.lang.Binding sharedData = new Binding();
            sharedData.setProperty("value", bindings.get("value"));
            sharedData.setProperty("cell", new HasFieldsWrapper((HasFields) bindings.get("cell"), bindings));
            sharedData.setProperty("cells", new HasFieldsWrapper((HasFields) bindings.get("cells"), bindings));
            sharedData.setProperty("row", new HasFieldsWrapper((HasFields) bindings.get("row"), bindings));
            sharedData.setProperty("rowIndex", bindings.get("rowIndex"));
            
            // There are many Compiler options, see Groovy Docs
            CompilerConfiguration config = new CompilerConfiguration();
            // config.setDebug(true);

            GroovyShell groovyShell = new GroovyShell(sharedData, config);
            // The GroovyShell, rather than Groovy Eval, is used to evaluate the Groovy script (s)
            // and return the result since GroovyShell supports caching.
            // NOTE: by using Binding, we shared data between OpenRefine and the script.
            // NOTE: A groovy.lang.Script is always compiled into a class.
            // The Groovy compiler will compile the class for us, with the body of the script copied into a run method.
            return groovyShell.evaluate(scriptText); 
                    
            } catch (Exception e) {
            return new EvalError(e.getMessage());
        }
    }
}



             
