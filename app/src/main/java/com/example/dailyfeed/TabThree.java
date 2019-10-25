package com.example.dailyfeed;

import android.annotation.SuppressLint;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.io.IOException;
import java.util.List;

public class TabThree extends Fragment {
    TextView about, tp, email, cords, add, loca;
    ImageView imgview;
    LocationCallback callback;
    FusedLocationProviderClient client;
    Location location;
CollapsingToolbarLayout collapsingToolbarLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_3, container, false);
//        about = v.findViewById(R.id.aboutTextView);
        /*tp = v.findViewById(R.id.someText);
        email = v.findViewById(R.id.email);
        cords = v.findViewById(R.id.TextViewCoords);
        add = v.findViewById(R.id.TextViewAddr);
        loca = v.findViewById(R.id.locationTextView);*/
        imgview = v.findViewById(R.id.img);
        client = LocationServices.getFusedLocationProviderClient(getActivity());
        callback = new LocationCallback() {

            @Override
            public void onLocationResult(LocationResult locationResult) {
                location = locationResult.getLocations().get(0);
                Log.i("DEV", "Location => " + location.getLatitude() + " , " + location.getLongitude());
                double lat = location.getLatitude();
                double longi = location.getLongitude();
                cords.setText("Location:\n\n" + lat + " , " + longi);

                convertAddress(location.getLatitude(), location.getLongitude());
                super.onLocationResult(locationResult);
            }
        };
        LocationRequest request = new LocationRequest();
        request.setFastestInterval(3000);
        request.setInterval(5000);
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        client.requestLocationUpdates(request, callback, Looper.myLooper());

        return v;
    }

    private void convertAddress(double latitude, double longitude) {

        Geocoder geo = new Geocoder(getActivity());


        try {
            List<Address> ads = geo.getFromLocation(latitude, longitude, 1);
            Address ad = ads.get(0);


            Log.i("DEV", "Address => " + ad.getAddressLine(0));
            Log.i("DEV", "State => " + ad.getAdminArea());
            Log.i("DEV", "Country => " + ad.getCountryName());
            Log.i("DEV", "Pin => " + ad.getPostalCode());
            Log.i("DEV", "Locality => " + ad.getLocality());

            add.setText("Address:\n\n" + ad.getAddressLine(0));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


