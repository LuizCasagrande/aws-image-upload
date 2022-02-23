package com.luizcasagrande.awsimageupload.datastore;

import com.luizcasagrande.awsimageupload.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {

    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.fromString("985605d6-1ea5-4417-89ce-6f10da8f99b6"), "luizcasagrande", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("e3d3eade-1379-4def-9b62-6932f1cf01fc"), "osmarcasagrande", null));
    }

    public List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }
}
