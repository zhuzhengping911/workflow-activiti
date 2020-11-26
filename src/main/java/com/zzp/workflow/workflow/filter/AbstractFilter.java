package com.zzp.workflow.workflow.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Zhengping Zhu
 * @Date: 2020/11/26 5:20 PM
 * @description: com.zzp.workflow.workflow.filter
 * @version: v1.0
 */
public abstract class AbstractFilter implements Filter {

    public static final String PARAM_NAME_EXCLUSIONS = "exclusions";

    private Set<String> excludesPattern;

    protected String contextPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        {
            String exclusions = filterConfig.getInitParameter(PARAM_NAME_EXCLUSIONS);
            if (exclusions != null && exclusions.trim().length() != 0) {
                excludesPattern = new HashSet<String>(Arrays.asList(exclusions.split("\\s*,\\s*")));
            }
        }
        this.contextPath = getContextPath(filterConfig.getServletContext());

    }

    public boolean isExclusion(String requestURI) {
        if (excludesPattern == null || requestURI == null) {
            return false;
        }

        if (contextPath != null && requestURI.startsWith(contextPath)) {
            requestURI = requestURI.substring(contextPath.length());
            if (!requestURI.startsWith("/")) {
                requestURI = "/" + requestURI;
            }
        }

        for (String pattern : excludesPattern) {
            if (matches(pattern, requestURI)) {
                return true;
            }
        }

        return false;
    }

    private boolean matches(String pattern, String source) {
        if (pattern == null || source == null) {
            return false;
        }
        pattern = pattern.trim();
        source = source.trim();
        if (pattern.endsWith("*")) {
            // pattern: /druid* source:/druid/index.html
            int length = pattern.length() - 1;
            if (source.length() >= length) {
                if (pattern.substring(0, length).equals(
                        source.substring(0, length))) {
                    return true;
                }
            }
        } else if (pattern.startsWith("*")) {
            // pattern: *.html source:/xx/xx.html
            int length = pattern.length() - 1;
            if (source.length() >= length
                    && source.endsWith(pattern.substring(1))) {
                return true;
            }
        } else if (pattern.contains("*")) {
            // pattern:  /druid/*/index.html source:/druid/admin/index.html
            int start = pattern.indexOf("*");
            int end = pattern.lastIndexOf("*");
            if (source.startsWith(pattern.substring(0, start))
                    && source.endsWith(pattern.substring(end + 1))) {
                return true;
            }
        } else {
            // pattern: /druid/index.html source:/druid/index.html
            if (pattern.equals(source)) {
                return true;
            }
        }
        return false;
    }

    private String getContextPath(ServletContext context) {
        String contextPath = context.getContextPath();

        if (contextPath == null || contextPath.length() == 0) {
            contextPath = "/";
        }

        return contextPath;
    }
}
