package com.mblinn.oo.tinyweb;

import java.util.List;
import java.util.Map;

public abstract class TemplateController implements Controller
{

    private final View view;

    public TemplateController(final View view)
    {
        this.view = view;
    }

    @Override
    public HttpResponse handleRequest(final HttpRequest request)
    {
        Integer responseCode = 200;
        String responseBody = "";

        try {
            final Map<String, List<String>> model = doRequest(request);
            responseBody = view.render(model);
        } catch (final ControllerException ce) {
            responseCode = ce.getStatusCode();
        } catch (final RenderingException re) {
            responseCode = 500;
            responseBody = "Exception while rendering.";
        } catch (final Throwable t) {
            responseCode = 500;
        }

        return HttpResponse.Builder.newBuilder().body(responseBody).responseCode(responseCode).build();
    }

    protected abstract Map<String, List<String>> doRequest(HttpRequest request) throws ControllerException;

}
