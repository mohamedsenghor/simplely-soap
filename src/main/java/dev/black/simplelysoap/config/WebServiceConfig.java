package dev.black.simplelysoap.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class WebServiceConfig extends WsConfigurerAdapter {

    private static final String NAMESPACE_URI = "http://www.black.dev/banque";

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();

        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        // Let's expose the SOAP service under the /soap path
        return new ServletRegistrationBean(servlet, "/soap/*"); // The SOAP endpoint path
    }

    @Bean(name = "banque") // the name property is used to access the WSDL at http://localhost:8085/soap/banque.wsdl
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema banqueSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("BanquePort");
        wsdl11Definition.setLocationUri("/soap");
        wsdl11Definition.setTargetNamespace(NAMESPACE_URI);
        wsdl11Definition.setSchema(banqueSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema banqueSchema() {
        return new SimpleXsdSchema(new ClassPathResource("wsdl/schema.xsd"));
    }
}
