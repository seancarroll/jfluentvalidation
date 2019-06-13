package jfluentvalidation.constraints.url;

import java.net.URL;

public class Profile {

    private  URL website;

    public Profile(URL website) {
        this.website = website;
    }

    public void setWebsite(URL website) {
        this.website = website;
    }

    public URL getWebsite() {
        return website;
    }
}
