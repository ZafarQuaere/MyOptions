package com.optiontown.app.view.fragment.fpo.redeem;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.optiontown.R;
import com.optiontown.app.model.login.LoginData;
import com.optiontown.app.model.redeem.SelectedPassDataForSearchFlight;
import com.optiontown.app.model.redeem.ZoneLatLongitude;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.login.LoginFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amit on 06-09-2016.
 */
public class OTDialogFragment extends DialogFragment {
    private MapView mapView;
    private GoogleMap map;
    private SelectedPassDataForSearchFlight selectedPassDataForSearchFlight;
    private final int TYPE_DIALOG_TRAVEL_FLEXIBILITY = 1;
    private final int TYPE_DIALOG_TRAVEL_ZONE = 2;
    private final int TYPE_DIALOG_ADVANCE_BOOKING = 3;
    private Bundle savedInstanceState;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        this.savedInstanceState = savedInstanceState;
        final Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.setContentView(R.layout.fragment_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();

        try
        {
            selectedPassDataForSearchFlight = ((SelectedPassDataForSearchFlight) getArguments().getSerializable(getString(R.string.key_serializable)));

            switch (selectedPassDataForSearchFlight.getTYPE_DIALOG())
            {
                case TYPE_DIALOG_TRAVEL_ZONE:
                    setUITravelZone(dialog);
                    break;

                case TYPE_DIALOG_ADVANCE_BOOKING:
                    setUIAdvanceBooking(dialog);
                    break;

                case TYPE_DIALOG_TRAVEL_FLEXIBILITY:
                    setUITravelFlexibility(dialog);
                    break;
            }
        }
        catch (Exception e)
        {
            Utils.ERROR("" + e.toString());
        }





        return dialog;
    }

    private void setUIAdvanceBooking(final Dialog dialog)
    {
        ((LinearLayout) dialog.findViewById(R.id.lytTravelZone)).setVisibility(View.GONE);
        ((LinearLayout) dialog.findViewById(R.id.lytTravelFlexibility)).setVisibility(View.GONE);
        ((LinearLayout) dialog.findViewById(R.id.lytABandTP)).setVisibility(View.VISIBLE);


        OTTextView txtClose = (OTTextView)dialog.findViewById(R.id.txtClose);
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        txtClose.setText(selectedPassDataForSearchFlight.getClose_Label());

        OTTextView txtTitle = (OTTextView)dialog.findViewById(R.id.txtTitle);
        txtTitle.setText(selectedPassDataForSearchFlight.getAdvanceBookingTitle());

        OTTextView txtABTitle = (OTTextView)dialog.findViewById(R.id.txtABTitle);
        txtABTitle.setText(selectedPassDataForSearchFlight.getAdvanceBookingTitle());

        OTTextView txtABDescription = (OTTextView)dialog.findViewById(R.id.txtABDescription);
        txtABDescription.setText("" + Html.fromHtml(selectedPassDataForSearchFlight.getAdvanceBookLongLateLabel()));

        OTTextView txtTPTitle = (OTTextView)dialog.findViewById(R.id.txtTPTitle);
        txtTPTitle.setText(selectedPassDataForSearchFlight.getTravelPeriodTitle());

        OTTextView txtTPDescription1 = (OTTextView)dialog.findViewById(R.id.txtTPDescription1);
        txtTPDescription1.setText(selectedPassDataForSearchFlight.getTravelPeriodVAlidityDateLabel());

        OTTextView txtTPDescription2 = (OTTextView)dialog.findViewById(R.id.txtTPDescription2);
        txtTPDescription2.setText(selectedPassDataForSearchFlight.getTravelPeriodVAlidityUnderDateLabel());


    }

    private void setUITravelFlexibility(final Dialog dialog) {

        ((LinearLayout) dialog.findViewById(R.id.lytTravelZone)).setVisibility(View.GONE);
        ((LinearLayout) dialog.findViewById(R.id.lytTravelFlexibility)).setVisibility(View.VISIBLE);
        ((LinearLayout) dialog.findViewById(R.id.lytABandTP)).setVisibility(View.GONE);


        OTTextView txtClose = (OTTextView)dialog.findViewById(R.id.txtClose);
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        txtClose.setText(selectedPassDataForSearchFlight.getClose_Label());

        OTTextView txtTitle = (OTTextView)dialog.findViewById(R.id.txtTitle);
        txtTitle.setText(selectedPassDataForSearchFlight.getTravelFlexibilityLabel());

        OTTextView txtTFDescription = (OTTextView)dialog.findViewById(R.id.txtTFDescription);
        txtTFDescription.setText(selectedPassDataForSearchFlight.getTravelFlexibilityRange());
    }

