package com.example.anafor.api;

import java.util.List;

public class ResultSearchKeyword {
    public List<Documents> documents;

    Meta meta;

    public class Meta{
        int total_count;

        public int getTotal_count() {
            return total_count;
        }

        public void setTotal_count(int total_count) {
            this.total_count = total_count;
        }
    }



    public class Documents {
        private String place_name;
        private String distance;
        private String place_url;
        private String category_name;
        private String address_Name;
        private String road_address_name;
        private String id;
        private String phone;
        private String category_group_code;
        private String category_group_name;
        private String x;
        private String y;

        public String getPlace_name() {
            return place_name;
        }

        public void setPlace_name(String place_name) {
            this.place_name = place_name;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getPlace_url() {
            return place_url;
        }

        public void setPlace_url(String place_url) {
            this.place_url = place_url;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public String getAddress_Name() {
            return address_Name;
        }

        public void setAddress_Name(String address_Name) {
            this.address_Name = address_Name;
        }

        public String getRoad_address_name() {
            return road_address_name;
        }

        public void setRoad_address_name(String road_address_name) {
            this.road_address_name = road_address_name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCategory_group_code() {
            return category_group_code;
        }

        public void setCategory_group_code(String category_group_code) {
            this.category_group_code = category_group_code;
        }

        public String getCategory_group_name() {
            return category_group_name;
        }

        public void setCategory_group_name(String category_group_name) {
            this.category_group_name = category_group_name;
        }

        public String getX() {
            return x;
        }

        public void setX(String x) {
            this.x = x;
        }

        public String getY() {
            return y;
        }

        public void setY(String y) {
            this.y = y;
        }
    }
}
