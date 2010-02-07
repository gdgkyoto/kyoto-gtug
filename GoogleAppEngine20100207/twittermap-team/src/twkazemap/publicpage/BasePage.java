/*
 * WicketExamplePage.java
 *
 * Created on 2010/02/07, 11:10
 */
 
package twkazemap.publicpage;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.markup.html.resources.StyleSheetReference;
import org.apache.wicket.util.string.Strings;

/** 
 *
 * @author masanao
 * @version 
 */

public class BasePage extends WebPage {

    /**
     * Constructor
     */
    public BasePage() {
        this(null);
    }
    
    /**
     * Construct.
     * @param model
     */
    public BasePage(IModel model) {
        super(model);
        final String packageName = getClass().getPackage().getName();
        add(new HeaderPanel("mainNavigation", Strings.afterLast(packageName, '.')));
        add(new StyleSheetReference("stylesheet", BasePage.class, "style.css"));
    }
}
