package kr.co.greenuniversity.config;
import kr.co.greenuniversity.interceptor.AppInfoIntercepter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    private final AppInfo appInfo;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AppInfoIntercepter(appInfo));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uploadPath = "file:" + System.getProperty("user.dir") + "/uploads/";
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(uploadPath);
    }
}

