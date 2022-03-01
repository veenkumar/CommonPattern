package com.commonpattern.utils.application

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.biteMe.extn.toast
import com.commonpattern.utils.application.CommonUtils.isOnline
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnSuccessListener


object PermissionHelper : GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, OnSuccessListener<Location> {
    private var isLocation=false
    private var googleApiClient: GoogleApiClient?=null
    private var locationRequest: LocationRequest? = null
    private var locationCallback: LocationCallback? = null
    private var mFusedLocationClient: FusedLocationProviderClient? = null

    private var context:Activity?=null
    private var onResultLaucher: ActivityResultLauncher<IntentSenderRequest>?=null
    private var onPermissionLaucher : ActivityResultLauncher<Array<String>>?=null
    private var listner: onLocationListner?=null

    fun startLocation(context: Activity, onResultLaucher: ActivityResultLauncher<IntentSenderRequest>?,
        onPermissionLaucher: ActivityResultLauncher<Array<String>>?, listner: onLocationListner?
    ) {
        PermissionHelper.context =context
        PermissionHelper.onResultLaucher =onResultLaucher
        PermissionHelper.onPermissionLaucher =onPermissionLaucher
        PermissionHelper.listner =listner
        isLocation =false

        if(checkPermission()) startLocationFunctioning()
    }


    private fun checkPermission() : Boolean {
        var ret=true
        if (ActivityCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                context!!,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED) {
            ret=false
            onPermissionLaucher?.launch(
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            )
        }
        return ret
    }

    private fun startLocationFunctioning() {
        if (!isOnline(context!!)) { (context)?.toast("No Internet Found")
        } else {
            if (isGPlayServicesOK()) {
                buildGoogleApiClient()
            }
        }
    }



    private fun isGPlayServicesOK(): Boolean {
        val isAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context)
        when {
            isAvailable == ConnectionResult.SUCCESS -> { return true }
            GoogleApiAvailability.getInstance().isUserResolvableError(isAvailable) -> { GoogleApiAvailability.getInstance().getErrorDialog(
                context,
                isAvailable,
                1001
            ).show() }
            else -> { Toast.makeText(context, "Can't Connect to Store", Toast.LENGTH_SHORT).show() }
        }
        return false
    }




    private fun buildGoogleApiClient(){
        try {
            googleApiClient = GoogleApiClient.Builder(context)
                    .addApi(LocationServices.API)
                    .enableAutoManage(context as FragmentActivity, 0, this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build()
            googleApiClient?.connect()
        }catch (e: java.lang.Exception){
            e.printStackTrace()
        }
    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }

    override fun onConnected(p0: Bundle?) {
        if(!isLocation) {
            val builder = LocationSettingsRequest.Builder().addLocationRequest(createLocationRequest()!!)
            builder.setAlwaysShow(true)
            val client = LocationServices.getSettingsClient(context)
            val task = client.checkLocationSettings(builder.build())
            task.addOnSuccessListener { loadCurrentLoc() }
            task.addOnFailureListener { e ->
                if (e is ResolvableApiException) {
                    val intentSenderRequest: IntentSenderRequest =
                        IntentSenderRequest.Builder(e.resolution).build()
                    onResultLaucher?.launch(intentSenderRequest)
                }
            }
        }
    }







    override fun onConnectionSuspended(p0: Int) {
    }


    private fun  createLocationRequest() : LocationRequest? {
        locationRequest = LocationRequest.create()
        locationRequest!!.interval = 2000
        locationRequest!!.fastestInterval = 10 * 1000.toLong()
        locationRequest!!.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        return locationRequest
    }

    @SuppressLint("MissingPermission")
    fun loadCurrentLoc() {
        try {
            locationCallback =object: LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    for (location in locationResult.locations) {
                        if (location != null) {
                            if(!isLocation) {
                                mFusedLocationClient?.removeLocationUpdates(locationCallback)
                                locationCallBack(location)
                            }
                        }
                    }
                }
            }
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
            mFusedLocationClient!!.lastLocation.addOnSuccessListener(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun locationCallBack(location: Location?) {
        mFusedLocationClient?.removeLocationUpdates(locationCallback)
        isLocation = true
        listner?.onLocation(location)
    }



    @SuppressLint("MissingPermission")
    override fun onSuccess(p0: Location?) {
        if(p0!=null) locationCallBack(p0)
        else mFusedLocationClient!!.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
    }



    public interface onLocationListner {
        fun onLocation(location: Location?)
    }


}