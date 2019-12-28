package com.company.alibaba.utils;

import com.company.alibaba.entities.Currency;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class Currencies {
    JSONObject currencies ;

    public ArrayList<Currency> getCurrencies(){
        ArrayList<Currency> currencies = new ArrayList<>() ;
        try {
            JSONArray currenciesJsonArr = this.currencies.getJSONArray("Currencies") ;
            for (int i = 0; i < currenciesJsonArr.length() ; i++) {
                JSONObject currencyJson = currenciesJsonArr.getJSONObject(i) ;
                Currency currency = new Currency() ;
                currency.setCurrencyCode(currencyJson.getString("CODE"));
                currency.setCurrecnyName(currencyJson.getString("NAME"));
                currency.setCurrencySymbol(currencyJson.getString("SYMBOL"));
                currency.setCurrencyId(i);
                currencies.add(currency) ;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return currencies ;
    }

    public Currencies(){
        try {
            currencies = new JSONObject("{\n" +
                    "    \"Currencies\": [\n" +
                    "        {\n" +
                    "            \"id\": \"1\",\n" +
                    "            \"CODE\": \"AED\",\n" +
                    "            \"SYMBOL\": \"AED\",\n" +
                    "            \"NAME\": \"UAE Dirham\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"2\",\n" +
                    "            \"CODE\": \"AFN\",\n" +
                    "            \"SYMBOL\": \"Af\",\n" +
                    "            \"NAME\": \"Afghani\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"3\",\n" +
                    "            \"CODE\": \"ALL\",\n" +
                    "            \"SYMBOL\": \"L\",\n" +
                    "            \"NAME\": \"Lek\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"4\",\n" +
                    "            \"CODE\": \"AMD\",\n" +
                    "            \"SYMBOL\": \"AMD\",\n" +
                    "            \"NAME\": \"Armenian Dram\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"6\",\n" +
                    "            \"CODE\": \"AOA\",\n" +
                    "            \"SYMBOL\": \"Kz\",\n" +
                    "            \"NAME\": \"Kwanza\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"7\",\n" +
                    "            \"CODE\": \"ARS\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"Argentine Peso\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"8\",\n" +
                    "            \"CODE\": \"AUD\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"Australian Dollar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"9\",\n" +
                    "            \"CODE\": \"AWG\",\n" +
                    "            \"SYMBOL\": \"ƒ\",\n" +
                    "            \"NAME\": \"Aruban Guilder/Florin\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"10\",\n" +
                    "            \"CODE\": \"AZN\",\n" +
                    "            \"SYMBOL\": \"AZN\",\n" +
                    "            \"NAME\": \"Azerbaijanian Manat\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"11\",\n" +
                    "            \"CODE\": \"BAM\",\n" +
                    "            \"SYMBOL\": \"BAM\\r\\n\",\n" +
                    "            \"NAME\": \"Konvertibilna Marka\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"12\",\n" +
                    "            \"CODE\": \"BBD\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"Barbados Dollar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"13\",\n" +
                    "            \"CODE\": \"BDT\",\n" +
                    "            \"SYMBOL\": \"BDT\",\n" +
                    "            \"NAME\": \"Taka\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"14\",\n" +
                    "            \"CODE\": \"BGN\",\n" +
                    "            \"SYMBOL\": \"BGN\",\n" +
                    "            \"NAME\": \"Bulgarian Lev\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"15\",\n" +
                    "            \"CODE\": \"BHD\",\n" +
                    "            \"SYMBOL\": \"BHD\",\n" +
                    "            \"NAME\": \"Bahraini Dinar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"16\",\n" +
                    "            \"CODE\": \"BIF\",\n" +
                    "            \"SYMBOL\": \"BIF\",\n" +
                    "            \"NAME\": \"Burundi Franc\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"17\",\n" +
                    "            \"CODE\": \"BMD\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"Bermudian Dollar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"18\",\n" +
                    "            \"CODE\": \"BND\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"Brunei Dollar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"19\",\n" +
                    "            \"CODE\": \"BOB\",\n" +
                    "            \"SYMBOL\": \"Bs.\",\n" +
                    "            \"NAME\": \"Boliviano\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"20\",\n" +
                    "            \"CODE\": \"BRL\",\n" +
                    "            \"SYMBOL\": \"R$\",\n" +
                    "            \"NAME\": \"Brazilian Real\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"21\",\n" +
                    "            \"CODE\": \"BSD\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"Bahamian Dollar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"23\",\n" +
                    "            \"CODE\": \"BTN\",\n" +
                    "            \"SYMBOL\": \"BTN\",\n" +
                    "            \"NAME\": \"Ngultrum\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"24\",\n" +
                    "            \"CODE\": \"BWP\",\n" +
                    "            \"SYMBOL\": \"P\",\n" +
                    "            \"NAME\": \"Pula\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"26\",\n" +
                    "            \"CODE\": \"BYR\",\n" +
                    "            \"SYMBOL\": \"Br\",\n" +
                    "            \"NAME\": \"Belarussian Ruble\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"27\",\n" +
                    "            \"CODE\": \"BZD\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"Belize Dollar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"28\",\n" +
                    "            \"CODE\": \"CAD\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"Canadian Dollar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"29\",\n" +
                    "            \"CODE\": \"CDF\",\n" +
                    "            \"SYMBOL\": \"CDF\",\n" +
                    "            \"NAME\": \"Congolese Franc\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"30\",\n" +
                    "            \"CODE\": \"CHF\",\n" +
                    "            \"SYMBOL\": \"CHF\",\n" +
                    "            \"NAME\": \"Swiss Franc\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"32\",\n" +
                    "            \"CODE\": \"CLP\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"Chilean Peso\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"33\",\n" +
                    "            \"CODE\": \"CNY\",\n" +
                    "            \"SYMBOL\": \"¥\",\n" +
                    "            \"NAME\": \"Yuan\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"34\",\n" +
                    "            \"CODE\": \"COP\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"Colombian Peso\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"35\",\n" +
                    "            \"CODE\": \"CRC\",\n" +
                    "            \"SYMBOL\": \"CRC\",\n" +
                    "            \"NAME\": \"Costa Rican Colon\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"37\",\n" +
                    "            \"CODE\": \"CUP\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"Cuban Peso\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"38\",\n" +
                    "            \"CODE\": \"CVE\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"Cape Verde Escudo\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"39\",\n" +
                    "            \"CODE\": \"CZK\",\n" +
                    "            \"SYMBOL\": \"CZK\",\n" +
                    "            \"NAME\": \"Czech Koruna\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"40\",\n" +
                    "            \"CODE\": \"DJF\",\n" +
                    "            \"SYMBOL\": \"DJF\",\n" +
                    "            \"NAME\": \"Djibouti Franc\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"41\",\n" +
                    "            \"CODE\": \"DKK\",\n" +
                    "            \"SYMBOL\": \"kr\",\n" +
                    "            \"NAME\": \"Danish Krone\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"42\",\n" +
                    "            \"CODE\": \"DOP\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"Dominican Peso\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"43\",\n" +
                    "            \"CODE\": \"DZD\",\n" +
                    "            \"SYMBOL\": \"DZD\",\n" +
                    "            \"NAME\": \"Algerian Dinar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"44\",\n" +
                    "            \"CODE\": \"EGP\",\n" +
                    "            \"SYMBOL\": \"£\",\n" +
                    "            \"NAME\": \"Egyptian Pound\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"45\",\n" +
                    "            \"CODE\": \"ERN\",\n" +
                    "            \"SYMBOL\": \"Nfk\",\n" +
                    "            \"NAME\": \"Nakfa\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"46\",\n" +
                    "            \"CODE\": \"ETB\",\n" +
                    "            \"SYMBOL\": \"\",\n" +
                    "            \"NAME\": \"Ethiopian Birr\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"47\",\n" +
                    "            \"CODE\": \"EUR\",\n" +
                    "            \"SYMBOL\": \"€\",\n" +
                    "            \"NAME\": \"Euro\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"48\",\n" +
                    "            \"CODE\": \"FJD\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"Fiji Dollar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"49\",\n" +
                    "            \"CODE\": \"FKP\",\n" +
                    "            \"SYMBOL\": \"£\",\n" +
                    "            \"NAME\": \"Falkland Islands Pound\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"50\",\n" +
                    "            \"CODE\": \"GBP\",\n" +
                    "            \"SYMBOL\": \"£\",\n" +
                    "            \"NAME\": \"Pound Sterling\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"51\",\n" +
                    "            \"CODE\": \"GEL\",\n" +
                    "            \"SYMBOL\": \"GEL\",\n" +
                    "            \"NAME\": \"Lari\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"53\",\n" +
                    "            \"CODE\": \"GHS\",\n" +
                    "            \"SYMBOL\": \"GHS\",\n" +
                    "            \"NAME\": \"Cedi\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"54\",\n" +
                    "            \"CODE\": \"GIP\",\n" +
                    "            \"SYMBOL\": \"£\",\n" +
                    "            \"NAME\": \"Gibraltar Pound\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"55\",\n" +
                    "            \"CODE\": \"GMD\",\n" +
                    "            \"SYMBOL\": \"D\",\n" +
                    "            \"NAME\": \"Dalasi\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"56\",\n" +
                    "            \"CODE\": \"GNF\",\n" +
                    "            \"SYMBOL\": \"GNF\",\n" +
                    "            \"NAME\": \"Guinea Franc\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"57\",\n" +
                    "            \"CODE\": \"GTQ\",\n" +
                    "            \"SYMBOL\": \"Q\",\n" +
                    "            \"NAME\": \"Quetzal\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"58\",\n" +
                    "            \"CODE\": \"GYD\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"Guyana Dollar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"59\",\n" +
                    "            \"CODE\": \"HKD\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"Hong Kong Dollar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"60\",\n" +
                    "            \"CODE\": \"HNL\",\n" +
                    "            \"SYMBOL\": \"L\",\n" +
                    "            \"NAME\": \"Lempira\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"61\",\n" +
                    "            \"CODE\": \"HRK\",\n" +
                    "            \"SYMBOL\": \"Kn\",\n" +
                    "            \"NAME\": \"Croatian Kuna\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"62\",\n" +
                    "            \"CODE\": \"HTG\",\n" +
                    "            \"SYMBOL\": \"G\",\n" +
                    "            \"NAME\": \"Gourde\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"63\",\n" +
                    "            \"CODE\": \"HUF\",\n" +
                    "            \"SYMBOL\": \"Ft\",\n" +
                    "            \"NAME\": \"Forint\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"64\",\n" +
                    "            \"CODE\": \"IDR\",\n" +
                    "            \"SYMBOL\": \"Rp\",\n" +
                    "            \"NAME\": \"Rupiah\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"65\",\n" +
                    "            \"CODE\": \"ILS\",\n" +
                    "            \"SYMBOL\": \"ILS\",\n" +
                    "            \"NAME\": \"New Israeli Shekel\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"67\",\n" +
                    "            \"CODE\": \"INR\",\n" +
                    "            \"SYMBOL\": \"INR\",\n" +
                    "            \"NAME\": \"Indian Rupee\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"68\",\n" +
                    "            \"CODE\": \"IQD\",\n" +
                    "            \"SYMBOL\": \"IQD\",\n" +
                    "            \"NAME\": \"Iraqi Dinar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"69\",\n" +
                    "            \"CODE\": \"IRR\",\n" +
                    "            \"SYMBOL\": \"IRR\",\n" +
                    "            \"NAME\": \"Iranian Rial\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"70\",\n" +
                    "            \"CODE\": \"ISK\",\n" +
                    "            \"SYMBOL\": \"Kr\",\n" +
                    "            \"NAME\": \"Iceland Krona\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"72\",\n" +
                    "            \"CODE\": \"JMD\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"Jamaican Dollar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"73\",\n" +
                    "            \"CODE\": \"JOD\",\n" +
                    "            \"SYMBOL\": \"JD\",\n" +
                    "            \"NAME\": \"Jordanian Dinar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"74\",\n" +
                    "            \"CODE\": \"JPY\",\n" +
                    "            \"SYMBOL\": \"¥\",\n" +
                    "            \"NAME\": \"Yen\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"75\",\n" +
                    "            \"CODE\": \"KES\",\n" +
                    "            \"SYMBOL\": \"Sh\",\n" +
                    "            \"NAME\": \"Kenyan Shilling\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"76\",\n" +
                    "            \"CODE\": \"KGS\",\n" +
                    "            \"SYMBOL\": \"KGS\",\n" +
                    "            \"NAME\": \"Som\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"77\",\n" +
                    "            \"CODE\": \"KHR\",\n" +
                    "            \"SYMBOL\": \"KHR\",\n" +
                    "            \"NAME\": \"Riel\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"79\",\n" +
                    "            \"CODE\": \"KPW\",\n" +
                    "            \"SYMBOL\": \"KPW\",\n" +
                    "            \"NAME\": \"North Korean Won\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"80\",\n" +
                    "            \"CODE\": \"KRW\",\n" +
                    "            \"SYMBOL\": \"KRW\",\n" +
                    "            \"NAME\": \"South Korean Won\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"81\",\n" +
                    "            \"CODE\": \"KWD\",\n" +
                    "            \"SYMBOL\": \"KWD\",\n" +
                    "            \"NAME\": \"Kuwaiti Dinar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"82\",\n" +
                    "            \"CODE\": \"KYD\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"Cayman Islands Dollar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"83\",\n" +
                    "            \"CODE\": \"KZT\",\n" +
                    "            \"SYMBOL\": \"KZT\",\n" +
                    "            \"NAME\": \"Tenge\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"84\",\n" +
                    "            \"CODE\": \"LAK\",\n" +
                    "            \"SYMBOL\": \"LAK\",\n" +
                    "            \"NAME\": \"Kip\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"85\",\n" +
                    "            \"CODE\": \"LBP\",\n" +
                    "            \"SYMBOL\": \"LBP\",\n" +
                    "            \"NAME\": \"Lebanese Pound\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"86\",\n" +
                    "            \"CODE\": \"LKR\",\n" +
                    "            \"SYMBOL\": \"Rs\",\n" +
                    "            \"NAME\": \"Sri Lanka Rupee\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"87\",\n" +
                    "            \"CODE\": \"LRD\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"Liberian Dollar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"88\",\n" +
                    "            \"CODE\": \"LSL\",\n" +
                    "            \"SYMBOL\": \"L\",\n" +
                    "            \"NAME\": \"Loti\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"91\",\n" +
                    "            \"CODE\": \"LYD\",\n" +
                    "            \"SYMBOL\": \"LYD\",\n" +
                    "            \"NAME\": \"Libyan Dinar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"92\",\n" +
                    "            \"CODE\": \"MAD\",\n" +
                    "            \"SYMBOL\": \"MAD.\",\n" +
                    "            \"NAME\": \"Moroccan Dirham\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"93\",\n" +
                    "            \"CODE\": \"MDL\",\n" +
                    "            \"SYMBOL\": \"L\",\n" +
                    "            \"NAME\": \"Moldavian Leu\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"94\",\n" +
                    "            \"CODE\": \"MGA\",\n" +
                    "            \"SYMBOL\": \"MGA\",\n" +
                    "            \"NAME\": \"Malagasy Ariary\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"95\",\n" +
                    "            \"CODE\": \"MKD\",\n" +
                    "            \"SYMBOL\": \"MKD\",\n" +
                    "            \"NAME\": \"Denar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"96\",\n" +
                    "            \"CODE\": \"MMK\",\n" +
                    "            \"SYMBOL\": \"K\",\n" +
                    "            \"NAME\": \"Kyat\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"97\",\n" +
                    "            \"CODE\": \"MNT\",\n" +
                    "            \"SYMBOL\": \"MNT\",\n" +
                    "            \"NAME\": \"Tugrik\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"98\",\n" +
                    "            \"CODE\": \"MOP\",\n" +
                    "            \"SYMBOL\": \"P\",\n" +
                    "            \"NAME\": \"Pataca\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"99\",\n" +
                    "            \"CODE\": \"MRO\",\n" +
                    "            \"SYMBOL\": \"UM\",\n" +
                    "            \"NAME\": \"Ouguiya\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"100\",\n" +
                    "            \"CODE\": \"MUR\",\n" +
                    "            \"SYMBOL\": \"MUR\",\n" +
                    "            \"NAME\": \"Mauritius Rupee\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"101\",\n" +
                    "            \"CODE\": \"MVR\",\n" +
                    "            \"SYMBOL\": \"MVR\",\n" +
                    "            \"NAME\": \"Rufiyaa\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"102\",\n" +
                    "            \"CODE\": \"MWK\",\n" +
                    "            \"SYMBOL\": \"MK\",\n" +
                    "            \"NAME\": \"Kwacha\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"103\",\n" +
                    "            \"CODE\": \"MXN\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"Mexican Peso\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"104\",\n" +
                    "            \"CODE\": \"MYR\",\n" +
                    "            \"SYMBOL\": \"RM\",\n" +
                    "            \"NAME\": \"Malaysian Ringgit\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"105\",\n" +
                    "            \"CODE\": \"MZN\",\n" +
                    "            \"SYMBOL\": \"MTn\",\n" +
                    "            \"NAME\": \"Metical\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"106\",\n" +
                    "            \"CODE\": \"NAD\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"Namibia Dollar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"107\",\n" +
                    "            \"CODE\": \"NGN\",\n" +
                    "            \"SYMBOL\": \"NGN\",\n" +
                    "            \"NAME\": \"Naira\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"108\",\n" +
                    "            \"CODE\": \"NIO\",\n" +
                    "            \"SYMBOL\": \"C$\",\n" +
                    "            \"NAME\": \"Cordoba Oro\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"109\",\n" +
                    "            \"CODE\": \"NOK\",\n" +
                    "            \"SYMBOL\": \"kr\",\n" +
                    "            \"NAME\": \"Norwegian Krone\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"110\",\n" +
                    "            \"CODE\": \"NPR\",\n" +
                    "            \"SYMBOL\": \"NPR\",\n" +
                    "            \"NAME\": \"Nepalese Rupee\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"111\",\n" +
                    "            \"CODE\": \"NZD\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"New Zealand Dollar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"112\",\n" +
                    "            \"CODE\": \"OMR\",\n" +
                    "            \"SYMBOL\": \"OMR\",\n" +
                    "            \"NAME\": \"Rial Omani\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"113\",\n" +
                    "            \"CODE\": \"PAB\",\n" +
                    "            \"SYMBOL\": \"PAB\",\n" +
                    "            \"NAME\": \"Balboa\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"114\",\n" +
                    "            \"CODE\": \"PEN\",\n" +
                    "            \"SYMBOL\": \"PEN\",\n" +
                    "            \"NAME\": \"Nuevo Sol\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"115\",\n" +
                    "            \"CODE\": \"PGK\",\n" +
                    "            \"SYMBOL\": \"K\",\n" +
                    "            \"NAME\": \"Kina\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"116\",\n" +
                    "            \"CODE\": \"PHP\",\n" +
                    "            \"SYMBOL\": \"PHP\",\n" +
                    "            \"NAME\": \"Philippine Peso\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"117\",\n" +
                    "            \"CODE\": \"PKR\",\n" +
                    "            \"SYMBOL\": \"PKR\",\n" +
                    "            \"NAME\": \"Pakistan Rupee\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"118\",\n" +
                    "            \"CODE\": \"PLN\",\n" +
                    "            \"SYMBOL\": \"PLN\",\n" +
                    "            \"NAME\": \"PZloty\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"119\",\n" +
                    "            \"CODE\": \"PYG\",\n" +
                    "            \"SYMBOL\": \"PYG\",\n" +
                    "            \"NAME\": \"Guarani\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"120\",\n" +
                    "            \"CODE\": \"QAR\",\n" +
                    "            \"SYMBOL\": \"QAR\",\n" +
                    "            \"NAME\": \"Qatari Rial\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"121\",\n" +
                    "            \"CODE\": \"RON\",\n" +
                    "            \"SYMBOL\": \"L\",\n" +
                    "            \"NAME\": \"Leu\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"122\",\n" +
                    "            \"CODE\": \"RSD\",\n" +
                    "            \"SYMBOL\": \"din\",\n" +
                    "            \"NAME\": \"Serbian Dinar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"123\",\n" +
                    "            \"CODE\": \"RUB\",\n" +
                    "            \"SYMBOL\": \"RUB\",\n" +
                    "            \"NAME\": \"Russian Ruble\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"124\",\n" +
                    "            \"CODE\": \"RWF\",\n" +
                    "            \"SYMBOL\": \"RWF\",\n" +
                    "            \"NAME\": \"Rwanda Franc\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"125\",\n" +
                    "            \"CODE\": \"SAR\",\n" +
                    "            \"SYMBOL\": \"SAR\",\n" +
                    "            \"NAME\": \"Saudi Riyal\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"126\",\n" +
                    "            \"CODE\": \"SBD\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"Solomon Islands Dollar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"127\",\n" +
                    "            \"CODE\": \"SCR\",\n" +
                    "            \"SYMBOL\": \"SCR\",\n" +
                    "            \"NAME\": \"Seychelles Rupee\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"128\",\n" +
                    "            \"CODE\": \"SDG\",\n" +
                    "            \"SYMBOL\": \"£\",\n" +
                    "            \"NAME\": \"Sudanese Pound\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"129\",\n" +
                    "            \"CODE\": \"SEK\",\n" +
                    "            \"SYMBOL\": \"kr\",\n" +
                    "            \"NAME\": \"Swedish Krona\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"130\",\n" +
                    "            \"CODE\": \"SGD\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"Singapore Dollar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"131\",\n" +
                    "            \"CODE\": \"SHP\",\n" +
                    "            \"SYMBOL\": \"£\",\n" +
                    "            \"NAME\": \"Saint Helena Pound\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"132\",\n" +
                    "            \"CODE\": \"SLL\",\n" +
                    "            \"SYMBOL\": \"Le\",\n" +
                    "            \"NAME\": \"Leone\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"133\",\n" +
                    "            \"CODE\": \"SOS\",\n" +
                    "            \"SYMBOL\": \"Sh\",\n" +
                    "            \"NAME\": \"Somali Shilling\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"134\",\n" +
                    "            \"CODE\": \"SRD\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"Suriname Dollar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"135\",\n" +
                    "            \"CODE\": \"STD\",\n" +
                    "            \"SYMBOL\": \"Db\",\n" +
                    "            \"NAME\": \"Dobra\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"137\",\n" +
                    "            \"CODE\": \"SYP\",\n" +
                    "            \"SYMBOL\": \"SYP\",\n" +
                    "            \"NAME\": \"Syrian Pound\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"138\",\n" +
                    "            \"CODE\": \"SZL\",\n" +
                    "            \"SYMBOL\": \"L\",\n" +
                    "            \"NAME\": \"Lilangeni\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"139\",\n" +
                    "            \"CODE\": \"THB\",\n" +
                    "            \"SYMBOL\": \"THB\",\n" +
                    "            \"NAME\": \"Baht\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"140\",\n" +
                    "            \"CODE\": \"TJS\",\n" +
                    "            \"SYMBOL\": \"TJS\",\n" +
                    "            \"NAME\": \"Somoni\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"141\",\n" +
                    "            \"CODE\": \"TMT\",\n" +
                    "            \"SYMBOL\": \"m\",\n" +
                    "            \"NAME\": \"Manat\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"142\",\n" +
                    "            \"CODE\": \"TND\",\n" +
                    "            \"SYMBOL\": \"TND\",\n" +
                    "            \"NAME\": \"Tunisian Dinar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"143\",\n" +
                    "            \"CODE\": \"TOP\",\n" +
                    "            \"SYMBOL\": \"T$\",\n" +
                    "            \"NAME\": \"Pa’anga\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"144\",\n" +
                    "            \"CODE\": \"TRY\",\n" +
                    "            \"SYMBOL\": \"TRY\",\n" +
                    "            \"NAME\": \"Turkish Lira\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"145\",\n" +
                    "            \"CODE\": \"TTD\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"Trinidad and Tobago Dollar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"146\",\n" +
                    "            \"CODE\": \"TWD\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"Taiwan Dollar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"147\",\n" +
                    "            \"CODE\": \"TZS\",\n" +
                    "            \"SYMBOL\": \"Sh\",\n" +
                    "            \"NAME\": \"Tanzanian Shilling\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"148\",\n" +
                    "            \"CODE\": \"UAH\",\n" +
                    "            \"SYMBOL\": \"UAH\",\n" +
                    "            \"NAME\": \"Hryvnia\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"149\",\n" +
                    "            \"CODE\": \"UGX\",\n" +
                    "            \"SYMBOL\": \"Sh\",\n" +
                    "            \"NAME\": \"Uganda Shilling\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"150\",\n" +
                    "            \"CODE\": \"USD\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"US Dollar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"151\",\n" +
                    "            \"CODE\": \"UYU\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"Peso Uruguayo\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"152\",\n" +
                    "            \"CODE\": \"UZS\",\n" +
                    "            \"SYMBOL\": \"UZS\",\n" +
                    "            \"NAME\": \"Uzbekistan Sum\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"153\",\n" +
                    "            \"CODE\": \"VEF\",\n" +
                    "            \"SYMBOL\": \"VEF\",\n" +
                    "            \"NAME\": \"Bolivar Fuerte\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"154\",\n" +
                    "            \"CODE\": \"VND\",\n" +
                    "            \"SYMBOL\": \"VND\",\n" +
                    "            \"NAME\": \"Dong\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"155\",\n" +
                    "            \"CODE\": \"VUV\",\n" +
                    "            \"SYMBOL\": \"Vt\",\n" +
                    "            \"NAME\": \"Vatu\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"156\",\n" +
                    "            \"CODE\": \"WST\",\n" +
                    "            \"SYMBOL\": \"T\",\n" +
                    "            \"NAME\": \"Tala\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"157\",\n" +
                    "            \"CODE\": \"XAF\",\n" +
                    "            \"SYMBOL\": \"XAF\",\n" +
                    "            \"NAME\": \"CFA Franc BCEAO\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"160\",\n" +
                    "            \"CODE\": \"XCD\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"East Caribbean Dollar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"163\",\n" +
                    "            \"CODE\": \"XPF\",\n" +
                    "            \"SYMBOL\": \"XPF\",\n" +
                    "            \"NAME\": \"CFP Franc\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"164\",\n" +
                    "            \"CODE\": \"YER\",\n" +
                    "            \"SYMBOL\": \"YER\",\n" +
                    "            \"NAME\": \"Yemeni Rial\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"165\",\n" +
                    "            \"CODE\": \"ZAR\",\n" +
                    "            \"SYMBOL\": \"R\",\n" +
                    "            \"NAME\": \"Rand\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"167\",\n" +
                    "            \"CODE\": \"ZMW\",\n" +
                    "            \"SYMBOL\": \"ZK\",\n" +
                    "            \"NAME\": \"Zambian Kwacha\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"168\",\n" +
                    "            \"CODE\": \"ZWL\",\n" +
                    "            \"SYMBOL\": \"$\",\n" +
                    "            \"NAME\": \"Zimbabwe Dollar\"\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }




}
