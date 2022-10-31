package com.autodb_api.security;

import com.google.gson.JsonObject;
import net.minidev.json.parser.JSONParser;

import java.util.Base64;

public class JWTDecoder {
    JSONParser payload;
    JSONParser header;

    public JWTDecoder(String token) {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        //header = new String(decoder.decode(chunks[0]));
        //payload = new String(decoder.decode(chunks[1]));

        try {
            JSONParser parser = new JSONParser();

            JsonObject header = (JsonObject) parser.parse(new String(decoder.decode(chunks[0])));
            JsonObject payload = (JsonObject) parser.parse(new String(decoder.decode(chunks[1])));

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public String getPayload() {
        return payload.toString();
    }

    public String getHeader() {
        return header.toString();
    }


}
