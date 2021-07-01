/*
CECS 453-01 Final Project
Authors: Nikko Chan & Khai Trinh
Due Date: July 1, 2021
Description: This class stores the information
of each uploaded image.
*/

package com.procode.imagevault.upload;

import com.google.firebase.database.Exclude;

public class Upload {
    private String mName, mImageUrl, mKey;

    public Upload() {}

    public Upload(String name, String imageUrl) {
        // Default name will be Untitled
        if (name.trim().equals("")) name = "Untitled";

        mName = name;
        mImageUrl = imageUrl;
    }

    public String getName() { return mName; }

    public void setName(String name) { mName = name; }

    public String getImageUrl() { return mImageUrl; }

    public void setImageUrl(String imageUrl) { mImageUrl = imageUrl; }

    // Do not allow key to be in the database because it is redundant
    @Exclude
    public String getKey() { return mKey; }

    @Exclude
    public void setKey(String key) { mKey = key; }
}
