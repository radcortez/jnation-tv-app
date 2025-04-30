package pt.jnation.tv.resource;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.Location;
import io.quarkus.qute.TemplateInstance;

@CheckedTemplate
public class ErrorTemplate {
    @Location("error.html")
    public static native TemplateInstance error(String error);
}
