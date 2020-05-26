import com.example.ekirana.ProductsApiResponse;

import java.util.List;

public class ApiResponce
{
    private List<ProductsApiResponse> ProductsList;

    public ApiResponce(List<ProductsApiResponse> productsList) {
        ProductsList = productsList;
    }

    public List<ProductsApiResponse> getProductsList() {
        return ProductsList;
    }

    public void setProductsList(List<ProductsApiResponse> productsList) {
        ProductsList = productsList;
    }
}