    private void setUITravelZone(final Dialog dialog)
    {
        ((LinearLayout) dialog.findViewById(R.id.lytTravelZone)).setVisibility(View.VISIBLE);
        ((LinearLayout) dialog.findViewById(R.id.lytTravelFlexibility)).setVisibility(View.GONE);
        ((LinearLayout) dialog.findViewById(R.id.lytABandTP)).setVisibility(View.GONE);

        if(selectedPassDataForSearchFlight != null)
        {
            OTTextView txtClose = (OTTextView)dialog.findViewById(R.id.txtClose);
            txtClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            txtClose.setText(selectedPassDataForSearchFlight.getClose_Label());

            OTTextView txtTitle = (OTTextView)dialog.findViewById(R.id.txtTitle);
            txtTitle.setText(selectedPassDataForSearchFlight.getTravelZoneTitleLabel());
            OTTextView txtDescription = (OTTextView)dialog.findViewById(R.id.txtDescription);
            txtDescription.setText(selectedPassDataForSearchFlight.getZoneTipLabel());

            mapView = (MapView)dialog.findViewById(R.id.mapView);
            mapView.onCreate(savedInstanceState);

            // Gets to GoogleMap from the MapView and does initialization stuff
            map = mapView.getMap();
            Utils.DEBUG("map reference : " + map);
            if(map != null)
            {
                // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
                MapsInitializer.initialize(this.getActivity());
                map.getUiSettings().setMyLocationButtonEnabled(false);
                map.getUiSettings().setScrollGesturesEnabled(true);
                map.getUiSettings().setMapToolbarEnabled(false);
                map.getUiSettings().setZoomControlsEnabled(false);
                map.getUiSettings().setZoomGesturesEnabled(true);
                map.getUiSettings().setRotateGesturesEnabled(false);
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                map.setOnMarkerClickListener(
                        new GoogleMap.OnMarkerClickListener() {
                            public boolean onMarkerClick(Marker marker) {
                                marker.showInfoWindow();
                                return true;
                            }
                        });


                //add polyline
                PolylineOptions polylineOptions = new PolylineOptions();
                polylineOptions.width(2.0F);
                for (int index = 0; index < selectedPassDataForSearchFlight.getZoneLatLongitudes().size(); index++)
                {
                    ZoneLatLongitude zoneLatLongitude = selectedPassDataForSearchFlight.getZoneLatLongitudes().get(index);

                    polylineOptions.add(new LatLng(zoneLatLongitude.getArriveLatitude(), zoneLatLongitude.getArriveLongitude()),
                            new LatLng(zoneLatLongitude.getDepartLatitude(), zoneLatLongitude.getDepartLongitude()));

                    map.addMarker(new MarkerOptions()
                            .position(new LatLng(zoneLatLongitude.getArriveLatitude(), zoneLatLongitude.getArriveLongitude()))
                            .title(zoneLatLongitude.getArriveCityName())
                    ).showInfoWindow();

                    map.addMarker(new MarkerOptions()
                            .position(new LatLng(zoneLatLongitude.getDepartLatitude(), zoneLatLongitude.getDepartLongitude()))
                            .title(zoneLatLongitude.getDepartCityName())
                    ).showInfoWindow();
                }

                /*LatLng mum = new LatLng(19.08861, 72.86806);//mum
                LatLng del = new LatLng(28.56944, 77.11806);//del

                LatLng lko = new LatLng(26.8465, 80.9467);//


                polylineOptions.add(mum, del);
                polylineOptions.add(mum, lko);*/

                map.addPolyline(polylineOptions);

                //add marker
               /*List<LatLng> list = new ArrayList<>();
                list.add(mum);
                list.add(del);
                list.add(lko);
                for (int index = 0; index < list.size(); index++)
                {
                    map.addMarker(new MarkerOptions()
                            .position(list.get(index))
                            .title("city " + index)
                            ).showInfoWindow();

                }*/



                //zoom level
                List<LatLng> points = polylineOptions.getPoints(); // route is instance of PolylineOptions

                LatLngBounds.Builder bc = new LatLngBounds.Builder();

                for (LatLng item : points) {
                    bc.include(item);
                }

                map.moveCamera(CameraUpdateFactory.newLatLngBounds(bc.build(), (int) Utils.conertDpToPixel(getActivity(), 300), (int) Utils.conertDpToPixel(getActivity(), 300), (int) Utils.conertDpToPixel(getActivity(), 80)));

            }

        }
    }


    @Override
    public void onResume() {
        if(mapView != null)
        {
            mapView.onResume();
        }
        super.onResume();
    }

    @Override
    public void onDestroy() {
        if(mapView != null) {
            mapView.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        if(mapView != null) {
            mapView.onLowMemory();
        }
        super.onLowMemory();
    }
}


