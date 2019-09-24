package com.example.dailyfeed;

public class ListItems {
   private String mHeading;
   private String mDescription;
   private String mImageURL;
   private String mDetailURL;
   private String mPublishedAt;

   public ListItems(String mHeading, String mDescription, String mImageURL, String mDetailURL,String mPublishedAt) {
      this.mHeading = mHeading;
      this.mDescription = mDescription;
      this.mImageURL = mImageURL;
      this.mDetailURL = mDetailURL;
      this.mPublishedAt = mPublishedAt;
   }

   public String getmHeading() {
      return mHeading;
   }

   public String getmDescription() {
      return mDescription;
   }

   public String getmImageURL() {
      return mImageURL;
   }

   public String getmDetailURL() {
      return mDetailURL;
   }
   public String getmPublishedAt(){
      return mPublishedAt;
   }

}
