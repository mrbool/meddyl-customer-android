package com.gtsoft.meddyl.customer.system.gtsoft;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class LocationFinder implements LocationListener
{
    LocationManager locationManager;

    private Double _latitude=0.0;
    private Double _longitude=0.0;
    private Boolean _has_gps_service=false;

    public LocationFinder(Context context)
    {
        Criteria criteria;
        String provider;

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setCostAllowed(false);

        // get the best provider depending on the criteria
        provider = locationManager.getBestProvider(criteria, false);

        // getting GPS status
        Boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        // getting network status
        Boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!isGPSEnabled || provider == null)
        {
            _has_gps_service = false;
        } else
        {
            _has_gps_service = true;

            if (locationManager != null)
            {
                Location location = locationManager.getLastKnownLocation(provider);
                if(location != null)
                {
                    _latitude = location.getLatitude();
                    _longitude = location.getLongitude();
                }
                else
                {
                    // make request for new location
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                    //locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, this, null);
                }
            }
        }
    }

    @Override
    public void onLocationChanged(Location location)
    {
        if(location != null)
        {
            _latitude = location.getLatitude();
            _longitude = location.getLongitude();
        }

        locationManager.removeUpdates(this);
    }

    @Override
    public void onProviderDisabled(String arg0)
    {
    }

    @Override
    public void onProviderEnabled(String arg0)
    {
    }

    @Override
    public void onStatusChanged(String arg0, int arg1, Bundle arg2)
    {
    }


    /* properties */
    public Boolean has_gps_service()
    {
        return _has_gps_service;
    }

    public Double getLatitude()
    {
        return _latitude;
    }

    public Double getLongitude()
    {
        return _longitude;
    }
}