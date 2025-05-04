package pt.jnation.tv.resource;

import io.quarkus.qute.TemplateInstance;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

class ExceptionMappers {
    @ServerExceptionMapper
    public RestResponse<TemplateInstance> mapException(Exception e) {
        return RestResponse.ok(ErrorTemplate.error(e.getMessage()));
    }
}
