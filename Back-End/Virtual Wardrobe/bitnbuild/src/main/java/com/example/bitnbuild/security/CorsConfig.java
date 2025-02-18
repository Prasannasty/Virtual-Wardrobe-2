// Importing necessary Spring framework annotations and classes for CORS configuration
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Marking this class as a Spring configuration class
@Configuration
public class WebMvcCorsConfig implements WebMvcConfigurer {

    /**
     * Customizes the CORS settings for the application.
     * This method is called to configure how cross-origin requests are handled.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Apply the CORS configuration to all endpoints ("/**" matches all paths)
        registry.addMapping("/**")
                // Allow cross-origin requests from this specific origin (frontend URL)
                .allowedOrigins("http://localhost:5173")
                // Specify which HTTP methods are allowed in cross-origin requests
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // Allow all headers to be sent in the request
                .allowedHeaders("*")
                // Allow cookies and other credentials (e.g., Authorization headers) to be included
                .allowCredentials(true);
    }
}
