package fic.riws.eli5riws.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${app.api.base-url}")
    private String apiBaseUrl;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**/*.html", "/**/*.js", "/**/*.css",
                "/**/*.jpg", "/**/*.png", "/**/*.ttf",
                "/**/*.woff", "/**/*.woff2")
                .setCachePeriod(31536000)
                .addResourceLocations("classpath:/public/");

        registry.addResourceHandler("/", "/**")
                .setCachePeriod(31536000)
                .addResourceLocations("classpath:/public/index.html")
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        if (resourcePath.startsWith(apiBaseUrl)) {
                            return null;
                        }
                        return location.exists() && location.isReadable() ? location : null;
                    }
                });

    }
}