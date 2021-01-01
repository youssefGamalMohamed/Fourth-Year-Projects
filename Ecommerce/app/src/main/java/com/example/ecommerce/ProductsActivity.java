package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {

    String coming_category;
    int Customer_ID;

    int [] computers = {R.drawable.computer1 , R.drawable.computer2 , R.drawable.computer3 , R.drawable.computer4 , R.drawable.computer5 , R.drawable.computer6};
    int [] labtops =   {R.drawable.labtop1 , R.drawable.labtop2 , R.drawable.labtop3 , R.drawable.labtop4 , R.drawable.labtop5 , R.drawable.labtop6};
    int [] mobiles_tablets = {R.drawable.mobile_tablet1 , R.drawable.mobile_tablet2 , R.drawable.mobile_tablet3 , R.drawable.mobile_tablet4 ,
            R.drawable.mobile_tablet5 , R.drawable.mobile_tablet6};

    int [] cameras = {R.drawable.camera1 , R.drawable.camera2 , R.drawable.camera3 , R.drawable.camera4 , R.drawable.camera5 , R.drawable.camera6 };
    int [] watches = {R.drawable.watche1 , R.drawable.watche2 , R.drawable.watche3 , R.drawable.watche4 , R.drawable.watche5 , R.drawable.watche6};
    int [] televisions = {R.drawable.television1 , R.drawable.television2 , R.drawable.television3 , R.drawable.television4 , R.drawable.television5 , R.drawable.television6};
    int [] projectors = {R.drawable.projector1 , R.drawable.projector2 , R.drawable.projector3 , R.drawable.projector4 , R.drawable.projector5 , R.drawable.projector6};
    int [] men_clothes = {R.drawable.men_clothes1 , R.drawable.men_clothes2 , R.drawable.men_clothes3 , R.drawable.men_clothes4 , R.drawable.men_clothes5 , R.drawable.men_clothes6};
    int [] books = {R.drawable.book1 , R.drawable.book2 , R.drawable.book3 , R.drawable.book4 , R.drawable.book5 , R.drawable.book6};
    int [] sports = {R.drawable.sport1 , R.drawable.sport2 , R.drawable.sport3 , R.drawable.sport4 , R.drawable.sport5 , R.drawable.sport6};

    int [][] all_categories = {computers , labtops , mobiles_tablets , cameras , watches , televisions , projectors , men_clothes , books , sports};


    EcommerceDataBase dbobj;

    RecyclerView product_recyclerView;
    ArrayList<Product> productArrayList;
    ProductArrayAdapter adapter;

    @Override
    protected void onRestart() {
        while (productArrayList.size() > 0){
            adapter.removeAt(0);
        }
        SpecifyDisplayedProducts();
        super.onRestart();
    }

    ImageButton btn_cart;
    ImageButton btn_search;
    EditText ed_productname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        btn_cart = (ImageButton)findViewById(R.id.btn_cart_that_in_productactivity);
        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext() , OrdersActivity.class);
                startActivity(i);
            }
        });



        Bundle b = getIntent().getExtras();
        coming_category = b.getString("category");
        Customer_ID = b.getInt("custid");


        dbobj = new EcommerceDataBase(getApplicationContext());
        productArrayList = new ArrayList<Product>();

        SpecifyDisplayedProducts();

        adapter = new ProductArrayAdapter(productArrayList, new OnButtonClickListenerInProductRecyclerViewItem() {
            @Override
            public void OnButtonClickListener(int position, View item) {
                Product tmp = productArrayList.get(position);


                Product p = new Product(tmp.ProductID , tmp.ProductName , tmp.ProductPrice , tmp.ProductQuantity , tmp.ProductImageSrc);


                TextView tv_quan = (TextView)item.findViewById(R.id.tv_productquantity);
                int tv_new_quantity = Cart.GetNumberFromString(tv_quan.getText().toString()) - 1;


                Cart.AddProductToCart(p);
                if(tv_new_quantity > 0) {
                    dbobj.ReducingQuantitybtOne(p.ProductID , tv_new_quantity);
                    tv_quan.setText("Quantity : " + tv_new_quantity);
                }
                else{
                    adapter.removeAt(position);
                }
            }
        });
                product_recyclerView = findViewById(R.id.product_recyclerview);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext());
        product_recyclerView.setLayoutManager(lm);
        product_recyclerView.setAdapter(adapter);
        //product_recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext() , DividerItemDecoration.VERTICAL));
        product_recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(20));



        ed_productname = findViewById(R.id.ed_search_for_product);
        btn_search = (ImageButton)findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed_productname.getText().toString().equals("")){
                    while(productArrayList.size()>0){
                        adapter.removeAt(0);
                    }
                    SpecifyDisplayedProducts();
                }else{
                    for(int i = 0 ; i < productArrayList.size() ; i++)
                    {
                        if(!ed_productname.getText().toString().equals(productArrayList.get(i).ProductName)){
                            adapter.removeAt(i);
                            i--;
                        }
                    }
                }
            }
        });

    }

    public void SpecifyDisplayedProducts(){
        int indx_in_array_all_categories = dbobj.GetCategoryID(coming_category) - 1;
        Cursor c = dbobj.GetProducts_ForSpecificCategory(indx_in_array_all_categories + 1);
        int pos = 0;
        while(!c.isAfterLast())
        {
            String name = c.getString(1);
            int id = Integer.parseInt(c.getString(0)), price = Integer.parseInt(c.getString(2)) , quantity = Integer.parseInt(c.getString(3));
            Product product = new Product(id , name , price , quantity , all_categories[indx_in_array_all_categories][pos]);
            productArrayList.add(product);
            System.out.println(id + " " + name +" "+price+" "+quantity+" "+all_categories[indx_in_array_all_categories][pos]);
            pos++;
            c.moveToNext();
        }
    }
}