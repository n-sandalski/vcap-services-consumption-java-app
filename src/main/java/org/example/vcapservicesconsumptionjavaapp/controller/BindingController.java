package org.example.vcapservicesconsumptionjavaapp.controller;

import com.sap.cloud.environment.servicebinding.SapServiceOperatorLayeredServiceBindingAccessor;
import com.sap.cloud.environment.servicebinding.api.ServiceBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Function;

@RestController
public class BindingController {

    private static final Logger logger = LoggerFactory.getLogger(BindingController.class);

    @GetMapping("/log-all-bindings")
    public String logBindings() {
        try {
            Function<String, String> readPath = System::getenv;
            SapServiceOperatorLayeredServiceBindingAccessor sapServiceOperatorLayeredServiceBindingAccessor = new SapServiceOperatorLayeredServiceBindingAccessor(readPath, SapServiceOperatorLayeredServiceBindingAccessor.DEFAULT_PARSING_STRATEGIES);
            List<ServiceBinding> allServiceBindings = sapServiceOperatorLayeredServiceBindingAccessor.getServiceBindings();
//
//            // filter for a specific binding
//            ServiceBinding xsuaaBinding = allServiceBindings.stream()
//                    .filter(binding -> "xsuaa".equalsIgnoreCase(binding.getServiceName().orElse(null)))
//                    .filter(binding -> "lite".equalsIgnoreCase(binding.getServicePlan().orElse(null)))
//                    .filter(binding -> binding.getTags().contains("tag"))
//                    .findFirst()
//                    .get();

            logger.info("Current Path:{}", System.getProperty("user.dir"));
            logger.info("Bindings size: {}\n", allServiceBindings.size());

            allServiceBindings.forEach((binding) -> {
                logger.info("Service Binding: {}", binding.toString());
                logger.info("Service Binding Name: {}", binding.getName().orElse(""));
                logger.info("Service Binding Service Name: {}", binding.getServiceName().orElse(""));
                logger.info("Service Binding Service Plan: {}", binding.getServicePlan().orElse(""));
                logger.info("Service Binding Service Credentials:...");
                binding.getCredentials().forEach((key, value) -> {
                    logger.info("Credential Name: {}", key);
                    logger.info("Credential Value: {}", value);
                });
            });

            return "HELLO WORLD!";
        } catch (Exception e) {
            logger.error("Failed to retrieve service bindings", e);
            return "An error occurred while retrieving bindings. Check logs for details.";
        }
    }
}
