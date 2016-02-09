package com.xteam.demo.app.activities;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import com.google.gson.Gson;
import com.xteam.demo.R;
import com.xteam.demo.app.domain.Product;
import com.xteam.demo.app.service.ProductService;
import okhttp3.ResponseBody;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EActivity(R.layout.main_activity)
public class MainActivity extends AppCompatActivity { //todo need to remove inspections that says that we need to extend AbstractActivity always . What about extending an abstract Activity?

    @ViewById(R.id.main_activity__toolbar)
    Toolbar toolbar;
    @ViewById(R.id.main_activity__tag_search__edit_text)
    EditText searchEditText;
    @ViewById(R.id.main_activity__show_only_stock__check_box)
    CheckBox onlyShowItemsInStockCheckBox;

    @ViewById(R.id.my_recycler_view)
    RecyclerView recyclerView;

    private Subscription subscription;

    private MyAdapter mAdapter;

    @AfterViews
    void afterInject() {
        setSupportActionBar(toolbar);
        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        mAdapter = new MyAdapter(Collections.emptyList());
        recyclerView.setAdapter(mAdapter);
    }

    @Click(R.id.main_activity_button)
    void buttonClicked() {
        final Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://74.50.59.155:5000/api/")
                .build();
        final ProductService productService = retrofit.create(ProductService.class);
        final Observable<Response<ResponseBody>> productsResponse = productService.getProducts(5, 5, "", false);
        subscription = productsResponse.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).
                map(response -> new BufferedReader(response.body().charStream())).
                flatMap(bufferedReader -> fetchProducts(bufferedReader)).
                toList().
                subscribe(product -> {
                    Log.d("lala", "buttonClicked: " + product.get(0).getFace());
                    mAdapter.setNewItems(product);
                }, error -> {
                    Log.e("Shit", error.getMessage());
                });
    }

    private Observable<Product> fetchProducts(final BufferedReader bufferedReader) {
        final Gson gson = new Gson();
        try {
            final List<Product> productList = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                productList.add(gson.fromJson(line, Product.class));
            }
            return Observable.from(productList);
        } catch (final IOException e) {
            return Observable.error(e);
        }
    }

    //todo add ability to search from the search bar
    //todo add ability to cache results for 1 hour http://stackoverflow.com/questions/23429046/can-retrofit-with-okhttp-use-cache-data-when-offline
    //todo add ability to select checkbox

    @Override
    protected void onDestroy() {
        subscription.unsubscribe();
        super.onDestroy();
    }

}
