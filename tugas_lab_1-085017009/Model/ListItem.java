package com.example.tugas_recycle.Model;

public class ListItem {
        private String mNama;
        private String mDeskripsi;
        private String mImage;


    public ListItem(String nama, String deskripsi, String image){
        mNama=nama;
        mDeskripsi=deskripsi;
        mImage=image;
    }

    public String getmNama() {
        return mNama;
    }

    public void setmNama(String mNama) {
        this.mNama = mNama;
    }

    public String getmDeskripsi() {
        return mDeskripsi;
    }

    public void setmDeskripsi(String mDeskripsi) {
        this.mDeskripsi = mDeskripsi;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }
}
