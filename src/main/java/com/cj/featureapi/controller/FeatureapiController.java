package com.cj.featureapi.controller;

import com.cj.featureapi.json.FeatureRequest;
import com.cj.featureapi.json.FeatureResponse;
import com.cj.featureapi.service.FeatureapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/feature")
public class FeatureapiController {
    @Autowired
    private FeatureapiService featureapiService;

    @GetMapping
    public FeatureResponse getFeature(
            @RequestParam String email,
            @RequestParam String featureName) {
        return new FeatureResponse(featureapiService.checkFeatureAccess(email, featureName));
    }

    @PostMapping
    public void setFeature(@RequestBody FeatureRequest featureRequest, HttpServletResponse response) {
        boolean successful = featureapiService.setClientFeature(featureRequest);
        if (!successful) response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        return;
    }
}
