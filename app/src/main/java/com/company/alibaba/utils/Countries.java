package com.company.alibaba.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class Countries {
    JSONObject jsonObject  ;

    public JSONObject getJsonFullData() {
        return jsonObject;
    }

    public ArrayList<String> getCountries(){
        ArrayList<String> countries = new ArrayList<>() ;
        try {
            JSONArray countriesJsonArr = jsonObject.getJSONArray("Countries") ;
            for (int i = 0; i < countriesJsonArr.length() ; i++) {
                JSONObject country = countriesJsonArr.getJSONObject(i) ;
                countries.add(country.getString("FIELD2")) ;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return countries ;
    }

    public ArrayList<String> getCountryISO3AlphaCode(){
        ArrayList<String> countries = new ArrayList<>() ;

        try {
            JSONArray countriesJsonArr = jsonObject.getJSONArray("Countries") ;

            for (int i = 0; i < countriesJsonArr.length() ; i++) {
                JSONObject country = countriesJsonArr.getJSONObject(i) ;
                countries.add(country.getString("FIELD4")) ;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return countries ;
    }

    public ArrayList<String> getCountryISO2AlphaCode(){
        ArrayList<String> countries = new ArrayList<>() ;

        try {
            JSONArray countriesJsonArr = jsonObject.getJSONArray("Countries") ;

            for (int i = 0; i < countriesJsonArr.length() ; i++) {
                JSONObject country = countriesJsonArr.getJSONObject(i) ;
                countries.add(country.getString("FIELD3")) ;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return countries ;
    }


    public Countries(){
        try {
             jsonObject = new JSONObject("{\n" +
                    "   \"Countries\": [\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Afghanistan\",\n" +
                    "         \"FIELD3\": \"AF\",\n" +
                    "         \"FIELD4\": \"AFG\",\n" +
                    "         \"FIELD5\": 4,\n" +
                    "         \"FIELD6\": 33,\n" +
                    "         \"FIELD7\": 65\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Albania\",\n" +
                    "         \"FIELD3\": \"AL\",\n" +
                    "         \"FIELD4\": \"ALB\",\n" +
                    "         \"FIELD5\": 8,\n" +
                    "         \"FIELD6\": 41,\n" +
                    "         \"FIELD7\": 20\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Algeria\",\n" +
                    "         \"FIELD3\": \"DZ\",\n" +
                    "         \"FIELD4\": \"DZA\",\n" +
                    "         \"FIELD5\": 12,\n" +
                    "         \"FIELD6\": 28,\n" +
                    "         \"FIELD7\": 3\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"American Samoa\",\n" +
                    "         \"FIELD3\": \"AS\",\n" +
                    "         \"FIELD4\": \"ASM\",\n" +
                    "         \"FIELD5\": 16,\n" +
                    "         \"FIELD6\": -14.3333,\n" +
                    "         \"FIELD7\": -170\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Andorra\",\n" +
                    "         \"FIELD3\": \"AD\",\n" +
                    "         \"FIELD4\": \"AND\",\n" +
                    "         \"FIELD5\": 20,\n" +
                    "         \"FIELD6\": 42.5,\n" +
                    "         \"FIELD7\": 1.6\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Angola\",\n" +
                    "         \"FIELD3\": \"AO\",\n" +
                    "         \"FIELD4\": \"AGO\",\n" +
                    "         \"FIELD5\": 24,\n" +
                    "         \"FIELD6\": -12.5,\n" +
                    "         \"FIELD7\": 18.5\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Anguilla\",\n" +
                    "         \"FIELD3\": \"AI\",\n" +
                    "         \"FIELD4\": \"AIA\",\n" +
                    "         \"FIELD5\": 660,\n" +
                    "         \"FIELD6\": 18.25,\n" +
                    "         \"FIELD7\": -63.1667\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Antarctica\",\n" +
                    "         \"FIELD3\": \"AQ\",\n" +
                    "         \"FIELD4\": \"ATA\",\n" +
                    "         \"FIELD5\": 10,\n" +
                    "         \"FIELD6\": -90,\n" +
                    "         \"FIELD7\": 0\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Antigua and Barbuda\",\n" +
                    "         \"FIELD3\": \"AG\",\n" +
                    "         \"FIELD4\": \"ATG\",\n" +
                    "         \"FIELD5\": 28,\n" +
                    "         \"FIELD6\": 17.05,\n" +
                    "         \"FIELD7\": -61.8\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Argentina\",\n" +
                    "         \"FIELD3\": \"AR\",\n" +
                    "         \"FIELD4\": \"ARG\",\n" +
                    "         \"FIELD5\": 32,\n" +
                    "         \"FIELD6\": -34,\n" +
                    "         \"FIELD7\": -64\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Armenia\",\n" +
                    "         \"FIELD3\": \"AM\",\n" +
                    "         \"FIELD4\": \"ARM\",\n" +
                    "         \"FIELD5\": 51,\n" +
                    "         \"FIELD6\": 40,\n" +
                    "         \"FIELD7\": 45\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Aruba\",\n" +
                    "         \"FIELD3\": \"AW\",\n" +
                    "         \"FIELD4\": \"ABW\",\n" +
                    "         \"FIELD5\": 533,\n" +
                    "         \"FIELD6\": 12.5,\n" +
                    "         \"FIELD7\": -69.9667\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Australia\",\n" +
                    "         \"FIELD3\": \"AU\",\n" +
                    "         \"FIELD4\": \"AUS\",\n" +
                    "         \"FIELD5\": 36,\n" +
                    "         \"FIELD6\": -27,\n" +
                    "         \"FIELD7\": 133\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Austria\",\n" +
                    "         \"FIELD3\": \"AT\",\n" +
                    "         \"FIELD4\": \"AUT\",\n" +
                    "         \"FIELD5\": 40,\n" +
                    "         \"FIELD6\": 47.3333,\n" +
                    "         \"FIELD7\": 13.3333\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Azerbaijan\",\n" +
                    "         \"FIELD3\": \"AZ\",\n" +
                    "         \"FIELD4\": \"AZE\",\n" +
                    "         \"FIELD5\": 31,\n" +
                    "         \"FIELD6\": 40.5,\n" +
                    "         \"FIELD7\": 47.5\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Bahamas\",\n" +
                    "         \"FIELD3\": \"BS\",\n" +
                    "         \"FIELD4\": \"BHS\",\n" +
                    "         \"FIELD5\": 44,\n" +
                    "         \"FIELD6\": 24.25,\n" +
                    "         \"FIELD7\": -76\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Bahrain\",\n" +
                    "         \"FIELD3\": \"BH\",\n" +
                    "         \"FIELD4\": \"BHR\",\n" +
                    "         \"FIELD5\": 48,\n" +
                    "         \"FIELD6\": 26,\n" +
                    "         \"FIELD7\": 50.55\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Bangladesh\",\n" +
                    "         \"FIELD3\": \"BD\",\n" +
                    "         \"FIELD4\": \"BGD\",\n" +
                    "         \"FIELD5\": 50,\n" +
                    "         \"FIELD6\": 24,\n" +
                    "         \"FIELD7\": 90\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Barbados\",\n" +
                    "         \"FIELD3\": \"BB\",\n" +
                    "         \"FIELD4\": \"BRB\",\n" +
                    "         \"FIELD5\": 52,\n" +
                    "         \"FIELD6\": 13.1667,\n" +
                    "         \"FIELD7\": -59.5333\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Belarus\",\n" +
                    "         \"FIELD3\": \"BY\",\n" +
                    "         \"FIELD4\": \"BLR\",\n" +
                    "         \"FIELD5\": 112,\n" +
                    "         \"FIELD6\": 53,\n" +
                    "         \"FIELD7\": 28\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Belgium\",\n" +
                    "         \"FIELD3\": \"BE\",\n" +
                    "         \"FIELD4\": \"BEL\",\n" +
                    "         \"FIELD5\": 56,\n" +
                    "         \"FIELD6\": 50.8333,\n" +
                    "         \"FIELD7\": 4\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Belize\",\n" +
                    "         \"FIELD3\": \"BZ\",\n" +
                    "         \"FIELD4\": \"BLZ\",\n" +
                    "         \"FIELD5\": 84,\n" +
                    "         \"FIELD6\": 17.25,\n" +
                    "         \"FIELD7\": -88.75\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Benin\",\n" +
                    "         \"FIELD3\": \"BJ\",\n" +
                    "         \"FIELD4\": \"BEN\",\n" +
                    "         \"FIELD5\": 204,\n" +
                    "         \"FIELD6\": 9.5,\n" +
                    "         \"FIELD7\": 2.25\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Bermuda\",\n" +
                    "         \"FIELD3\": \"BM\",\n" +
                    "         \"FIELD4\": \"BMU\",\n" +
                    "         \"FIELD5\": 60,\n" +
                    "         \"FIELD6\": 32.3333,\n" +
                    "         \"FIELD7\": -64.75\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Bhutan\",\n" +
                    "         \"FIELD3\": \"BT\",\n" +
                    "         \"FIELD4\": \"BTN\",\n" +
                    "         \"FIELD5\": 64,\n" +
                    "         \"FIELD6\": 27.5,\n" +
                    "         \"FIELD7\": 90.5\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Bolivia, Plurinational State of\",\n" +
                    "         \"FIELD3\": \"BO\",\n" +
                    "         \"FIELD4\": \"BOL\",\n" +
                    "         \"FIELD5\": 68,\n" +
                    "         \"FIELD6\": -17,\n" +
                    "         \"FIELD7\": -65\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Bolivia\",\n" +
                    "         \"FIELD3\": \"BO\",\n" +
                    "         \"FIELD4\": \"BOL\",\n" +
                    "         \"FIELD5\": 68,\n" +
                    "         \"FIELD6\": -17,\n" +
                    "         \"FIELD7\": -65\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Bosnia and Herzegovina\",\n" +
                    "         \"FIELD3\": \"BA\",\n" +
                    "         \"FIELD4\": \"BIH\",\n" +
                    "         \"FIELD5\": 70,\n" +
                    "         \"FIELD6\": 44,\n" +
                    "         \"FIELD7\": 18\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Botswana\",\n" +
                    "         \"FIELD3\": \"BW\",\n" +
                    "         \"FIELD4\": \"BWA\",\n" +
                    "         \"FIELD5\": 72,\n" +
                    "         \"FIELD6\": -22,\n" +
                    "         \"FIELD7\": 24\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Bouvet Island\",\n" +
                    "         \"FIELD3\": \"BV\",\n" +
                    "         \"FIELD4\": \"BVT\",\n" +
                    "         \"FIELD5\": 74,\n" +
                    "         \"FIELD6\": -54.4333,\n" +
                    "         \"FIELD7\": 3.4\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Brazil\",\n" +
                    "         \"FIELD3\": \"BR\",\n" +
                    "         \"FIELD4\": \"BRA\",\n" +
                    "         \"FIELD5\": 76,\n" +
                    "         \"FIELD6\": -10,\n" +
                    "         \"FIELD7\": -55\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"British Indian Ocean Territory\",\n" +
                    "         \"FIELD3\": \"IO\",\n" +
                    "         \"FIELD4\": \"IOT\",\n" +
                    "         \"FIELD5\": 86,\n" +
                    "         \"FIELD6\": -6,\n" +
                    "         \"FIELD7\": 71.5\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Brunei Darussalam\",\n" +
                    "         \"FIELD3\": \"BN\",\n" +
                    "         \"FIELD4\": \"BRN\",\n" +
                    "         \"FIELD5\": 96,\n" +
                    "         \"FIELD6\": 4.5,\n" +
                    "         \"FIELD7\": 114.6667\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Brunei\",\n" +
                    "         \"FIELD3\": \"BN\",\n" +
                    "         \"FIELD4\": \"BRN\",\n" +
                    "         \"FIELD5\": 96,\n" +
                    "         \"FIELD6\": 4.5,\n" +
                    "         \"FIELD7\": 114.6667\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Bulgaria\",\n" +
                    "         \"FIELD3\": \"BG\",\n" +
                    "         \"FIELD4\": \"BGR\",\n" +
                    "         \"FIELD5\": 100,\n" +
                    "         \"FIELD6\": 43,\n" +
                    "         \"FIELD7\": 25\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Burkina Faso\",\n" +
                    "         \"FIELD3\": \"BF\",\n" +
                    "         \"FIELD4\": \"BFA\",\n" +
                    "         \"FIELD5\": 854,\n" +
                    "         \"FIELD6\": 13,\n" +
                    "         \"FIELD7\": -2\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Burundi\",\n" +
                    "         \"FIELD3\": \"BI\",\n" +
                    "         \"FIELD4\": \"BDI\",\n" +
                    "         \"FIELD5\": 108,\n" +
                    "         \"FIELD6\": -3.5,\n" +
                    "         \"FIELD7\": 30\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Cambodia\",\n" +
                    "         \"FIELD3\": \"KH\",\n" +
                    "         \"FIELD4\": \"KHM\",\n" +
                    "         \"FIELD5\": 116,\n" +
                    "         \"FIELD6\": 13,\n" +
                    "         \"FIELD7\": 105\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Cameroon\",\n" +
                    "         \"FIELD3\": \"CM\",\n" +
                    "         \"FIELD4\": \"CMR\",\n" +
                    "         \"FIELD5\": 120,\n" +
                    "         \"FIELD6\": 6,\n" +
                    "         \"FIELD7\": 12\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Canada\",\n" +
                    "         \"FIELD3\": \"CA\",\n" +
                    "         \"FIELD4\": \"CAN\",\n" +
                    "         \"FIELD5\": 124,\n" +
                    "         \"FIELD6\": 60,\n" +
                    "         \"FIELD7\": -95\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Cape Verde\",\n" +
                    "         \"FIELD3\": \"CV\",\n" +
                    "         \"FIELD4\": \"CPV\",\n" +
                    "         \"FIELD5\": 132,\n" +
                    "         \"FIELD6\": 16,\n" +
                    "         \"FIELD7\": -24\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Cayman Islands\",\n" +
                    "         \"FIELD3\": \"KY\",\n" +
                    "         \"FIELD4\": \"CYM\",\n" +
                    "         \"FIELD5\": 136,\n" +
                    "         \"FIELD6\": 19.5,\n" +
                    "         \"FIELD7\": -80.5\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Central African Republic\",\n" +
                    "         \"FIELD3\": \"CF\",\n" +
                    "         \"FIELD4\": \"CAF\",\n" +
                    "         \"FIELD5\": 140,\n" +
                    "         \"FIELD6\": 7,\n" +
                    "         \"FIELD7\": 21\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Chad\",\n" +
                    "         \"FIELD3\": \"TD\",\n" +
                    "         \"FIELD4\": \"TCD\",\n" +
                    "         \"FIELD5\": 148,\n" +
                    "         \"FIELD6\": 15,\n" +
                    "         \"FIELD7\": 19\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Chile\",\n" +
                    "         \"FIELD3\": \"CL\",\n" +
                    "         \"FIELD4\": \"CHL\",\n" +
                    "         \"FIELD5\": 152,\n" +
                    "         \"FIELD6\": -30,\n" +
                    "         \"FIELD7\": -71\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"China\",\n" +
                    "         \"FIELD3\": \"CN\",\n" +
                    "         \"FIELD4\": \"CHN\",\n" +
                    "         \"FIELD5\": 156,\n" +
                    "         \"FIELD6\": 35,\n" +
                    "         \"FIELD7\": 105\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Christmas Island\",\n" +
                    "         \"FIELD3\": \"CX\",\n" +
                    "         \"FIELD4\": \"CXR\",\n" +
                    "         \"FIELD5\": 162,\n" +
                    "         \"FIELD6\": -10.5,\n" +
                    "         \"FIELD7\": 105.6667\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Cocos (Keeling) Islands\",\n" +
                    "         \"FIELD3\": \"CC\",\n" +
                    "         \"FIELD4\": \"CCK\",\n" +
                    "         \"FIELD5\": 166,\n" +
                    "         \"FIELD6\": -12.5,\n" +
                    "         \"FIELD7\": 96.8333\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Colombia\",\n" +
                    "         \"FIELD3\": \"CO\",\n" +
                    "         \"FIELD4\": \"COL\",\n" +
                    "         \"FIELD5\": 170,\n" +
                    "         \"FIELD6\": 4,\n" +
                    "         \"FIELD7\": -72\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Comoros\",\n" +
                    "         \"FIELD3\": \"KM\",\n" +
                    "         \"FIELD4\": \"COM\",\n" +
                    "         \"FIELD5\": 174,\n" +
                    "         \"FIELD6\": -12.1667,\n" +
                    "         \"FIELD7\": 44.25\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Congo\",\n" +
                    "         \"FIELD3\": \"CG\",\n" +
                    "         \"FIELD4\": \"COG\",\n" +
                    "         \"FIELD5\": 178,\n" +
                    "         \"FIELD6\": -1,\n" +
                    "         \"FIELD7\": 15\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Congo, the Democratic Republic of the\",\n" +
                    "         \"FIELD3\": \"CD\",\n" +
                    "         \"FIELD4\": \"COD\",\n" +
                    "         \"FIELD5\": 180,\n" +
                    "         \"FIELD6\": 0,\n" +
                    "         \"FIELD7\": 25\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Cook Islands\",\n" +
                    "         \"FIELD3\": \"CK\",\n" +
                    "         \"FIELD4\": \"COK\",\n" +
                    "         \"FIELD5\": 184,\n" +
                    "         \"FIELD6\": -21.2333,\n" +
                    "         \"FIELD7\": -159.7667\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Costa Rica\",\n" +
                    "         \"FIELD3\": \"CR\",\n" +
                    "         \"FIELD4\": \"CRI\",\n" +
                    "         \"FIELD5\": 188,\n" +
                    "         \"FIELD6\": 10,\n" +
                    "         \"FIELD7\": -84\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Côte d'Ivoire\",\n" +
                    "         \"FIELD3\": \"CI\",\n" +
                    "         \"FIELD4\": \"CIV\",\n" +
                    "         \"FIELD5\": 384,\n" +
                    "         \"FIELD6\": 8,\n" +
                    "         \"FIELD7\": -5\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Ivory Coast\",\n" +
                    "         \"FIELD3\": \"CI\",\n" +
                    "         \"FIELD4\": \"CIV\",\n" +
                    "         \"FIELD5\": 384,\n" +
                    "         \"FIELD6\": 8,\n" +
                    "         \"FIELD7\": -5\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Croatia\",\n" +
                    "         \"FIELD3\": \"HR\",\n" +
                    "         \"FIELD4\": \"HRV\",\n" +
                    "         \"FIELD5\": 191,\n" +
                    "         \"FIELD6\": 45.1667,\n" +
                    "         \"FIELD7\": 15.5\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Cuba\",\n" +
                    "         \"FIELD3\": \"CU\",\n" +
                    "         \"FIELD4\": \"CUB\",\n" +
                    "         \"FIELD5\": 192,\n" +
                    "         \"FIELD6\": 21.5,\n" +
                    "         \"FIELD7\": -80\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Cyprus\",\n" +
                    "         \"FIELD3\": \"CY\",\n" +
                    "         \"FIELD4\": \"CYP\",\n" +
                    "         \"FIELD5\": 196,\n" +
                    "         \"FIELD6\": 35,\n" +
                    "         \"FIELD7\": 33\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Czech Republic\",\n" +
                    "         \"FIELD3\": \"CZ\",\n" +
                    "         \"FIELD4\": \"CZE\",\n" +
                    "         \"FIELD5\": 203,\n" +
                    "         \"FIELD6\": 49.75,\n" +
                    "         \"FIELD7\": 15.5\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Denmark\",\n" +
                    "         \"FIELD3\": \"DK\",\n" +
                    "         \"FIELD4\": \"DNK\",\n" +
                    "         \"FIELD5\": 208,\n" +
                    "         \"FIELD6\": 56,\n" +
                    "         \"FIELD7\": 10\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Djibouti\",\n" +
                    "         \"FIELD3\": \"DJ\",\n" +
                    "         \"FIELD4\": \"DJI\",\n" +
                    "         \"FIELD5\": 262,\n" +
                    "         \"FIELD6\": 11.5,\n" +
                    "         \"FIELD7\": 43\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Dominica\",\n" +
                    "         \"FIELD3\": \"DM\",\n" +
                    "         \"FIELD4\": \"DMA\",\n" +
                    "         \"FIELD5\": 212,\n" +
                    "         \"FIELD6\": 15.4167,\n" +
                    "         \"FIELD7\": -61.3333\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Dominican Republic\",\n" +
                    "         \"FIELD3\": \"DO\",\n" +
                    "         \"FIELD4\": \"DOM\",\n" +
                    "         \"FIELD5\": 214,\n" +
                    "         \"FIELD6\": 19,\n" +
                    "         \"FIELD7\": -70.6667\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Ecuador\",\n" +
                    "         \"FIELD3\": \"EC\",\n" +
                    "         \"FIELD4\": \"ECU\",\n" +
                    "         \"FIELD5\": 218,\n" +
                    "         \"FIELD6\": -2,\n" +
                    "         \"FIELD7\": -77.5\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Egypt\",\n" +
                    "         \"FIELD3\": \"EG\",\n" +
                    "         \"FIELD4\": \"EGY\",\n" +
                    "         \"FIELD5\": 818,\n" +
                    "         \"FIELD6\": 27,\n" +
                    "         \"FIELD7\": 30\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"El Salvador\",\n" +
                    "         \"FIELD3\": \"SV\",\n" +
                    "         \"FIELD4\": \"SLV\",\n" +
                    "         \"FIELD5\": 222,\n" +
                    "         \"FIELD6\": 13.8333,\n" +
                    "         \"FIELD7\": -88.9167\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Equatorial Guinea\",\n" +
                    "         \"FIELD3\": \"GQ\",\n" +
                    "         \"FIELD4\": \"GNQ\",\n" +
                    "         \"FIELD5\": 226,\n" +
                    "         \"FIELD6\": 2,\n" +
                    "         \"FIELD7\": 10\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Eritrea\",\n" +
                    "         \"FIELD3\": \"ER\",\n" +
                    "         \"FIELD4\": \"ERI\",\n" +
                    "         \"FIELD5\": 232,\n" +
                    "         \"FIELD6\": 15,\n" +
                    "         \"FIELD7\": 39\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Estonia\",\n" +
                    "         \"FIELD3\": \"EE\",\n" +
                    "         \"FIELD4\": \"EST\",\n" +
                    "         \"FIELD5\": 233,\n" +
                    "         \"FIELD6\": 59,\n" +
                    "         \"FIELD7\": 26\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Ethiopia\",\n" +
                    "         \"FIELD3\": \"ET\",\n" +
                    "         \"FIELD4\": \"ETH\",\n" +
                    "         \"FIELD5\": 231,\n" +
                    "         \"FIELD6\": 8,\n" +
                    "         \"FIELD7\": 38\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Falkland Islands (Malvinas)\",\n" +
                    "         \"FIELD3\": \"FK\",\n" +
                    "         \"FIELD4\": \"FLK\",\n" +
                    "         \"FIELD5\": 238,\n" +
                    "         \"FIELD6\": -51.75,\n" +
                    "         \"FIELD7\": -59\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Faroe Islands\",\n" +
                    "         \"FIELD3\": \"FO\",\n" +
                    "         \"FIELD4\": \"FRO\",\n" +
                    "         \"FIELD5\": 234,\n" +
                    "         \"FIELD6\": 62,\n" +
                    "         \"FIELD7\": -7\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Fiji\",\n" +
                    "         \"FIELD3\": \"FJ\",\n" +
                    "         \"FIELD4\": \"FJI\",\n" +
                    "         \"FIELD5\": 242,\n" +
                    "         \"FIELD6\": -18,\n" +
                    "         \"FIELD7\": 175\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Finland\",\n" +
                    "         \"FIELD3\": \"FI\",\n" +
                    "         \"FIELD4\": \"FIN\",\n" +
                    "         \"FIELD5\": 246,\n" +
                    "         \"FIELD6\": 64,\n" +
                    "         \"FIELD7\": 26\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"France\",\n" +
                    "         \"FIELD3\": \"FR\",\n" +
                    "         \"FIELD4\": \"FRA\",\n" +
                    "         \"FIELD5\": 250,\n" +
                    "         \"FIELD6\": 46,\n" +
                    "         \"FIELD7\": 2\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"French Guiana\",\n" +
                    "         \"FIELD3\": \"GF\",\n" +
                    "         \"FIELD4\": \"GUF\",\n" +
                    "         \"FIELD5\": 254,\n" +
                    "         \"FIELD6\": 4,\n" +
                    "         \"FIELD7\": -53\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"French Polynesia\",\n" +
                    "         \"FIELD3\": \"PF\",\n" +
                    "         \"FIELD4\": \"PYF\",\n" +
                    "         \"FIELD5\": 258,\n" +
                    "         \"FIELD6\": -15,\n" +
                    "         \"FIELD7\": -140\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"French Southern Territories\",\n" +
                    "         \"FIELD3\": \"TF\",\n" +
                    "         \"FIELD4\": \"ATF\",\n" +
                    "         \"FIELD5\": 260,\n" +
                    "         \"FIELD6\": -43,\n" +
                    "         \"FIELD7\": 67\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Gabon\",\n" +
                    "         \"FIELD3\": \"GA\",\n" +
                    "         \"FIELD4\": \"GAB\",\n" +
                    "         \"FIELD5\": 266,\n" +
                    "         \"FIELD6\": -1,\n" +
                    "         \"FIELD7\": 11.75\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Gambia\",\n" +
                    "         \"FIELD3\": \"GM\",\n" +
                    "         \"FIELD4\": \"GMB\",\n" +
                    "         \"FIELD5\": 270,\n" +
                    "         \"FIELD6\": 13.4667,\n" +
                    "         \"FIELD7\": -16.5667\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Georgia\",\n" +
                    "         \"FIELD3\": \"GE\",\n" +
                    "         \"FIELD4\": \"GEO\",\n" +
                    "         \"FIELD5\": 268,\n" +
                    "         \"FIELD6\": 42,\n" +
                    "         \"FIELD7\": 43.5\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Germany\",\n" +
                    "         \"FIELD3\": \"DE\",\n" +
                    "         \"FIELD4\": \"DEU\",\n" +
                    "         \"FIELD5\": 276,\n" +
                    "         \"FIELD6\": 51,\n" +
                    "         \"FIELD7\": 9\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Ghana\",\n" +
                    "         \"FIELD3\": \"GH\",\n" +
                    "         \"FIELD4\": \"GHA\",\n" +
                    "         \"FIELD5\": 288,\n" +
                    "         \"FIELD6\": 8,\n" +
                    "         \"FIELD7\": -2\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Gibraltar\",\n" +
                    "         \"FIELD3\": \"GI\",\n" +
                    "         \"FIELD4\": \"GIB\",\n" +
                    "         \"FIELD5\": 292,\n" +
                    "         \"FIELD6\": 36.1833,\n" +
                    "         \"FIELD7\": -5.3667\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Greece\",\n" +
                    "         \"FIELD3\": \"GR\",\n" +
                    "         \"FIELD4\": \"GRC\",\n" +
                    "         \"FIELD5\": 300,\n" +
                    "         \"FIELD6\": 39,\n" +
                    "         \"FIELD7\": 22\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Greenland\",\n" +
                    "         \"FIELD3\": \"GL\",\n" +
                    "         \"FIELD4\": \"GRL\",\n" +
                    "         \"FIELD5\": 304,\n" +
                    "         \"FIELD6\": 72,\n" +
                    "         \"FIELD7\": -40\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Grenada\",\n" +
                    "         \"FIELD3\": \"GD\",\n" +
                    "         \"FIELD4\": \"GRD\",\n" +
                    "         \"FIELD5\": 308,\n" +
                    "         \"FIELD6\": 12.1167,\n" +
                    "         \"FIELD7\": -61.6667\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Guadeloupe\",\n" +
                    "         \"FIELD3\": \"GP\",\n" +
                    "         \"FIELD4\": \"GLP\",\n" +
                    "         \"FIELD5\": 312,\n" +
                    "         \"FIELD6\": 16.25,\n" +
                    "         \"FIELD7\": -61.5833\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Guam\",\n" +
                    "         \"FIELD3\": \"GU\",\n" +
                    "         \"FIELD4\": \"GUM\",\n" +
                    "         \"FIELD5\": 316,\n" +
                    "         \"FIELD6\": 13.4667,\n" +
                    "         \"FIELD7\": 144.7833\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Guatemala\",\n" +
                    "         \"FIELD3\": \"GT\",\n" +
                    "         \"FIELD4\": \"GTM\",\n" +
                    "         \"FIELD5\": 320,\n" +
                    "         \"FIELD6\": 15.5,\n" +
                    "         \"FIELD7\": -90.25\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Guernsey\",\n" +
                    "         \"FIELD3\": \"GG\",\n" +
                    "         \"FIELD4\": \"GGY\",\n" +
                    "         \"FIELD5\": 831,\n" +
                    "         \"FIELD6\": 49.5,\n" +
                    "         \"FIELD7\": -2.56\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Guinea\",\n" +
                    "         \"FIELD3\": \"GN\",\n" +
                    "         \"FIELD4\": \"GIN\",\n" +
                    "         \"FIELD5\": 324,\n" +
                    "         \"FIELD6\": 11,\n" +
                    "         \"FIELD7\": -10\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Guinea-Bissau\",\n" +
                    "         \"FIELD3\": \"GW\",\n" +
                    "         \"FIELD4\": \"GNB\",\n" +
                    "         \"FIELD5\": 624,\n" +
                    "         \"FIELD6\": 12,\n" +
                    "         \"FIELD7\": -15\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Guyana\",\n" +
                    "         \"FIELD3\": \"GY\",\n" +
                    "         \"FIELD4\": \"GUY\",\n" +
                    "         \"FIELD5\": 328,\n" +
                    "         \"FIELD6\": 5,\n" +
                    "         \"FIELD7\": -59\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Haiti\",\n" +
                    "         \"FIELD3\": \"HT\",\n" +
                    "         \"FIELD4\": \"HTI\",\n" +
                    "         \"FIELD5\": 332,\n" +
                    "         \"FIELD6\": 19,\n" +
                    "         \"FIELD7\": -72.4167\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Heard Island and McDonald Islands\",\n" +
                    "         \"FIELD3\": \"HM\",\n" +
                    "         \"FIELD4\": \"HMD\",\n" +
                    "         \"FIELD5\": 334,\n" +
                    "         \"FIELD6\": -53.1,\n" +
                    "         \"FIELD7\": 72.5167\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Holy See (Vatican City State)\",\n" +
                    "         \"FIELD3\": \"VA\",\n" +
                    "         \"FIELD4\": \"VAT\",\n" +
                    "         \"FIELD5\": 336,\n" +
                    "         \"FIELD6\": 41.9,\n" +
                    "         \"FIELD7\": 12.45\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Honduras\",\n" +
                    "         \"FIELD3\": \"HN\",\n" +
                    "         \"FIELD4\": \"HND\",\n" +
                    "         \"FIELD5\": 340,\n" +
                    "         \"FIELD6\": 15,\n" +
                    "         \"FIELD7\": -86.5\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Hong Kong\",\n" +
                    "         \"FIELD3\": \"HK\",\n" +
                    "         \"FIELD4\": \"HKG\",\n" +
                    "         \"FIELD5\": 344,\n" +
                    "         \"FIELD6\": 22.25,\n" +
                    "         \"FIELD7\": 114.1667\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Hungary\",\n" +
                    "         \"FIELD3\": \"HU\",\n" +
                    "         \"FIELD4\": \"HUN\",\n" +
                    "         \"FIELD5\": 348,\n" +
                    "         \"FIELD6\": 47,\n" +
                    "         \"FIELD7\": 20\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Iceland\",\n" +
                    "         \"FIELD3\": \"IS\",\n" +
                    "         \"FIELD4\": \"ISL\",\n" +
                    "         \"FIELD5\": 352,\n" +
                    "         \"FIELD6\": 65,\n" +
                    "         \"FIELD7\": -18\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"India\",\n" +
                    "         \"FIELD3\": \"IN\",\n" +
                    "         \"FIELD4\": \"IND\",\n" +
                    "         \"FIELD5\": 356,\n" +
                    "         \"FIELD6\": 20,\n" +
                    "         \"FIELD7\": 77\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Indonesia\",\n" +
                    "         \"FIELD3\": \"ID\",\n" +
                    "         \"FIELD4\": \"IDN\",\n" +
                    "         \"FIELD5\": 360,\n" +
                    "         \"FIELD6\": -5,\n" +
                    "         \"FIELD7\": 120\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Iran, Islamic Republic of\",\n" +
                    "         \"FIELD3\": \"IR\",\n" +
                    "         \"FIELD4\": \"IRN\",\n" +
                    "         \"FIELD5\": 364,\n" +
                    "         \"FIELD6\": 32,\n" +
                    "         \"FIELD7\": 53\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Iraq\",\n" +
                    "         \"FIELD3\": \"IQ\",\n" +
                    "         \"FIELD4\": \"IRQ\",\n" +
                    "         \"FIELD5\": 368,\n" +
                    "         \"FIELD6\": 33,\n" +
                    "         \"FIELD7\": 44\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Ireland\",\n" +
                    "         \"FIELD3\": \"IE\",\n" +
                    "         \"FIELD4\": \"IRL\",\n" +
                    "         \"FIELD5\": 372,\n" +
                    "         \"FIELD6\": 53,\n" +
                    "         \"FIELD7\": -8\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Isle of Man\",\n" +
                    "         \"FIELD3\": \"IM\",\n" +
                    "         \"FIELD4\": \"IMN\",\n" +
                    "         \"FIELD5\": 833,\n" +
                    "         \"FIELD6\": 54.23,\n" +
                    "         \"FIELD7\": -4.55\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Israel\",\n" +
                    "         \"FIELD3\": \"IL\",\n" +
                    "         \"FIELD4\": \"ISR\",\n" +
                    "         \"FIELD5\": 376,\n" +
                    "         \"FIELD6\": 31.5,\n" +
                    "         \"FIELD7\": 34.75\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Italy\",\n" +
                    "         \"FIELD3\": \"IT\",\n" +
                    "         \"FIELD4\": \"ITA\",\n" +
                    "         \"FIELD5\": 380,\n" +
                    "         \"FIELD6\": 42.8333,\n" +
                    "         \"FIELD7\": 12.8333\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Jamaica\",\n" +
                    "         \"FIELD3\": \"JM\",\n" +
                    "         \"FIELD4\": \"JAM\",\n" +
                    "         \"FIELD5\": 388,\n" +
                    "         \"FIELD6\": 18.25,\n" +
                    "         \"FIELD7\": -77.5\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Japan\",\n" +
                    "         \"FIELD3\": \"JP\",\n" +
                    "         \"FIELD4\": \"JPN\",\n" +
                    "         \"FIELD5\": 392,\n" +
                    "         \"FIELD6\": 36,\n" +
                    "         \"FIELD7\": 138\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Jersey\",\n" +
                    "         \"FIELD3\": \"JE\",\n" +
                    "         \"FIELD4\": \"JEY\",\n" +
                    "         \"FIELD5\": 832,\n" +
                    "         \"FIELD6\": 49.21,\n" +
                    "         \"FIELD7\": -2.13\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Jordan\",\n" +
                    "         \"FIELD3\": \"JO\",\n" +
                    "         \"FIELD4\": \"JOR\",\n" +
                    "         \"FIELD5\": 400,\n" +
                    "         \"FIELD6\": 31,\n" +
                    "         \"FIELD7\": 36\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Kazakhstan\",\n" +
                    "         \"FIELD3\": \"KZ\",\n" +
                    "         \"FIELD4\": \"KAZ\",\n" +
                    "         \"FIELD5\": 398,\n" +
                    "         \"FIELD6\": 48,\n" +
                    "         \"FIELD7\": 68\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Kenya\",\n" +
                    "         \"FIELD3\": \"KE\",\n" +
                    "         \"FIELD4\": \"KEN\",\n" +
                    "         \"FIELD5\": 404,\n" +
                    "         \"FIELD6\": 1,\n" +
                    "         \"FIELD7\": 38\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Kiribati\",\n" +
                    "         \"FIELD3\": \"KI\",\n" +
                    "         \"FIELD4\": \"KIR\",\n" +
                    "         \"FIELD5\": 296,\n" +
                    "         \"FIELD6\": 1.4167,\n" +
                    "         \"FIELD7\": 173\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Korea, Democratic People's Republic of\",\n" +
                    "         \"FIELD3\": \"KP\",\n" +
                    "         \"FIELD4\": \"PRK\",\n" +
                    "         \"FIELD5\": 408,\n" +
                    "         \"FIELD6\": 40,\n" +
                    "         \"FIELD7\": 127\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Korea, Republic of\",\n" +
                    "         \"FIELD3\": \"KR\",\n" +
                    "         \"FIELD4\": \"KOR\",\n" +
                    "         \"FIELD5\": 410,\n" +
                    "         \"FIELD6\": 37,\n" +
                    "         \"FIELD7\": 127.5\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"South Korea\",\n" +
                    "         \"FIELD3\": \"KR\",\n" +
                    "         \"FIELD4\": \"KOR\",\n" +
                    "         \"FIELD5\": 410,\n" +
                    "         \"FIELD6\": 37,\n" +
                    "         \"FIELD7\": 127.5\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Kuwait\",\n" +
                    "         \"FIELD3\": \"KW\",\n" +
                    "         \"FIELD4\": \"KWT\",\n" +
                    "         \"FIELD5\": 414,\n" +
                    "         \"FIELD6\": 29.3375,\n" +
                    "         \"FIELD7\": 47.6581\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Kyrgyzstan\",\n" +
                    "         \"FIELD3\": \"KG\",\n" +
                    "         \"FIELD4\": \"KGZ\",\n" +
                    "         \"FIELD5\": 417,\n" +
                    "         \"FIELD6\": 41,\n" +
                    "         \"FIELD7\": 75\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Lao People's Democratic Republic\",\n" +
                    "         \"FIELD3\": \"LA\",\n" +
                    "         \"FIELD4\": \"LAO\",\n" +
                    "         \"FIELD5\": 418,\n" +
                    "         \"FIELD6\": 18,\n" +
                    "         \"FIELD7\": 105\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Latvia\",\n" +
                    "         \"FIELD3\": \"LV\",\n" +
                    "         \"FIELD4\": \"LVA\",\n" +
                    "         \"FIELD5\": 428,\n" +
                    "         \"FIELD6\": 57,\n" +
                    "         \"FIELD7\": 25\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Lebanon\",\n" +
                    "         \"FIELD3\": \"LB\",\n" +
                    "         \"FIELD4\": \"LBN\",\n" +
                    "         \"FIELD5\": 422,\n" +
                    "         \"FIELD6\": 33.8333,\n" +
                    "         \"FIELD7\": 35.8333\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Lesotho\",\n" +
                    "         \"FIELD3\": \"LS\",\n" +
                    "         \"FIELD4\": \"LSO\",\n" +
                    "         \"FIELD5\": 426,\n" +
                    "         \"FIELD6\": -29.5,\n" +
                    "         \"FIELD7\": 28.5\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Liberia\",\n" +
                    "         \"FIELD3\": \"LR\",\n" +
                    "         \"FIELD4\": \"LBR\",\n" +
                    "         \"FIELD5\": 430,\n" +
                    "         \"FIELD6\": 6.5,\n" +
                    "         \"FIELD7\": -9.5\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Libyan Arab Jamahiriya\",\n" +
                    "         \"FIELD3\": \"LY\",\n" +
                    "         \"FIELD4\": \"LBY\",\n" +
                    "         \"FIELD5\": 434,\n" +
                    "         \"FIELD6\": 25,\n" +
                    "         \"FIELD7\": 17\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Libya\",\n" +
                    "         \"FIELD3\": \"LY\",\n" +
                    "         \"FIELD4\": \"LBY\",\n" +
                    "         \"FIELD5\": 434,\n" +
                    "         \"FIELD6\": 25,\n" +
                    "         \"FIELD7\": 17\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Liechtenstein\",\n" +
                    "         \"FIELD3\": \"LI\",\n" +
                    "         \"FIELD4\": \"LIE\",\n" +
                    "         \"FIELD5\": 438,\n" +
                    "         \"FIELD6\": 47.1667,\n" +
                    "         \"FIELD7\": 9.5333\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Lithuania\",\n" +
                    "         \"FIELD3\": \"LT\",\n" +
                    "         \"FIELD4\": \"LTU\",\n" +
                    "         \"FIELD5\": 440,\n" +
                    "         \"FIELD6\": 56,\n" +
                    "         \"FIELD7\": 24\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Luxembourg\",\n" +
                    "         \"FIELD3\": \"LU\",\n" +
                    "         \"FIELD4\": \"LUX\",\n" +
                    "         \"FIELD5\": 442,\n" +
                    "         \"FIELD6\": 49.75,\n" +
                    "         \"FIELD7\": 6.1667\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Macao\",\n" +
                    "         \"FIELD3\": \"MO\",\n" +
                    "         \"FIELD4\": \"MAC\",\n" +
                    "         \"FIELD5\": 446,\n" +
                    "         \"FIELD6\": 22.1667,\n" +
                    "         \"FIELD7\": 113.55\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Macedonia, the former Yugoslav Republic of\",\n" +
                    "         \"FIELD3\": \"MK\",\n" +
                    "         \"FIELD4\": \"MKD\",\n" +
                    "         \"FIELD5\": 807,\n" +
                    "         \"FIELD6\": 41.8333,\n" +
                    "         \"FIELD7\": 22\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Madagascar\",\n" +
                    "         \"FIELD3\": \"MG\",\n" +
                    "         \"FIELD4\": \"MDG\",\n" +
                    "         \"FIELD5\": 450,\n" +
                    "         \"FIELD6\": -20,\n" +
                    "         \"FIELD7\": 47\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Malawi\",\n" +
                    "         \"FIELD3\": \"MW\",\n" +
                    "         \"FIELD4\": \"MWI\",\n" +
                    "         \"FIELD5\": 454,\n" +
                    "         \"FIELD6\": -13.5,\n" +
                    "         \"FIELD7\": 34\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Malaysia\",\n" +
                    "         \"FIELD3\": \"MY\",\n" +
                    "         \"FIELD4\": \"MYS\",\n" +
                    "         \"FIELD5\": 458,\n" +
                    "         \"FIELD6\": 2.5,\n" +
                    "         \"FIELD7\": 112.5\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Maldives\",\n" +
                    "         \"FIELD3\": \"MV\",\n" +
                    "         \"FIELD4\": \"MDV\",\n" +
                    "         \"FIELD5\": 462,\n" +
                    "         \"FIELD6\": 3.25,\n" +
                    "         \"FIELD7\": 73\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Mali\",\n" +
                    "         \"FIELD3\": \"ML\",\n" +
                    "         \"FIELD4\": \"MLI\",\n" +
                    "         \"FIELD5\": 466,\n" +
                    "         \"FIELD6\": 17,\n" +
                    "         \"FIELD7\": -4\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Malta\",\n" +
                    "         \"FIELD3\": \"MT\",\n" +
                    "         \"FIELD4\": \"MLT\",\n" +
                    "         \"FIELD5\": 470,\n" +
                    "         \"FIELD6\": 35.8333,\n" +
                    "         \"FIELD7\": 14.5833\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Marshall Islands\",\n" +
                    "         \"FIELD3\": \"MH\",\n" +
                    "         \"FIELD4\": \"MHL\",\n" +
                    "         \"FIELD5\": 584,\n" +
                    "         \"FIELD6\": 9,\n" +
                    "         \"FIELD7\": 168\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Martinique\",\n" +
                    "         \"FIELD3\": \"MQ\",\n" +
                    "         \"FIELD4\": \"MTQ\",\n" +
                    "         \"FIELD5\": 474,\n" +
                    "         \"FIELD6\": 14.6667,\n" +
                    "         \"FIELD7\": -61\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Mauritania\",\n" +
                    "         \"FIELD3\": \"MR\",\n" +
                    "         \"FIELD4\": \"MRT\",\n" +
                    "         \"FIELD5\": 478,\n" +
                    "         \"FIELD6\": 20,\n" +
                    "         \"FIELD7\": -12\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Mauritius\",\n" +
                    "         \"FIELD3\": \"MU\",\n" +
                    "         \"FIELD4\": \"MUS\",\n" +
                    "         \"FIELD5\": 480,\n" +
                    "         \"FIELD6\": -20.2833,\n" +
                    "         \"FIELD7\": 57.55\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Mayotte\",\n" +
                    "         \"FIELD3\": \"YT\",\n" +
                    "         \"FIELD4\": \"MYT\",\n" +
                    "         \"FIELD5\": 175,\n" +
                    "         \"FIELD6\": -12.8333,\n" +
                    "         \"FIELD7\": 45.1667\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Mexico\",\n" +
                    "         \"FIELD3\": \"MX\",\n" +
                    "         \"FIELD4\": \"MEX\",\n" +
                    "         \"FIELD5\": 484,\n" +
                    "         \"FIELD6\": 23,\n" +
                    "         \"FIELD7\": -102\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Micronesia, Federated States of\",\n" +
                    "         \"FIELD3\": \"FM\",\n" +
                    "         \"FIELD4\": \"FSM\",\n" +
                    "         \"FIELD5\": 583,\n" +
                    "         \"FIELD6\": 6.9167,\n" +
                    "         \"FIELD7\": 158.25\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Moldova, Republic of\",\n" +
                    "         \"FIELD3\": \"MD\",\n" +
                    "         \"FIELD4\": \"MDA\",\n" +
                    "         \"FIELD5\": 498,\n" +
                    "         \"FIELD6\": 47,\n" +
                    "         \"FIELD7\": 29\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Monaco\",\n" +
                    "         \"FIELD3\": \"MC\",\n" +
                    "         \"FIELD4\": \"MCO\",\n" +
                    "         \"FIELD5\": 492,\n" +
                    "         \"FIELD6\": 43.7333,\n" +
                    "         \"FIELD7\": 7.4\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Mongolia\",\n" +
                    "         \"FIELD3\": \"MN\",\n" +
                    "         \"FIELD4\": \"MNG\",\n" +
                    "         \"FIELD5\": 496,\n" +
                    "         \"FIELD6\": 46,\n" +
                    "         \"FIELD7\": 105\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Montenegro\",\n" +
                    "         \"FIELD3\": \"ME\",\n" +
                    "         \"FIELD4\": \"MNE\",\n" +
                    "         \"FIELD5\": 499,\n" +
                    "         \"FIELD6\": 42,\n" +
                    "         \"FIELD7\": 19\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Montserrat\",\n" +
                    "         \"FIELD3\": \"MS\",\n" +
                    "         \"FIELD4\": \"MSR\",\n" +
                    "         \"FIELD5\": 500,\n" +
                    "         \"FIELD6\": 16.75,\n" +
                    "         \"FIELD7\": -62.2\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Morocco\",\n" +
                    "         \"FIELD3\": \"MA\",\n" +
                    "         \"FIELD4\": \"MAR\",\n" +
                    "         \"FIELD5\": 504,\n" +
                    "         \"FIELD6\": 32,\n" +
                    "         \"FIELD7\": -5\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Mozambique\",\n" +
                    "         \"FIELD3\": \"MZ\",\n" +
                    "         \"FIELD4\": \"MOZ\",\n" +
                    "         \"FIELD5\": 508,\n" +
                    "         \"FIELD6\": -18.25,\n" +
                    "         \"FIELD7\": 35\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Myanmar\",\n" +
                    "         \"FIELD3\": \"MM\",\n" +
                    "         \"FIELD4\": \"MMR\",\n" +
                    "         \"FIELD5\": 104,\n" +
                    "         \"FIELD6\": 22,\n" +
                    "         \"FIELD7\": 98\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Burma\",\n" +
                    "         \"FIELD3\": \"MM\",\n" +
                    "         \"FIELD4\": \"MMR\",\n" +
                    "         \"FIELD5\": 104,\n" +
                    "         \"FIELD6\": 22,\n" +
                    "         \"FIELD7\": 98\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Namibia\",\n" +
                    "         \"FIELD3\": \"NA\",\n" +
                    "         \"FIELD4\": \"NAM\",\n" +
                    "         \"FIELD5\": 516,\n" +
                    "         \"FIELD6\": -22,\n" +
                    "         \"FIELD7\": 17\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Nauru\",\n" +
                    "         \"FIELD3\": \"NR\",\n" +
                    "         \"FIELD4\": \"NRU\",\n" +
                    "         \"FIELD5\": 520,\n" +
                    "         \"FIELD6\": -0.5333,\n" +
                    "         \"FIELD7\": 166.9167\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Nepal\",\n" +
                    "         \"FIELD3\": \"NP\",\n" +
                    "         \"FIELD4\": \"NPL\",\n" +
                    "         \"FIELD5\": 524,\n" +
                    "         \"FIELD6\": 28,\n" +
                    "         \"FIELD7\": 84\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Netherlands\",\n" +
                    "         \"FIELD3\": \"NL\",\n" +
                    "         \"FIELD4\": \"NLD\",\n" +
                    "         \"FIELD5\": 528,\n" +
                    "         \"FIELD6\": 52.5,\n" +
                    "         \"FIELD7\": 5.75\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Netherlands Antilles\",\n" +
                    "         \"FIELD3\": \"AN\",\n" +
                    "         \"FIELD4\": \"ANT\",\n" +
                    "         \"FIELD5\": 530,\n" +
                    "         \"FIELD6\": 12.25,\n" +
                    "         \"FIELD7\": -68.75\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"New Caledonia\",\n" +
                    "         \"FIELD3\": \"NC\",\n" +
                    "         \"FIELD4\": \"NCL\",\n" +
                    "         \"FIELD5\": 540,\n" +
                    "         \"FIELD6\": -21.5,\n" +
                    "         \"FIELD7\": 165.5\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"New Zealand\",\n" +
                    "         \"FIELD3\": \"NZ\",\n" +
                    "         \"FIELD4\": \"NZL\",\n" +
                    "         \"FIELD5\": 554,\n" +
                    "         \"FIELD6\": -41,\n" +
                    "         \"FIELD7\": 174\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Nicaragua\",\n" +
                    "         \"FIELD3\": \"NI\",\n" +
                    "         \"FIELD4\": \"NIC\",\n" +
                    "         \"FIELD5\": 558,\n" +
                    "         \"FIELD6\": 13,\n" +
                    "         \"FIELD7\": -85\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Niger\",\n" +
                    "         \"FIELD3\": \"NE\",\n" +
                    "         \"FIELD4\": \"NER\",\n" +
                    "         \"FIELD5\": 562,\n" +
                    "         \"FIELD6\": 16,\n" +
                    "         \"FIELD7\": 8\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Nigeria\",\n" +
                    "         \"FIELD3\": \"NG\",\n" +
                    "         \"FIELD4\": \"NGA\",\n" +
                    "         \"FIELD5\": 566,\n" +
                    "         \"FIELD6\": 10,\n" +
                    "         \"FIELD7\": 8\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Niue\",\n" +
                    "         \"FIELD3\": \"NU\",\n" +
                    "         \"FIELD4\": \"NIU\",\n" +
                    "         \"FIELD5\": 570,\n" +
                    "         \"FIELD6\": -19.0333,\n" +
                    "         \"FIELD7\": -169.8667\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Norfolk Island\",\n" +
                    "         \"FIELD3\": \"NF\",\n" +
                    "         \"FIELD4\": \"NFK\",\n" +
                    "         \"FIELD5\": 574,\n" +
                    "         \"FIELD6\": -29.0333,\n" +
                    "         \"FIELD7\": 167.95\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Northern Mariana Islands\",\n" +
                    "         \"FIELD3\": \"MP\",\n" +
                    "         \"FIELD4\": \"MNP\",\n" +
                    "         \"FIELD5\": 580,\n" +
                    "         \"FIELD6\": 15.2,\n" +
                    "         \"FIELD7\": 145.75\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Norway\",\n" +
                    "         \"FIELD3\": \"NO\",\n" +
                    "         \"FIELD4\": \"NOR\",\n" +
                    "         \"FIELD5\": 578,\n" +
                    "         \"FIELD6\": 62,\n" +
                    "         \"FIELD7\": 10\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Oman\",\n" +
                    "         \"FIELD3\": \"OM\",\n" +
                    "         \"FIELD4\": \"OMN\",\n" +
                    "         \"FIELD5\": 512,\n" +
                    "         \"FIELD6\": 21,\n" +
                    "         \"FIELD7\": 57\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Pakistan\",\n" +
                    "         \"FIELD3\": \"PK\",\n" +
                    "         \"FIELD4\": \"PAK\",\n" +
                    "         \"FIELD5\": 586,\n" +
                    "         \"FIELD6\": 30,\n" +
                    "         \"FIELD7\": 70\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Palau\",\n" +
                    "         \"FIELD3\": \"PW\",\n" +
                    "         \"FIELD4\": \"PLW\",\n" +
                    "         \"FIELD5\": 585,\n" +
                    "         \"FIELD6\": 7.5,\n" +
                    "         \"FIELD7\": 134.5\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Palestinian Territory, Occupied\",\n" +
                    "         \"FIELD3\": \"PS\",\n" +
                    "         \"FIELD4\": \"PSE\",\n" +
                    "         \"FIELD5\": 275,\n" +
                    "         \"FIELD6\": 32,\n" +
                    "         \"FIELD7\": 35.25\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Panama\",\n" +
                    "         \"FIELD3\": \"PA\",\n" +
                    "         \"FIELD4\": \"PAN\",\n" +
                    "         \"FIELD5\": 591,\n" +
                    "         \"FIELD6\": 9,\n" +
                    "         \"FIELD7\": -80\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Papua New Guinea\",\n" +
                    "         \"FIELD3\": \"PG\",\n" +
                    "         \"FIELD4\": \"PNG\",\n" +
                    "         \"FIELD5\": 598,\n" +
                    "         \"FIELD6\": -6,\n" +
                    "         \"FIELD7\": 147\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Paraguay\",\n" +
                    "         \"FIELD3\": \"PY\",\n" +
                    "         \"FIELD4\": \"PRY\",\n" +
                    "         \"FIELD5\": 600,\n" +
                    "         \"FIELD6\": -23,\n" +
                    "         \"FIELD7\": -58\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Peru\",\n" +
                    "         \"FIELD3\": \"PE\",\n" +
                    "         \"FIELD4\": \"PER\",\n" +
                    "         \"FIELD5\": 604,\n" +
                    "         \"FIELD6\": -10,\n" +
                    "         \"FIELD7\": -76\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Philippines\",\n" +
                    "         \"FIELD3\": \"PH\",\n" +
                    "         \"FIELD4\": \"PHL\",\n" +
                    "         \"FIELD5\": 608,\n" +
                    "         \"FIELD6\": 13,\n" +
                    "         \"FIELD7\": 122\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Pitcairn\",\n" +
                    "         \"FIELD3\": \"PN\",\n" +
                    "         \"FIELD4\": \"PCN\",\n" +
                    "         \"FIELD5\": 612,\n" +
                    "         \"FIELD6\": -24.7,\n" +
                    "         \"FIELD7\": -127.4\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Poland\",\n" +
                    "         \"FIELD3\": \"PL\",\n" +
                    "         \"FIELD4\": \"POL\",\n" +
                    "         \"FIELD5\": 616,\n" +
                    "         \"FIELD6\": 52,\n" +
                    "         \"FIELD7\": 20\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Portugal\",\n" +
                    "         \"FIELD3\": \"PT\",\n" +
                    "         \"FIELD4\": \"PRT\",\n" +
                    "         \"FIELD5\": 620,\n" +
                    "         \"FIELD6\": 39.5,\n" +
                    "         \"FIELD7\": -8\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Puerto Rico\",\n" +
                    "         \"FIELD3\": \"PR\",\n" +
                    "         \"FIELD4\": \"PRI\",\n" +
                    "         \"FIELD5\": 630,\n" +
                    "         \"FIELD6\": 18.25,\n" +
                    "         \"FIELD7\": -66.5\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Qatar\",\n" +
                    "         \"FIELD3\": \"QA\",\n" +
                    "         \"FIELD4\": \"QAT\",\n" +
                    "         \"FIELD5\": 634,\n" +
                    "         \"FIELD6\": 25.5,\n" +
                    "         \"FIELD7\": 51.25\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Réunion\",\n" +
                    "         \"FIELD3\": \"RE\",\n" +
                    "         \"FIELD4\": \"REU\",\n" +
                    "         \"FIELD5\": 638,\n" +
                    "         \"FIELD6\": -21.1,\n" +
                    "         \"FIELD7\": 55.6\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Romania\",\n" +
                    "         \"FIELD3\": \"RO\",\n" +
                    "         \"FIELD4\": \"ROU\",\n" +
                    "         \"FIELD5\": 642,\n" +
                    "         \"FIELD6\": 46,\n" +
                    "         \"FIELD7\": 25\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Russian Federation\",\n" +
                    "         \"FIELD3\": \"RU\",\n" +
                    "         \"FIELD4\": \"RUS\",\n" +
                    "         \"FIELD5\": 643,\n" +
                    "         \"FIELD6\": 60,\n" +
                    "         \"FIELD7\": 100\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Russia\",\n" +
                    "         \"FIELD3\": \"RU\",\n" +
                    "         \"FIELD4\": \"RUS\",\n" +
                    "         \"FIELD5\": 643,\n" +
                    "         \"FIELD6\": 60,\n" +
                    "         \"FIELD7\": 100\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Rwanda\",\n" +
                    "         \"FIELD3\": \"RW\",\n" +
                    "         \"FIELD4\": \"RWA\",\n" +
                    "         \"FIELD5\": 646,\n" +
                    "         \"FIELD6\": -2,\n" +
                    "         \"FIELD7\": 30\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Saint Helena, Ascension and Tristan da Cunha\",\n" +
                    "         \"FIELD3\": \"SH\",\n" +
                    "         \"FIELD4\": \"SHN\",\n" +
                    "         \"FIELD5\": 654,\n" +
                    "         \"FIELD6\": -15.9333,\n" +
                    "         \"FIELD7\": -5.7\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Saint Kitts and Nevis\",\n" +
                    "         \"FIELD3\": \"KN\",\n" +
                    "         \"FIELD4\": \"KNA\",\n" +
                    "         \"FIELD5\": 659,\n" +
                    "         \"FIELD6\": 17.3333,\n" +
                    "         \"FIELD7\": -62.75\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Saint Lucia\",\n" +
                    "         \"FIELD3\": \"LC\",\n" +
                    "         \"FIELD4\": \"LCA\",\n" +
                    "         \"FIELD5\": 662,\n" +
                    "         \"FIELD6\": 13.8833,\n" +
                    "         \"FIELD7\": -61.1333\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Saint Pierre and Miquelon\",\n" +
                    "         \"FIELD3\": \"PM\",\n" +
                    "         \"FIELD4\": \"SPM\",\n" +
                    "         \"FIELD5\": 666,\n" +
                    "         \"FIELD6\": 46.8333,\n" +
                    "         \"FIELD7\": -56.3333\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Saint Vincent and the Grenadines\",\n" +
                    "         \"FIELD3\": \"VC\",\n" +
                    "         \"FIELD4\": \"VCT\",\n" +
                    "         \"FIELD5\": 670,\n" +
                    "         \"FIELD6\": 13.25,\n" +
                    "         \"FIELD7\": -61.2\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Saint Vincent & the Grenadines\",\n" +
                    "         \"FIELD3\": \"VC\",\n" +
                    "         \"FIELD4\": \"VCT\",\n" +
                    "         \"FIELD5\": 670,\n" +
                    "         \"FIELD6\": 13.25,\n" +
                    "         \"FIELD7\": -61.2\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"St. Vincent and the Grenadines\",\n" +
                    "         \"FIELD3\": \"VC\",\n" +
                    "         \"FIELD4\": \"VCT\",\n" +
                    "         \"FIELD5\": 670,\n" +
                    "         \"FIELD6\": 13.25,\n" +
                    "         \"FIELD7\": -61.2\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Samoa\",\n" +
                    "         \"FIELD3\": \"WS\",\n" +
                    "         \"FIELD4\": \"WSM\",\n" +
                    "         \"FIELD5\": 882,\n" +
                    "         \"FIELD6\": -13.5833,\n" +
                    "         \"FIELD7\": -172.3333\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"San Marino\",\n" +
                    "         \"FIELD3\": \"SM\",\n" +
                    "         \"FIELD4\": \"SMR\",\n" +
                    "         \"FIELD5\": 674,\n" +
                    "         \"FIELD6\": 43.7667,\n" +
                    "         \"FIELD7\": 12.4167\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Sao Tome and Principe\",\n" +
                    "         \"FIELD3\": \"ST\",\n" +
                    "         \"FIELD4\": \"STP\",\n" +
                    "         \"FIELD5\": 678,\n" +
                    "         \"FIELD6\": 1,\n" +
                    "         \"FIELD7\": 7\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Saudi Arabia\",\n" +
                    "         \"FIELD3\": \"SA\",\n" +
                    "         \"FIELD4\": \"SAU\",\n" +
                    "         \"FIELD5\": 682,\n" +
                    "         \"FIELD6\": 25,\n" +
                    "         \"FIELD7\": 45\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Senegal\",\n" +
                    "         \"FIELD3\": \"SN\",\n" +
                    "         \"FIELD4\": \"SEN\",\n" +
                    "         \"FIELD5\": 686,\n" +
                    "         \"FIELD6\": 14,\n" +
                    "         \"FIELD7\": -14\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Serbia\",\n" +
                    "         \"FIELD3\": \"RS\",\n" +
                    "         \"FIELD4\": \"SRB\",\n" +
                    "         \"FIELD5\": 688,\n" +
                    "         \"FIELD6\": 44,\n" +
                    "         \"FIELD7\": 21\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Seychelles\",\n" +
                    "         \"FIELD3\": \"SC\",\n" +
                    "         \"FIELD4\": \"SYC\",\n" +
                    "         \"FIELD5\": 690,\n" +
                    "         \"FIELD6\": -4.5833,\n" +
                    "         \"FIELD7\": 55.6667\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Sierra Leone\",\n" +
                    "         \"FIELD3\": \"SL\",\n" +
                    "         \"FIELD4\": \"SLE\",\n" +
                    "         \"FIELD5\": 694,\n" +
                    "         \"FIELD6\": 8.5,\n" +
                    "         \"FIELD7\": -11.5\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Singapore\",\n" +
                    "         \"FIELD3\": \"SG\",\n" +
                    "         \"FIELD4\": \"SGP\",\n" +
                    "         \"FIELD5\": 702,\n" +
                    "         \"FIELD6\": 1.3667,\n" +
                    "         \"FIELD7\": 103.8\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Slovakia\",\n" +
                    "         \"FIELD3\": \"SK\",\n" +
                    "         \"FIELD4\": \"SVK\",\n" +
                    "         \"FIELD5\": 703,\n" +
                    "         \"FIELD6\": 48.6667,\n" +
                    "         \"FIELD7\": 19.5\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Slovenia\",\n" +
                    "         \"FIELD3\": \"SI\",\n" +
                    "         \"FIELD4\": \"SVN\",\n" +
                    "         \"FIELD5\": 705,\n" +
                    "         \"FIELD6\": 46,\n" +
                    "         \"FIELD7\": 15\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Solomon Islands\",\n" +
                    "         \"FIELD3\": \"SB\",\n" +
                    "         \"FIELD4\": \"SLB\",\n" +
                    "         \"FIELD5\": 90,\n" +
                    "         \"FIELD6\": -8,\n" +
                    "         \"FIELD7\": 159\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Somalia\",\n" +
                    "         \"FIELD3\": \"SO\",\n" +
                    "         \"FIELD4\": \"SOM\",\n" +
                    "         \"FIELD5\": 706,\n" +
                    "         \"FIELD6\": 10,\n" +
                    "         \"FIELD7\": 49\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"South Africa\",\n" +
                    "         \"FIELD3\": \"ZA\",\n" +
                    "         \"FIELD4\": \"ZAF\",\n" +
                    "         \"FIELD5\": 710,\n" +
                    "         \"FIELD6\": -29,\n" +
                    "         \"FIELD7\": 24\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"South Georgia and the South Sandwich Islands\",\n" +
                    "         \"FIELD3\": \"GS\",\n" +
                    "         \"FIELD4\": \"SGS\",\n" +
                    "         \"FIELD5\": 239,\n" +
                    "         \"FIELD6\": -54.5,\n" +
                    "         \"FIELD7\": -37\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Spain\",\n" +
                    "         \"FIELD3\": \"ES\",\n" +
                    "         \"FIELD4\": \"ESP\",\n" +
                    "         \"FIELD5\": 724,\n" +
                    "         \"FIELD6\": 40,\n" +
                    "         \"FIELD7\": -4\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Sri Lanka\",\n" +
                    "         \"FIELD3\": \"LK\",\n" +
                    "         \"FIELD4\": \"LKA\",\n" +
                    "         \"FIELD5\": 144,\n" +
                    "         \"FIELD6\": 7,\n" +
                    "         \"FIELD7\": 81\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Sudan\",\n" +
                    "         \"FIELD3\": \"SD\",\n" +
                    "         \"FIELD4\": \"SDN\",\n" +
                    "         \"FIELD5\": 736,\n" +
                    "         \"FIELD6\": 15,\n" +
                    "         \"FIELD7\": 30\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Suriname\",\n" +
                    "         \"FIELD3\": \"SR\",\n" +
                    "         \"FIELD4\": \"SUR\",\n" +
                    "         \"FIELD5\": 740,\n" +
                    "         \"FIELD6\": 4,\n" +
                    "         \"FIELD7\": -56\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Svalbard and Jan Mayen\",\n" +
                    "         \"FIELD3\": \"SJ\",\n" +
                    "         \"FIELD4\": \"SJM\",\n" +
                    "         \"FIELD5\": 744,\n" +
                    "         \"FIELD6\": 78,\n" +
                    "         \"FIELD7\": 20\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Swaziland\",\n" +
                    "         \"FIELD3\": \"SZ\",\n" +
                    "         \"FIELD4\": \"SWZ\",\n" +
                    "         \"FIELD5\": 748,\n" +
                    "         \"FIELD6\": -26.5,\n" +
                    "         \"FIELD7\": 31.5\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Sweden\",\n" +
                    "         \"FIELD3\": \"SE\",\n" +
                    "         \"FIELD4\": \"SWE\",\n" +
                    "         \"FIELD5\": 752,\n" +
                    "         \"FIELD6\": 62,\n" +
                    "         \"FIELD7\": 15\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Switzerland\",\n" +
                    "         \"FIELD3\": \"CH\",\n" +
                    "         \"FIELD4\": \"CHE\",\n" +
                    "         \"FIELD5\": 756,\n" +
                    "         \"FIELD6\": 47,\n" +
                    "         \"FIELD7\": 8\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Syrian Arab Republic\",\n" +
                    "         \"FIELD3\": \"SY\",\n" +
                    "         \"FIELD4\": \"SYR\",\n" +
                    "         \"FIELD5\": 760,\n" +
                    "         \"FIELD6\": 35,\n" +
                    "         \"FIELD7\": 38\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Taiwan, Province of China\",\n" +
                    "         \"FIELD3\": \"TW\",\n" +
                    "         \"FIELD4\": \"TWN\",\n" +
                    "         \"FIELD5\": 158,\n" +
                    "         \"FIELD6\": 23.5,\n" +
                    "         \"FIELD7\": 121\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Taiwan\",\n" +
                    "         \"FIELD3\": \"TW\",\n" +
                    "         \"FIELD4\": \"TWN\",\n" +
                    "         \"FIELD5\": 158,\n" +
                    "         \"FIELD6\": 23.5,\n" +
                    "         \"FIELD7\": 121\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Tajikistan\",\n" +
                    "         \"FIELD3\": \"TJ\",\n" +
                    "         \"FIELD4\": \"TJK\",\n" +
                    "         \"FIELD5\": 762,\n" +
                    "         \"FIELD6\": 39,\n" +
                    "         \"FIELD7\": 71\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Tanzania, United Republic of\",\n" +
                    "         \"FIELD3\": \"TZ\",\n" +
                    "         \"FIELD4\": \"TZA\",\n" +
                    "         \"FIELD5\": 834,\n" +
                    "         \"FIELD6\": -6,\n" +
                    "         \"FIELD7\": 35\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Thailand\",\n" +
                    "         \"FIELD3\": \"TH\",\n" +
                    "         \"FIELD4\": \"THA\",\n" +
                    "         \"FIELD5\": 764,\n" +
                    "         \"FIELD6\": 15,\n" +
                    "         \"FIELD7\": 100\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Timor-Leste\",\n" +
                    "         \"FIELD3\": \"TL\",\n" +
                    "         \"FIELD4\": \"TLS\",\n" +
                    "         \"FIELD5\": 626,\n" +
                    "         \"FIELD6\": -8.55,\n" +
                    "         \"FIELD7\": 125.5167\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Togo\",\n" +
                    "         \"FIELD3\": \"TG\",\n" +
                    "         \"FIELD4\": \"TGO\",\n" +
                    "         \"FIELD5\": 768,\n" +
                    "         \"FIELD6\": 8,\n" +
                    "         \"FIELD7\": 1.1667\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Tokelau\",\n" +
                    "         \"FIELD3\": \"TK\",\n" +
                    "         \"FIELD4\": \"TKL\",\n" +
                    "         \"FIELD5\": 772,\n" +
                    "         \"FIELD6\": -9,\n" +
                    "         \"FIELD7\": -172\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Tonga\",\n" +
                    "         \"FIELD3\": \"TO\",\n" +
                    "         \"FIELD4\": \"TON\",\n" +
                    "         \"FIELD5\": 776,\n" +
                    "         \"FIELD6\": -20,\n" +
                    "         \"FIELD7\": -175\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Trinidad and Tobago\",\n" +
                    "         \"FIELD3\": \"TT\",\n" +
                    "         \"FIELD4\": \"TTO\",\n" +
                    "         \"FIELD5\": 780,\n" +
                    "         \"FIELD6\": 11,\n" +
                    "         \"FIELD7\": -61\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Trinidad & Tobago\",\n" +
                    "         \"FIELD3\": \"TT\",\n" +
                    "         \"FIELD4\": \"TTO\",\n" +
                    "         \"FIELD5\": 780,\n" +
                    "         \"FIELD6\": 11,\n" +
                    "         \"FIELD7\": -61\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Tunisia\",\n" +
                    "         \"FIELD3\": \"TN\",\n" +
                    "         \"FIELD4\": \"TUN\",\n" +
                    "         \"FIELD5\": 788,\n" +
                    "         \"FIELD6\": 34,\n" +
                    "         \"FIELD7\": 9\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Turkey\",\n" +
                    "         \"FIELD3\": \"TR\",\n" +
                    "         \"FIELD4\": \"TUR\",\n" +
                    "         \"FIELD5\": 792,\n" +
                    "         \"FIELD6\": 39,\n" +
                    "         \"FIELD7\": 35\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Turkmenistan\",\n" +
                    "         \"FIELD3\": \"TM\",\n" +
                    "         \"FIELD4\": \"TKM\",\n" +
                    "         \"FIELD5\": 795,\n" +
                    "         \"FIELD6\": 40,\n" +
                    "         \"FIELD7\": 60\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Turks and Caicos Islands\",\n" +
                    "         \"FIELD3\": \"TC\",\n" +
                    "         \"FIELD4\": \"TCA\",\n" +
                    "         \"FIELD5\": 796,\n" +
                    "         \"FIELD6\": 21.75,\n" +
                    "         \"FIELD7\": -71.5833\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Tuvalu\",\n" +
                    "         \"FIELD3\": \"TV\",\n" +
                    "         \"FIELD4\": \"TUV\",\n" +
                    "         \"FIELD5\": 798,\n" +
                    "         \"FIELD6\": -8,\n" +
                    "         \"FIELD7\": 178\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Uganda\",\n" +
                    "         \"FIELD3\": \"UG\",\n" +
                    "         \"FIELD4\": \"UGA\",\n" +
                    "         \"FIELD5\": 800,\n" +
                    "         \"FIELD6\": 1,\n" +
                    "         \"FIELD7\": 32\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Ukraine\",\n" +
                    "         \"FIELD3\": \"UA\",\n" +
                    "         \"FIELD4\": \"UKR\",\n" +
                    "         \"FIELD5\": 804,\n" +
                    "         \"FIELD6\": 49,\n" +
                    "         \"FIELD7\": 32\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"United Arab Emirates\",\n" +
                    "         \"FIELD3\": \"AE\",\n" +
                    "         \"FIELD4\": \"ARE\",\n" +
                    "         \"FIELD5\": 784,\n" +
                    "         \"FIELD6\": 24,\n" +
                    "         \"FIELD7\": 54\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"United Kingdom\",\n" +
                    "         \"FIELD3\": \"GB\",\n" +
                    "         \"FIELD4\": \"GBR\",\n" +
                    "         \"FIELD5\": 826,\n" +
                    "         \"FIELD6\": 54,\n" +
                    "         \"FIELD7\": -2\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"United States\",\n" +
                    "         \"FIELD3\": \"US\",\n" +
                    "         \"FIELD4\": \"USA\",\n" +
                    "         \"FIELD5\": 840,\n" +
                    "         \"FIELD6\": 38,\n" +
                    "         \"FIELD7\": -97\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"United States Minor Outlying Islands\",\n" +
                    "         \"FIELD3\": \"UM\",\n" +
                    "         \"FIELD4\": \"UMI\",\n" +
                    "         \"FIELD5\": 581,\n" +
                    "         \"FIELD6\": 19.2833,\n" +
                    "         \"FIELD7\": 166.6\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Uruguay\",\n" +
                    "         \"FIELD3\": \"UY\",\n" +
                    "         \"FIELD4\": \"URY\",\n" +
                    "         \"FIELD5\": 858,\n" +
                    "         \"FIELD6\": -33,\n" +
                    "         \"FIELD7\": -56\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Uzbekistan\",\n" +
                    "         \"FIELD3\": \"UZ\",\n" +
                    "         \"FIELD4\": \"UZB\",\n" +
                    "         \"FIELD5\": 860,\n" +
                    "         \"FIELD6\": 41,\n" +
                    "         \"FIELD7\": 64\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Vanuatu\",\n" +
                    "         \"FIELD3\": \"VU\",\n" +
                    "         \"FIELD4\": \"VUT\",\n" +
                    "         \"FIELD5\": 548,\n" +
                    "         \"FIELD6\": -16,\n" +
                    "         \"FIELD7\": 167\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Venezuela, Bolivarian Republic of\",\n" +
                    "         \"FIELD3\": \"VE\",\n" +
                    "         \"FIELD4\": \"VEN\",\n" +
                    "         \"FIELD5\": 862,\n" +
                    "         \"FIELD6\": 8,\n" +
                    "         \"FIELD7\": -66\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Venezuela\",\n" +
                    "         \"FIELD3\": \"VE\",\n" +
                    "         \"FIELD4\": \"VEN\",\n" +
                    "         \"FIELD5\": 862,\n" +
                    "         \"FIELD6\": 8,\n" +
                    "         \"FIELD7\": -66\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Viet Nam\",\n" +
                    "         \"FIELD3\": \"VN\",\n" +
                    "         \"FIELD4\": \"VNM\",\n" +
                    "         \"FIELD5\": 704,\n" +
                    "         \"FIELD6\": 16,\n" +
                    "         \"FIELD7\": 106\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Vietnam\",\n" +
                    "         \"FIELD3\": \"VN\",\n" +
                    "         \"FIELD4\": \"VNM\",\n" +
                    "         \"FIELD5\": 704,\n" +
                    "         \"FIELD6\": 16,\n" +
                    "         \"FIELD7\": 106\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Virgin Islands, British\",\n" +
                    "         \"FIELD3\": \"VG\",\n" +
                    "         \"FIELD4\": \"VGB\",\n" +
                    "         \"FIELD5\": 92,\n" +
                    "         \"FIELD6\": 18.5,\n" +
                    "         \"FIELD7\": -64.5\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Virgin Islands, U.S.\",\n" +
                    "         \"FIELD3\": \"VI\",\n" +
                    "         \"FIELD4\": \"VIR\",\n" +
                    "         \"FIELD5\": 850,\n" +
                    "         \"FIELD6\": 18.3333,\n" +
                    "         \"FIELD7\": -64.8333\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Wallis and Futuna\",\n" +
                    "         \"FIELD3\": \"WF\",\n" +
                    "         \"FIELD4\": \"WLF\",\n" +
                    "         \"FIELD5\": 876,\n" +
                    "         \"FIELD6\": -13.3,\n" +
                    "         \"FIELD7\": -176.2\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Western Sahara\",\n" +
                    "         \"FIELD3\": \"EH\",\n" +
                    "         \"FIELD4\": \"ESH\",\n" +
                    "         \"FIELD5\": 732,\n" +
                    "         \"FIELD6\": 24.5,\n" +
                    "         \"FIELD7\": -13\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Yemen\",\n" +
                    "         \"FIELD3\": \"YE\",\n" +
                    "         \"FIELD4\": \"YEM\",\n" +
                    "         \"FIELD5\": 887,\n" +
                    "         \"FIELD6\": 15,\n" +
                    "         \"FIELD7\": 48\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Zambia\",\n" +
                    "         \"FIELD3\": \"ZM\",\n" +
                    "         \"FIELD4\": \"ZMB\",\n" +
                    "         \"FIELD5\": 894,\n" +
                    "         \"FIELD6\": -15,\n" +
                    "         \"FIELD7\": 30\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"FIELD2\": \"Zimbabwe\",\n" +
                    "         \"FIELD3\": \"ZW\",\n" +
                    "         \"FIELD4\": \"ZWE\",\n" +
                    "         \"FIELD5\": 716,\n" +
                    "         \"FIELD6\": -20,\n" +
                    "         \"FIELD7\": 30\n" +
                    "      }\n" +
                    "   ]\n" +
                    "}") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }






}
