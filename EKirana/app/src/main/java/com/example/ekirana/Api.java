package com.example.ekirana;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api
{
    @FormUrlEncoded
    @POST("E-Kirana/check_login.php")
    Call<Responce> checkLogin(
        @Field("Email") String email,
        @Field("Password") String pass
    );

    @FormUrlEncoded
    @POST("E-Kirana/cust_registerAPI.php")
    Call<RegistrationResponse> registerUser(
            @Field("Name") String name,
            @Field("Address") String address,
            @Field("Number") String number,
            @Field("Email") String email,
            @Field("Password") String pass
    );

    @FormUrlEncoded
    @POST("E-Kirana/getProductsByCategoryApi.php")
    Call<List<ProductsApiResponse>> getProducts(
            @Field("category") String categoty
    );

    @FormUrlEncoded
    @POST("E-Kirana/getProductsByName.php")
    Call<List<CheckoutItems>> getProductsByName(
            @Field("name") String name
    );

    @FormUrlEncoded
    @POST("E-Kirana/getAllProducts.php")
    Call<List<ApiResponce>> getAllProducts();

}
