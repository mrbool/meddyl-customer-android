package com.gtsoft.meddyl.customer.system.gtsoft;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by TriarhGX on 5/15/14.
 */
public class LocationFinder implements LocationListener
{
    private Double _latitude=0.0;
    private Double _longitude=0.0;
    private Boolean _has_gps_service=false;

    public LocationFinder(Context context)
    {
        LocationManager locationManager;
        Criteria criteria;
        String provider;

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);

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
                //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                Location location = locationManager.getLastKnownLocation(provider);
                if(location != null)
                {
                    _latitude = location.getLatitude();
                    _longitude = location.getLongitude();
                }
            }
        }
        // the last known location of this provider



        //provider = locationManager.getBestProvider(criteria, true);
        //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 0, this);
        //setMostRecentLocation(locationManager.getLastKnownLocation(provider));
    }

    private void setMostRecentLocation(Location lastKnownLocation)
    {

    }

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

    /*
     * (non-Javadoc)
     *
     * @see
     * android.location.LocationListener#onLocationChanged(android.location.
     * Location)
     */
    @Override
    public void onLocationChanged(Location location) {

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * android.location.LocationListener#onProviderDisabled(java.lang.String)
     */
    @Override
    public void onProviderDisabled(String arg0) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * android.location.LocationListener#onProviderEnabled(java.lang.String)
     */
    @Override
    public void onProviderEnabled(String arg0) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see android.location.LocationListener#onStatusChanged(java.lang.String,
     * int, android.os.Bundle)
     */
    @Override
    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
        // TODO Auto-generated method stub

    }

}