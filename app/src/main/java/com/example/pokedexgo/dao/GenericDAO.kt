package com.example.pokedex.DAO

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import java.io.IOException
import java.lang.reflect.Type

/**
 * This method simulates query in backend that return a Json with data
 * Exception will be handle in other layer
 *
 * @param context sent
 * @param fileName the filename from asset to desirialize
 * @param classType The specific genericized type of src. You can obtain this type by using the
 *
 * @return an array of object of type T from the string. Returns {@code null} if {@code json}
 * is {@code null} or if {@code json} is empty.
 * 
 * @throws IOException if file is not found
 * @throws JsonParseException if json is not a valid representation for an object of type typeOfT
 * @throws JsonSyntaxException if json is not a valid representation for an object of type
 */
fun <T>getAllJsonList(context: Context, fileName: String, classType: Type): Array<T>? {
    val json = context.assets.open(fileName).bufferedReader().use {
        it.readText()
    }
    return Gson().fromJson(json, classType)
}

/**
 * This method simulates query in backend that return a Json with data
 * Exception will be handle in other layer
 *
 * @param context sent
 * @param fileName the filename from asset to desirialize
 * @param classType The specific genericized type of src. You can obtain this type by using the
 *
 * @return an object of type T from the string. Returns {@code null} if {@code json} is {@code null}
 * or if {@code json} is empty.
 *
 * @throws IOException if file is not found
 * @throws JsonParseException if json is not a valid representation for an object of type typeOfT
 * @throws JsonSyntaxException if json is not a valid representation for an object of type
 */
fun <T>getSingleJsonList(context: Context, fileName: String,  classType: Type): T? {
    val json = context.assets.open(fileName).bufferedReader().use {
        it.readText()
    }
    return Gson().fromJson(json, classType)
}
