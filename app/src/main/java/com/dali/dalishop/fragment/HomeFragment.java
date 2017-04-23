package com.dali.dalishop.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.dali.dalishop.R;
import com.dali.dalishop.adapter.HomeCampaginAdapter;
import com.dali.dalishop.bean.Banner;
import com.dali.dalishop.bean.HomeCampaign;
import com.dali.dalishop.http.BaseCallBack;
import com.dali.dalishop.http.OkhttpHelper;
import com.dali.dalishop.itemdivide.ItemDivide;
import com.dali.dalishop.utils.GsonUtils;
import com.dali.dalishop.widget.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    private SliderLayout mSliderLayout;

    private PagerIndicator indicator;

    private RecyclerView recyclerView;

    private HomeCampaginAdapter adapter;

    private OkhttpHelper helper = OkhttpHelper.getInstanse();

    OkHttpClient okHttpClient = new OkHttpClient();

    Gson gson = new Gson();

    List<Banner> banners = new ArrayList<>();

    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mSliderLayout = (SliderLayout) view.findViewById(R.id.slider);

        indicator = (PagerIndicator) view.findViewById(R.id.custom_indicator);

        requestImages();

        initRecyclerView(view);

        return view;
    }

    private void initRecyclerView(View v) {
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);


        helper.doGet(Constants.API.CAMPAIN_HOME,new BaseCallBack<List<HomeCampaign>>() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onSuccess(Response response, List<HomeCampaign> homeCampaigns) {
                initData(homeCampaigns);
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }

            @Override
            public void onRequestBefore(Request request) {

            }
        });

    }

    private void initData(List<HomeCampaign> homeCampaigns) {

        adapter = new HomeCampaginAdapter(homeCampaigns,getContext());

        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new ItemDivide());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void requestImages() {

        RequestBody body = new FormEncodingBuilder().add("type","1").build();
        Request request = new Request.Builder().url(Constants.API.BANNER).post(body).build();

            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(Response response) throws IOException {
                    if (response.isSuccessful()){

                        String json = response.body().string();
                        banners = gson.fromJson(json, new TypeToken<List<Banner>>() {
                        }.getType());

                        System.out.println(json);

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                initSlider(banners);
                            }
                        });
                    }
                }
            });



//        helper.get(Constants.API.BANNER + "?type=1", new BaseCallBack<List<Banner>>() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//
//            }
//
//            @Override
//            public void onSuccess(Response response, List<Banner> banners) {
//
//                initSlider(banners);
//            }
//
//            @Override
//            public void onError(Response response, int code, Exception e) {
//
//            }
//
//            @Override
//            public void onRequestBefore(Request request) {
//
//            }
//        });

    }

    private void initSlider(List<Banner> banners) {
        for (Banner banner:banners){
            TextSliderView textSliderView = new TextSliderView(this.getActivity());
            textSliderView.description(banner.getDescription()).image(banner.getImgUrl());
            textSliderView.setScaleType(BaseSliderView.ScaleType.Fit);
            mSliderLayout.addSlider(textSliderView);
        }

        //设置指示器
        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);

//        mSliderLayout.setCustomIndicator(indicator);

        //设置动画效果
        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.RotateUp);
        //设置动画效果
        mSliderLayout.setCustomAnimation(new DescriptionAnimation());
        //设置时长
        mSliderLayout.setDuration(3000);
    }

    @Override
    public void onDestroy() {
        mSliderLayout.stopAutoCycle();
        super.onDestroy();
    }

}
