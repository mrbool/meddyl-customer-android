package com.gtsoft.meddyl.customer.system.gtsoft;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DotNetDateDeserializer implements JsonSerializer<Date>, JsonDeserializer<Date>
{
    private static final String format = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    private static final SimpleDateFormat formatter = new SimpleDateFormat(format);

    @Override
    public Date deserialize(JsonElement src, Type typeOfSrc, JsonDeserializationContext context)
            throws JsonParseException
    {
//        try
//        {
            //return formatter.parse(src.getAsString());

            int idx1 = src.getAsString().indexOf("(");
            int idx2 = src.getAsString().indexOf(")") - 5;
            String s = src.getAsString().substring(idx1+1, idx2);
            long l = Long.valueOf(s);
            return new Date(l);
//        }
//        catch (ParseException e)
//        {
//            return null;
//        }
    }

    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context)
    {
        //return new JsonPrimitive(formatter.format(src));
        return new JsonPrimitive("/Date(" + src.getTime() + "-0000)/");
    }
}