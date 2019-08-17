package jfluentvalidation.validators.rulefor.uri;

import java.net.URI;

class Media {

    private URI contentLocation;

    public Media(URI contentLocation) {
        this.contentLocation = contentLocation;
    }

    public URI getContentLocation() {
        return contentLocation;
    }

}
