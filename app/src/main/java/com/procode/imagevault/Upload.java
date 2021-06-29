/*
CECS 453-01 Final Project
Authors: Nikko Chan & Khai Trinh
Due Date: July 1, 2021
Description: This class stores the name
and url of the selected image.
*/

package com.procode.imagevault;

public class Upload {
    private String mName, mImageUrl;

    public Upload() {}

    public Upload(String name, String imageUrl) {
        if (name.trim().equals("")) name = "Untitled";

        mName = name;
        mImageUrl = imageUrl;
    }

    public String getName() { return mName; }

    public void setName(String name) { mName = name; }

    public String getImageUrl() { return mImageUrl; }

    public void setImageUrl(String imageUrl) { mImageUrl = imageUrl; }
}
