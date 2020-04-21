package com.example.financialelephant;

import android.content.Context;
import android.content.res.AssetManager;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;

public class JsonParser {

    private ArrayList<Attribute> attributeList;
    private ArrayList<Company> companyList;

    void convertToJson(Context context) throws IOException {

        Reader reader = new InputStreamReader(context.getAssets().open("full_values.json"));
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(reader);

        attributeList = new ArrayList<>();
        Collections.addAll(attributeList,
                new Attribute("revenue", node.path("attribute_comparing").path("revenue").asInt()),
                new Attribute("paidusers", node.path("attribute_comparing").path("paidusers").asInt()),
                new Attribute("mau", node.path("attribute_comparing").path("mau").asInt()),
                new Attribute("urr", node.path("attribute_comparing").path("urr").asInt()),
                new Attribute("test", node.path("attribute_comparing").path("test").asInt()));

        companyList = new ArrayList<>();
        for (int i = 0; i < node.path("companies").size(); i++) {
            String name = node.path("companies").get(i).path("name").asText();
            Attribute revenue = new Attribute("revenue", node.path("companies").get(i).path("revenue").asInt());
            Attribute paidUsers = new Attribute("paidusers", node.path("companies").get(i).path("paidusers").asInt());
            Attribute mau = new Attribute("mau", node.path("companies").get(i).path("mau").asInt());
            Attribute urr = new Attribute("urr", node.path("companies").get(i).path("urr").asInt());
            Attribute test = new Attribute("test", node.path("companies").get(i).path("test").asInt());
            ArrayList<Attribute> attributes = new ArrayList<Attribute>();
            Collections.addAll(attributes, revenue, paidUsers, mau, urr, test);

            companyList.add(new Company(name, i + 1, attributes));
        }
    }

    ArrayList<Attribute> getAttributeList() {
        return attributeList;
    }

    ArrayList<Company> getCompaniesList() {
        return companyList;
    }

}
