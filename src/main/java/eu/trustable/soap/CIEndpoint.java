package eu.trustable.soap;

import eu.trustable.soap.sample.ci.GetActiveCIByURLRequest;
import eu.trustable.soap.sample.ci.GetActiveCIByURLResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CIEndpoint {

    public static final String NAMESPACE_URI = "http://trustable.eu/soap/sample/ci";

    private CIRepository ciRepository;

    @Autowired
    public CIEndpoint(CIRepository ciRepository) {
        this.ciRepository = ciRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getActiveCIByURLRequest")
    @ResponsePayload
    public GetActiveCIByURLResponse getActiveCIByURL(@RequestPayload GetActiveCIByURLRequest request) {
        GetActiveCIByURLResponse response = new GetActiveCIByURLResponse();
        if( request.getUrl() != null ) {
            response.setCi(ciRepository.findCiByUrl((request.getUrl().trim().toLowerCase())));
        }
        return response;
    }
}
