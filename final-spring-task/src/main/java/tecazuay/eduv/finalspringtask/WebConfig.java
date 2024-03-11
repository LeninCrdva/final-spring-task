package tecazuay.eduv.finalspringtask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tecazuay.eduv.finalspringtask.interceptor.AuthInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/photo-public/**")
                .excludePathPatterns("/css/**") // Excluir CSS
                .excludePathPatterns("/js/**") // Excluir JavaScript
                .excludePathPatterns("/images/**"); // Excluir imágenes;
    }
}
