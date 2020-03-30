package com.activities.galarte.data;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.activities.galarte.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            if (username!=null && password!=null) {
                LoggedInUser testUser = new LoggedInUser("1", username);
                return new Result.Success<>(testUser);
            } else {
                return new Result.Error(new Exception("Not valid login"));
            }
//            LoggedInUser fakeUser =
//                    new LoggedInUser(
//                            java.util.UUID.randomUUID().toString(),
//                            "Jane Doe");
            //return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
