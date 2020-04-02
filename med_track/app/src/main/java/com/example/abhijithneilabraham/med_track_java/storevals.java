package com.example.abhijithneilabraham.med_track_java;

import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;

public class storevals implements Parcelable
{
    private String namestore,genderstore,addressstore,hospitalstore,dobstore,currentuser;
    private ParcelUuid[] val_uuids;

    public String getuser() {
        return currentuser;
    }

    public void set_user(String user) {
        this.currentuser = user;
    }

    public String getname() {
        return namestore;
    }

    public void set_name(String name) {
        this.namestore = name;
    }

    public String getgender() {
        return genderstore;
    }

    public void set_gender(String gender) {
        this.genderstore = gender;
    }
    public String gethosp() {
        return hospitalstore;
    }

    public void set_hosp(String hosp) {
        this.hospitalstore = hosp;
    }
    public String getaddress() {
        return addressstore;
    }

    public void set_address(String address) {
        this.addressstore = address;
    }
    public String getdob() {
        return dobstore;
    }

    public void set_dob(String dob) {
        this.dobstore = dob;
    }


//    public int getBluetooth_state() {
//        return bluetooth_state;
//    }
//
//    public void setBluetooth_state(int bluetooth_state) {
//        this.bluetooth_state = bluetooth_state;
//    }
//
//    public int getBluetooth_type() {
//        return bluetooth_type;
//    }
//
//    public void setBluetooth_type(int bluetooth_type) {
//        this.bluetooth_type = bluetooth_type;
//    }
//
//    public ParcelUuid[] getBluetooth_uuids() {
//        return bluetooth_uuids;
//    }
//
//    public void setBluetooth_uuids(ParcelUuid[] bluetooth_uuids) {
//        this.bluetooth_uuids = bluetooth_uuids;
//    }

//    public int getBluetooth_rssi() {
//        return bluetooth_rssi;
//    }
//
//    public void setBluetooth_rssi(int bluetooth_rssi) {
//        this.bluetooth_rssi = bluetooth_rssi;
//    }

    // Parcelable stuff
    public storevals()
    {}  //empty constructor

    protected storevals(Parcel in)
    {

        namestore = in.readString();
        addressstore=in.readString();
        hospitalstore=in.readString();
        dobstore=in.readString();
        genderstore=in.readString();
        currentuser=in.readString();
    }

    public void readFromParcel(Parcel in)
    {
        namestore = in.readString();
        addressstore=in.readString();
        hospitalstore=in.readString();
        dobstore=in.readString();
        genderstore=in.readString();
        currentuser=in.readString();
    }

    public static final Parcelable.Creator<storevals> CREATOR = new Parcelable.Creator<storevals>()
    {
        public storevals createFromParcel(Parcel in) {
            return new storevals(in);
        }

        public storevals[] newArray(int size) {

            return new storevals[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags)
    {
        out.writeString(namestore);
        out.writeString(addressstore);
        out.writeString(hospitalstore);
        out.writeString(genderstore);
        out.writeString(dobstore);
        out.writeString(currentuser);

    }


}