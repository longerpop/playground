package com.mblinn.oo.tinyweb;

import java.util.List;
import java.util.Map;

public class StrategyView implements View
{

    private final RenderingStrategy viewRenderer;

    public StrategyView(final RenderingStrategy viewRenderer)
    {
        this.viewRenderer = viewRenderer;
    }

    @Override
    public String render(final Map<String, List<String>> model) throws RenderingException
    {
        try {
            return viewRenderer.renderView(model);
        } catch (final Throwable t) {
            throw new RenderingException(t);
        }
    }

}
