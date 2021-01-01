package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class OrdersActivity extends AppCompatActivity {
    ArrayList<Product> productArrayList;
    RecyclerView order_recyclerView;
    OrderArrayAdapter adapter;
    EcommerceDataBase dobj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        dobj = new EcommerceDataBase(getApplicationContext());

        FillproductArraListWithData();

        order_recyclerView = findViewById(R.id.order_recyclerview);
        adapter = new OrderArrayAdapter(productArrayList);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext());
        order_recyclerView.setLayoutManager(lm);
        order_recyclerView.setAdapter(adapter);
        //product_recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext() , DividerItemDecoration.VERTICAL));
        order_recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(20));
    }

    public void FillproductArraListWithData(){
        productArrayList = new ArrayList<Product>();
        Set<Integer> setOfCountries = Cart.product_and_quantity.keySet();
        for(int key : setOfCountries) {
            Product tmp = Cart.product_and_quantity.get(key).first;
            Product p = new Product(tmp.ProductID , tmp.ProductName , tmp.ProductPrice , tmp.ProductQuantity , tmp.ProductImageSrc);
            productArrayList.add(p);
        }
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = item.getGroupId();
        Product tmp = productArrayList.get(position);
        Product p = new Product(tmp.ProductID ,tmp.ProductName , tmp.ProductPrice , tmp.ProductQuantity , tmp.ProductImageSrc);
        if(item.getItemId() == 121){
            // Delete it from Cart
            Cart.DeleteProductFromCart(p);
            // Delete From Database
            dobj.AddMoreQuantityToProduct(p.ProductID , p.ProductQuantity);
            // Delete it from Adapter
            adapter.removeAt(position);
            return true;
        }
        if(item.getItemId() == 122){
            LayoutInflater inflater = LayoutInflater.from(this);
            final View dialogview = inflater.inflate(R.layout.popup , null);
            final AlertDialog dialog = new AlertDialog.Builder(this).create();
            dialog.setView(dialogview);
            dialogview.findViewById(R.id.popup_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText entered_quantity_ed = dialogview.findViewById(R.id.popup_edittext);
                    int entered_quantity = Integer.parseInt(entered_quantity_ed.getText().toString());
                    int cur_quantity = p.ProductQuantity;
                    if(entered_quantity > cur_quantity)
                    {
                        boolean available_in_stock_or_not = dobj.CheckIFQuantityAvailableForProduct(p.ProductID , entered_quantity - cur_quantity);
                        if(available_in_stock_or_not == true) {
                            //update the and price quantity in product object
                            p.ProductQuantity = entered_quantity;
                            int actual_product_price_from_database = dobj.GetProductPrice(p.ProductID);
                            p.ProductPrice = p.ProductQuantity * actual_product_price_from_database;
                            //Add the difference between them to the the quantity in database
                            int actual_quanttiy_from_databse = dobj.GetQuantityforProduct(p.ProductID);
                            dobj.ReducingQuantitybtOne(p.ProductID , actual_quanttiy_from_databse - (entered_quantity - cur_quantity));
                            //Update the quantity in the Cart class
                            Cart.UpdateProductToCart(p , p.ProductQuantity);
                            //Update in the ArrayList of Adapter
                            adapter.updateAt(position , p);
                        }
                        else{
                            // Should Tell Him that no available in stock to add it to cart
                        }
                    }
                    else if(entered_quantity < cur_quantity){
                        //update the and price quantity in product object
                        p.ProductQuantity = entered_quantity;
                        int actual_product_price_from_database = dobj.GetProductPrice(p.ProductID);
                        p.ProductPrice = p.ProductQuantity * actual_product_price_from_database;
                        //Add the difference between them to the the quantity in database
                        dobj.AddMoreQuantityToProduct(p.ProductID , cur_quantity - entered_quantity);
                        //Update the quantity in the Cart class
                        Cart.UpdateProductToCart(p , p.ProductQuantity);
                        //Update in the ArrayList of Adapter
                        adapter.updateAt(position , p);
                    }
                    dialog.dismiss();
                }
            });

            dialog.show();
            return true;
        }
        return super.onContextItemSelected(item);
    }
}