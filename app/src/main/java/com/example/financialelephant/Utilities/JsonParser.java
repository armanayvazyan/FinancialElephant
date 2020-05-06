package com.example.financialelephant.Utilities;

import android.content.Context;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;

public class JsonParser {

    private ArrayList<Attribute> attributeList;
    private ArrayList<Company> companyList;

    public void convertToJson(Context context) throws IOException {

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
            String imgUrl = node.path("companies").get(i).path("imgUrl").asText();
            Attribute revenue = new Attribute("revenue", node.path("companies").get(i).path("revenue").asInt());
            Attribute paidUsers = new Attribute("paidusers", node.path("companies").get(i).path("paidusers").asInt());
            Attribute mau = new Attribute("mau", node.path("companies").get(i).path("mau").asInt());
            Attribute urr = new Attribute("urr", node.path("companies").get(i).path("urr").asInt());
            Attribute test = new Attribute("test", node.path("companies").get(i).path("test").asInt());
            ArrayList<Attribute> attributes = new ArrayList<>();
            Collections.addAll(attributes, revenue, paidUsers, mau, urr, test);
            companyList.add(new Company(name, i + 1, imgUrl, attributes));
        }
    }

    public ArrayList<Attribute> getAttributeList() {
        return attributeList;
    }

    public ArrayList<Company> getCompaniesList() {
        return companyList;
    }

}
