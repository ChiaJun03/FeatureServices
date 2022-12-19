package com.cj.featureapi.service;

import com.cj.featureapi.json.FeatureRequest;
import com.cj.featureapi.model.Client;
import com.cj.featureapi.model.Feature;
import com.cj.featureapi.repository.FeatureRepository;
import com.cj.featureapi.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeatureapiService {
    @Autowired
    private FeatureRepository featureRepository;
    @Autowired
    private ClientRepository clientRepository;


    public boolean checkFeatureAccess(String email, String featureName) {
        boolean checkAccess = false;
        Optional<Client> clientOpt = clientRepository.findClientByEmail(email);
        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();
            checkAccess = client.canAccess(featureName);
        }
        return checkAccess;
    }

    public boolean setClientFeature(FeatureRequest featureRequest) {
        String featureName = featureRequest.getFeatureName();
        String email = featureRequest.getEmail();
        boolean enable = featureRequest.isEnable();

        Optional<Client> clientOpt = clientRepository.findClientByEmail(email);
        if (!clientOpt.isPresent()) return false;

        Optional<Feature> featureOpt = featureRepository.findFeatureByName(featureName);
        if (!featureOpt.isPresent()) return false;

        Client client = clientOpt.get();
        Feature feature = featureOpt.get();

        // Check current client's access to the feature
        boolean currentAccess = client.canAccess(feature.getName());

        // If current access is the same with request's access, return false as access is not modified.
        //if (enable == currentAccess) {
        //    return false;
        //}

        if (enable) {
            client.getFeatures().add(feature);
        } else {
            client.getFeatures().remove(feature);
        }
        clientRepository.save(client);
        return true;
    }

}
