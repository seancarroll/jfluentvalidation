package jfluentvalidation.validators.rulefor.url;

import java.net.URL;

class Profile {

    private  URL website;

    public Profile(URL website) {
        this.website = website;
    }

    public URL getWebsite() {
        return website;
    }
}
