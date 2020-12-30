package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {

    String coming_category;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        Bundle b = getIntent().getExtras();
        coming_category = b.getString("category");
        dbobj = new EcommerceDataBase(getApplicationContext());
        productArrayList = new ArrayList<Product>();

        SpecifyDisplayedProducts();

        adapter = new ProductArrayAdapter(productArrayList, new OnButtonClickListenerInProductRecyclerViewItem() {
            @Override
            public void OnButtonClickListener(int position) {
                System.out.println("The Name of Product is " + productArrayList.get(position).ProductName);
            }
        });
        product_recyclerView = findViewById(R.id.product_recyclerview);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext());
        product_recyclerView.setLayoutManager(lm);
        product_recyclerView.setAdapter(adapter);
        //product_recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext() , DividerItemDecoration.VERTICAL));
        product_recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(20));
    }

    public void SpecifyDisplayedProducts(){
        int indx_in_array_all_categories = dbobj.GetCategoryID(coming_category) - 1;
        Cursor c = dbobj.GetProducts_ForSpecificCategory(indx_in_array_all_categories + 1);
        int pos = 0;
        while(!c.isAfterLast())
        {
            String name = c.getString(0);
            int price = Integer.parseInt(c.getString(1)) , quantity = Integer.parseInt(c.getString(2));
            Product product = new Product(name , price , quantity , all_categories[indx_in_array_all_categories][pos]);
            productArrayList.add(product);
            System.out.println(name +" "+price+" "+quantity+" "+all_categories[indx_in_array_all_categories][pos]);
            pos++;
            c.moveToNext();
        }
    }
}