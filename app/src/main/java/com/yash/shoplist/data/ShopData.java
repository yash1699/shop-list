package com.yash.shoplist.data;

import java.util.ArrayList;

public class ShopData {
    private final String[] shopNameList = {"Apple Store", "Samsung SmartCafé", "Mi Home", "Asus Store", "Lenovo Exclusive Store",
                                           "Dell Exclusive Store", "Acer Mall - Exclusive Store", "OnePlus Authorised Store",
                                           "Nokia Connect", "Oppo Mobile shop"};

    private final String[] shopAddressList = {"Apple Store BG Block(Newtown), Action Area I, Newtown, New Town, West Bengal 700156",
            "Samsung SmartCafé SOUTH, TN Mukerjee Rd, near Dankuni Flyover, Subhas Pally, Dankuni, West Bengal 712311",
            "Unit no 5, 3rd floor, Diamond Plaza Mall, 68, Jessore Rd, Kolkata, West Bengal 700055",
            "Asus Store Shop No-3,Shyama Prasad Mukherjee Road,Hazra,near Jatindas Park Metro, Station, Kolkata, West Bengal 700026",
            "Lenovo Exclusive Store 6, Chandni Chowk St, Chandni Chawk, Bowbazar, Kolkata, West Bengal 700072",
            "DB-9 Salt Lake City, Opposite Bidhan Nagar College, beside State Bank of India ATM, Kolkata, West Bengal 700064",
            "Acer Mall - Exclusive Store, 25B, Chittaranjan Ave, Esplanade, Chowringhee North, Bowbazar, Kolkata, West Bengal 700072",
            "OnePlus Authorised Store, CIT Rd Scheme 6M, Scheme 6M, Phool Bagan, Kankurgachi, Kolkata, West Bengal 700054",
            "Nokia Connect 5/2, Champadali More, Jessore Rd, Barasat, Kolkata, West Bengal 700124",
            "Oppo Mobile Shop 11, Madhusudan Banerjee Rd, East Belgharia, Mandal Para Bat Tala, Nandan Nagar, Kolkata, West Bengal 700083"};

    private final String[] shopOfferList = {"20% Off on any product if you pay via SBI bank Debit card.", "",
                                        "30% Off on Mi Note 10 for one month.,10% Off on any product if you pay via HDFC bank Credit card.","",
                                        "Buy any laptop worth more than ₹49,999 and get a Sony headphone Free!",
                                        "Buy any laptop and get a laptop bag Free!,Get 20% Off on laptop if you pay via Bank Of India's Debit/Credit card.",
                                        "", "Get a headphone free with any mobile you purchase.",
                                        "", "Get a headphone free with any mobile you purchase."};

    private final String[] shopItemTags = {"apple,iphone,ipad,ipod,airpod,phone,mobile,tablet","samsung,galaxy,s9,s10,phone,mobile,tablet","xiaomi,redmi,mi,phone,mobile","asus,predator,tuf,laptop",
                                           "lenovo,laptop", "dell,laptop","acer,laptop,nitro", "oneplus 7,oneplus 6,phone,mobile","nokia,lumia,phone,mobile",
                                           "oppo,reno,mobile,phone"};

    public ShopData(){
    }

    public String[] getAllShopNames(){
        return shopNameList;
    }

    public String getShopAddress(String shop){
        int position = findIndex(shop);
        return shopAddressList[position];
    }

    public String getShopOffer(String shop){
        int position = findIndex(shop);
        return shopOfferList[position];
    }

    private int findIndex(String searchShop){
        for(int i=0;i<shopNameList.length;i++){
            if(shopNameList[i].equals(searchShop))
                return i;
        }
        return -1;
    }

    public String[] searchItemTag(String searchQuery){
        String[] tags;
        ArrayList<String> newShopData = new ArrayList<>();
        for (int i=0;i<shopItemTags.length;i++) {
            tags = shopItemTags[i].split(",");
            for (String tag : tags) {
                if (searchQuery.contains(tag)) {
                    newShopData.add(shopNameList[i]);
                    break;
                }
            }
        }
        String[] result = new String[newShopData.size()];
        result = newShopData.toArray(result);
        return result;
    }
}
