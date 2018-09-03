package com.xiyoukeji.zhitingyun.data.remote;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.xiyoukeji.zhitingyun.ZhitingyunApplication;
import com.xiyoukeji.zhitingyun.constant.AppConstant;
import com.xiyoukeji.zhitingyun.constant.Urls;
import com.xiyoukeji.zhitingyun.data.DataSource;
import com.xiyoukeji.zhitingyun.data.entity.AccidEntity;
import com.xiyoukeji.zhitingyun.data.entity.AdviceBackEntity;
import com.xiyoukeji.zhitingyun.data.entity.AppointmentEntity;
import com.xiyoukeji.zhitingyun.data.entity.BaseModel;
import com.xiyoukeji.zhitingyun.data.entity.BaseModel0;
import com.xiyoukeji.zhitingyun.data.entity.DeviceEntity;
import com.xiyoukeji.zhitingyun.data.entity.EvaluationEntity;
import com.xiyoukeji.zhitingyun.data.entity.ExpertSchEntity;
import com.xiyoukeji.zhitingyun.data.entity.ListModel;
import com.xiyoukeji.zhitingyun.data.entity.ListModel0;
import com.xiyoukeji.zhitingyun.data.entity.LoginEntity;
import com.xiyoukeji.zhitingyun.data.entity.LunBoEntity;
import com.xiyoukeji.zhitingyun.data.entity.MallEntity;
import com.xiyoukeji.zhitingyun.data.entity.MessageEntity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.entity.Model1;
import com.xiyoukeji.zhitingyun.data.entity.OssMessageEntity;
import com.xiyoukeji.zhitingyun.data.entity.OtherEntity;
import com.xiyoukeji.zhitingyun.data.entity.Professor;
import com.xiyoukeji.zhitingyun.data.entity.Quick;
import com.xiyoukeji.zhitingyun.data.entity.RecordEntity;
import com.xiyoukeji.zhitingyun.data.entity.ReportEntity;
import com.xiyoukeji.zhitingyun.data.entity.SubmitEntity;
import com.xiyoukeji.zhitingyun.data.entity.SueEntity;
import com.xiyoukeji.zhitingyun.data.entity.TestRecordEntity;
import com.xiyoukeji.zhitingyun.data.entity.UploadEntity;
import com.xiyoukeji.zhitingyun.data.entity.UserEntity;
import com.xiyoukeji.zhitingyun.data.remote.transform.BaseTransformer;
import com.xiyoukeji.zhitingyun.data.remote.transform.BaseTransformer0;
import com.xiyoukeji.zhitingyun.data.remote.transform.DefaultTransformer;
import com.xiyoukeji.zhitingyun.data.remote.transform.DefaultTransformer0;
import com.xiyoukeji.zhitingyun.data.remote.transform.ErrorCheckerTransformer0;
import com.xiyoukeji.zhitingyun.util.L;
import com.xiyoukeji.zhitingyun.util.SPUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;

import static android.content.Context.MODE_PRIVATE;


public class RemoteDataSource implements DataSource {

    private static RemoteDataSource INSTANCE;
    private static Context sContext;
    private static ApiServer sApiServer;
    private static final int DEFAULT_TIME_OUT = 10;

    private static OkHttpClient provideOkHttpClient() {


        final SharedPreferences sharedPreferences = sContext.getSharedPreferences("mySP", MODE_PRIVATE);
        Log.d("aaa", sharedPreferences.getString("token", "") + "");

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                L.e(message);
            }
        });
        SetCookieCache cookieCache = new SetCookieCache();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        ClearableCookieJar cookieJar =
                new PersistentCookieJar(cookieCache, new SharedPrefsCookiePersistor(sContext));
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Authorization", sharedPreferences.getString("token", ""))
                                .build();
//                        Log.d( "sss","sss" );

                        return chain.proceed(request);
                    }
                })
                .cookieJar(cookieJar)
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .build();
        return client;
    }

    private static Retrofit provideRetrofit(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .build();
        return retrofit;
    }

    private RemoteDataSource(Context context) {
        sContext = context;
        Retrofit retrofit = provideRetrofit(provideOkHttpClient());
        sApiServer = retrofit.create(ApiServer.class);
    }

    public static RemoteDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource(context);
        }
        return INSTANCE;
    }


    @Override
    public void login(String phone, String password, BaseObserver<Model0<LoginEntity>> observer) {
        sApiServer.login(phone, password).compose(new DefaultTransformer0<BaseModel0<Model0<LoginEntity>>, Model0<LoginEntity>>())
                .subscribe(observer);
    }

    @Override
    public void sendCode(String phone, BaseObserver<BaseModel0> observer) {
        sApiServer.sendCode(phone).compose(new BaseTransformer0<BaseModel0>())
                .subscribe(observer);
    }

    @Override
    public void checkCode(String phone, String code, BaseObserver<Boolean> observer) {
        sApiServer.checkCode(phone, code).compose(new DefaultTransformer<BaseModel<Boolean>, Boolean>())
                .subscribe(observer);
    }

    @Override
    public void register(String phone, String code, String password, BaseObserver<Model0<LoginEntity>> observer) {
        sApiServer.register(phone, code, password).compose(new DefaultTransformer0<BaseModel0<Model0<LoginEntity>>, Model0<LoginEntity>>())
                .subscribe(observer);
    }

    @Override
    public void logout(BaseObserver<BaseModel0> observer) {
        sApiServer.logout().compose(new BaseTransformer0<BaseModel0>())
                .subscribe(observer);
    }

    @Override
    public void readAll(String id,BaseObserver<BaseModel0> observer) {
        sApiServer.readAll(id).compose(new BaseTransformer0<BaseModel0>())
                .subscribe(observer);
    }

    @Override
    public void updatePwd(String phone, String code, String password, BaseObserver<Model0<LoginEntity>> observer) {
        sApiServer.updatePwd(phone, code, password).compose(new DefaultTransformer0<BaseModel0<Model0<LoginEntity>>, Model0<LoginEntity>>())
                .subscribe(observer);
    }

    @Override
    public void getUserMsg(final CallBack<Model0<LoginEntity>> callBack) {
        sApiServer.getUserMsg().compose(new DefaultTransformer0<BaseModel0<Model0<LoginEntity>>, Model0<LoginEntity>>())
                .subscribe(new BaseObserver<Model0<LoginEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        callBack.addDis(d);
                    }

                    @Override
                    public void onNext(Model0<LoginEntity> loginEntityModel0) {
                        callBack.onSuccess(loginEntityModel0);
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        callBack.onError();
                    }

                });
    }


    @Override
    public void uploadAvatar(String path,final BaseObserver<Model0<String>> observer) {


File file = new File( path );
        RequestBody photoRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part photo = MultipartBody.Part.createFormData("img", file.getName(), photoRequestBody);//pic为key
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), path);
        sApiServer.uploadFile(name, photo  ).compose(new DefaultTransformer0<BaseModel0<Model0<String>>, Model0<String>>()).subscribe( observer );

    }


    /**
     * 获取mimeType
     *
     * @param file
     * @return
     */
    public static String getMimeType(final File file) {
        final String extension = getExtension(file);
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    }

    /**
     * 获取扩展名
     *
     * @param file
     * @return
     */
    private static String getExtension(final File file) {
        final String name = file.getName();
        final int idx = name.lastIndexOf(".");
        String suffix = "";
        if (idx > 0) {
            suffix = name.substring(idx + 1);
        }
        return suffix;
    }

    @Override
    public void changePwd(String newPwd, String oldPwd, BaseObserver<LoginEntity> observer) {
        sApiServer.changePwd(newPwd, oldPwd).compose(new DefaultTransformer<BaseModel<LoginEntity>, LoginEntity>())
                .subscribe(observer);
    }

    @Override
    public void resetPwd(String newPassword, String oldPassword, BaseObserver<Model0<LoginEntity>> observer) {
        sApiServer.resetPwd(newPassword, oldPassword).compose(new DefaultTransformer0<BaseModel0<Model0<LoginEntity>>, Model0<LoginEntity>>())
                .subscribe(observer);
    }

    @Override
    public void changePhone(String phone, String code, BaseObserver<Model0<LoginEntity>> observer) {
        sApiServer.changePhone(phone, code).compose(new DefaultTransformer0<BaseModel0<Model0<LoginEntity>>, Model0<LoginEntity>>())
                .subscribe(observer);
    }



    @Override
    public void getDoctor(int id, BaseObserver<AppointmentEntity> observer) {
        sApiServer.getDoctor(id).compose(new DefaultTransformer<BaseModel<AppointmentEntity>, AppointmentEntity>())
                .subscribe(observer);
    }

    @Override
    public void getRecordList(BaseObserver<Model0<List<TestRecordEntity>>> observer) {
        sApiServer.getRecordList(0).compose(new DefaultTransformer0<BaseModel0<Model0<List<TestRecordEntity>>>, Model0<List<TestRecordEntity>>>())
                .subscribe(observer);
    }

    @Override
    public void getTestRecordData(int id, BaseObserver<TestRecordEntity> observer) {
        sApiServer.getTestRecordData(id).compose(new DefaultTransformer<BaseModel<TestRecordEntity>, TestRecordEntity>())
                .subscribe(observer);
    }

    @Override
    public void sendDevice(Integer classify, String equipmentType, List<String> hertz, String chg_DATA, Integer P_S_DL, List<Double> L_P_VC, Integer L_P_VC_VALUE, List<Double> L_P_BASS, Integer L_P_BASS_VALUE, List<Double> L_P_MID, Integer L_P_MID_VALUE, List<Double> L_P_TRB, Integer L_P_TRB_VALUE, Integer L_P_NRLVL_VALUE, List<Double> L_P_NRLVL, Integer L_P_AFSLVL_VALUE, List<Double> L_P_AFSLVL, String L_P_EQ, Boolean L_SWITCH_P_CH, List<String> L_P_CHMPO, List<String> L_P_CHSW, List<String> L_P_CHSG, List<String> L_P_CHNG, List<String> L_P_CHLG, Boolean L_SWITCH_P_CHEXP, List<String> L_P_CHEXP, List<String> L_P_CHETH, List<String> L_P_CHER, List<Double> R_P_VC, Integer R_P_VC_VALUE, List<Double> R_P_BASS, Integer R_P_BASS_VALUE, List<Double> R_P_MID, Integer R_P_MID_VALUE, List<Double> R_P_TRB, Integer R_P_TRB_VALUE, Integer R_P_NRLVL_VALUE, List<Double> R_P_NRLVL, Integer R_P_AFSLVL_VALUE, List<Double> R_P_AFSLVL, String R_P_EQ, Boolean R_SWITCH_P_CH, List<String> R_P_CHMPO, List<String> R_P_CHSG, List<String> R_P_CHNG, List<String> R_P_CHLG, List<String> R_P_CHSW, Boolean R_SWITCH_P_CHEXP, List<String> R_P_CHEXP, List<String> R_P_CHETH, List<String> R_P_CHER, BaseObserver<Model0<DeviceEntity>> observer) {
//        sApiServer.sendDevice(classify, equipmentType, hertz, CHG_DATA, P_S_DL, L_P_VC, L_P_VC_VALUE, L_P_BASS, L_P_BASS_VALUE, L_P_MID, L_P_MID_VALUE, L_P_TRB, L_P_TRB_VALUE, L_P_NRLVL_VALUE, L_P_NRLVL, L_P_AFSLVL_VALUE, L_P_AFSLVL, L_P_EQ, L_SWITCH_P_CH, L_P_CHMPO, L_P_CHSW, L_P_CHSG, L_P_CHNG, L_P_CHLG, L_SWITCH_P_CHEXP, L_P_CHEXP, L_P_CHETH, L_P_CHER, R_P_VC,
//                R_P_VC_VALUE, R_P_BASS, R_P_BASS_VALUE, R_P_MID, R_P_MID_VALUE, R_P_TRB, R_P_TRB_VALUE, R_P_NRLVL_VALUE, R_P_NRLVL, R_P_AFSLVL_VALUE, R_P_AFSLVL, R_P_EQ, R_SWITCH_P_CH, R_P_CHMPO, R_P_CHSG, R_P_CHNG, R_P_CHLG, R_P_CHSW, R_SWITCH_P_CHEXP, R_P_CHEXP, R_P_CHETH, R_P_CHER)
//                .compose(new DefaultTransformer0<BaseModel0<Model0<DeviceEntity>>, Model0<DeviceEntity>>()).subscribe(observer);

    }


    @Override
    public void sendExpert(Integer proId, String equipmentParamIds, BaseObserver<BaseModel0> observer) {
        sApiServer.sendExpert(proId, equipmentParamIds).compose(new BaseTransformer0<BaseModel0>())
                .subscribe(observer);
    }


    @Override
    public void getAppointment(String name, String classify, BaseObserver<ListModel<List<AppointmentEntity>>> observer) {
        sApiServer.getAppointment(name, classify).compose(new DefaultTransformer0<BaseModel0<ListModel<List<AppointmentEntity>>>, ListModel<List<AppointmentEntity>>>())
//        sApiServer.getAppointment(name,classify).compose(new DefaultTransformer0<BaseModel0<ListModel<List<AppointmentEntity>>>, ListModel<AppointmentEntity>>())
                .subscribe(observer);
    }

    @Override
    public void getSchedual(Integer id, BaseObserver<Model0<ExpertSchEntity>> observer) {
        sApiServer.getSchedual(id).compose(new DefaultTransformer0<BaseModel0<Model0<ExpertSchEntity>>, Model0<ExpertSchEntity>>())
                .subscribe(observer);
    }

    @Override
    public void getPersonId(String accid, BaseObserver<Model0<AccidEntity>> observer) {
        sApiServer.getPersonId(accid).compose(new DefaultTransformer0<BaseModel0<Model0<AccidEntity>>, Model0<AccidEntity>>())
                .subscribe(observer);
    }

    @Override
    public void addQuickOrder(String equParamId, BaseObserver<Model0<Quick>> observer) {
        sApiServer.addQuickOrder(equParamId).compose(new DefaultTransformer0<BaseModel0<Model0<Quick>>, Model0<Quick>>())
                .subscribe(observer);
    }

    @Override
    public void submitRecord(String left_hertz, String right_hertz, String left_data, String right_data, String equipmentHolder, BaseObserver observer) {
        sApiServer.submitRecord(left_hertz, right_hertz, left_data, right_data, equipmentHolder).compose(new DefaultTransformer0())
                .subscribe(observer);
    }

    @Override
    public void sendAdvice(String professorId, String content, BaseObserver<Model0<AdviceBackEntity>> observer) {
        sApiServer.sendAdvice(professorId, content).compose(new DefaultTransformer0<BaseModel0<Model0<AdviceBackEntity>>, Model0<AdviceBackEntity>>())
                .subscribe(observer);
    }

    @Override
    public void adviceBack(String content, BaseObserver<Model0<AdviceBackEntity>> observer) {
        sApiServer.adviceBack(content).compose(new DefaultTransformer0<BaseModel0<Model0<AdviceBackEntity>>, Model0<AdviceBackEntity>>())
                .subscribe(observer);
    }

    @Override
    public void lunBo(String pageNo, String pageSize, BaseObserver<ListModel<List<LunBoEntity>>> observer) {
        sApiServer.lunBo(pageNo, pageSize).compose(new DefaultTransformer0<BaseModel0<ListModel<List<LunBoEntity>>>, ListModel<List<LunBoEntity>>>())
                .subscribe(observer);
    }

    @Override
    public void getUser(Integer id,BaseObserver<Model0<UserEntity>> observer) {
        sApiServer.getUser(id).compose(new DefaultTransformer0<BaseModel0<Model0<UserEntity>>, Model0<UserEntity>>())
                .subscribe(observer);
    }

    @Override
    public void basicInfo(String name, String sex, String age, String wearTimeEnum,String headPic, BaseObserver<Model0<UserEntity>> observer) {
        sApiServer.basicInfo(name, sex, age, wearTimeEnum,headPic).compose(new DefaultTransformer0<BaseModel0<Model0<UserEntity>>, Model0<UserEntity>>())
                .subscribe(observer);
    }

    @Override
    public void sueExpert(Integer professorId, String content, BaseObserver<Model0<RecordEntity>> observer) {
        sApiServer.sueExpert(professorId, content).compose(new DefaultTransformer0<BaseModel0<Model0<RecordEntity>>, Model0<RecordEntity>>())
                .subscribe(observer);
    }

    @Override
    public void getRecord(Integer id, BaseObserver<ListModel<List<RecordEntity>>> observer) {
        sApiServer.getRecord(id).compose(new DefaultTransformer0<BaseModel0<ListModel<List<RecordEntity>>>, ListModel<List<RecordEntity>>>())
                .subscribe(observer);
    }

    @Override
    public void erroCancel(Integer classify, Integer orderId, BaseObserver<BaseModel0> observer) {
        sApiServer.erroCancel(classify, orderId).compose(new BaseTransformer0<BaseModel0>())
                .subscribe(observer);
    }

    @Override
    public void checkMsg(String name,BaseObserver<Model1<MessageEntity>> observer) {
        sApiServer.checkMsg(name).compose(new DefaultTransformer0<BaseModel0<Model1<MessageEntity>>, Model1<MessageEntity>>())
                .subscribe(observer);
    }

    @Override
    public void getUserInfo(String name, BaseObserver<Model0<UserEntity>> observer) {
        sApiServer.getUserInfo(name).compose(new DefaultTransformer0<BaseModel0<Model0<UserEntity>>, Model0<UserEntity>>())
                .subscribe(observer);
    }

    @Override
    public void getQuickRecord(Integer id, BaseObserver<ListModel<List<RecordEntity>>> observer) {
        sApiServer.getQuickRecord(id).compose(new DefaultTransformer0<BaseModel0<ListModel<List<RecordEntity>>>, ListModel<List<RecordEntity>>>())
                .subscribe(observer);
    }

    @Override
    public void sendEvaluation(Integer orderId, Integer starts, String tags, String evaluation, BaseObserver<Model0<EvaluationEntity>> observer) {
        sApiServer.sendEvaluation( orderId,starts,tags,evaluation ).compose(new DefaultTransformer0<BaseModel0<Model0<EvaluationEntity>>, Model0<EvaluationEntity>>()  )
                .subscribe( observer);
    }

    @Override
    public void sendNormalEvaluation(Integer orderId, Integer starts, String tags, String evaluation, BaseObserver<Model0<EvaluationEntity>> observer) {
        sApiServer.sendNormalEvaluation( orderId,starts,tags,evaluation ).compose(new DefaultTransformer0<BaseModel0<Model0<EvaluationEntity>>, Model0<EvaluationEntity>>()  )
                .subscribe( observer);
    }

    @Override
    public void getMall(Integer pageNo, Integer pageSize, boolean sort, BaseObserver<ListModel<List<MallEntity>>> observer) {
        sApiServer.getMall(pageNo,pageSize,sort).compose( new DefaultTransformer0<BaseModel0<ListModel<List<MallEntity>>>,ListModel<List<MallEntity>>>() )
                .subscribe( observer);
    }

    @Override
    public void getReport(Integer orderId, BaseObserver<Model0<RecordEntity>> observer) {
        sApiServer.getReport( orderId ).compose( new DefaultTransformer0<BaseModel0<Model0<RecordEntity>>,Model0<RecordEntity>>() )
                .subscribe( observer );
    }

    @Override
    public void getRecent(String name, BaseObserver<Model0<RecordEntity>> observer) {
        sApiServer.getRecent( name ).compose( new DefaultTransformer0<BaseModel0<Model0<RecordEntity>>,Model0<RecordEntity>>() )
                .subscribe( observer );
    }

    @Override
    public void orderStatus(Integer orderId,Integer classify, BaseObserver<Model0<RecordEntity>> observer) {
        sApiServer.orderStatus( orderId,classify ).compose( new DefaultTransformer0<BaseModel0<Model0<RecordEntity>>,Model0<RecordEntity>>() )
                .subscribe( observer );
    }

    @Override
    public void getOthers(Integer id, BaseObserver<Model0<OtherEntity>> observer) {
        sApiServer.getOthers( id).compose( new DefaultTransformer0<BaseModel0<Model0<OtherEntity>>,Model0<OtherEntity>>()  )
                .subscribe( observer );
    }

    @Override
    public void readOrnot(String name, BaseObserver<Model0<MessageEntity>> observer) {
        sApiServer.readOrnot( name ).compose( new DefaultTransformer0<BaseModel0<Model0<MessageEntity>>,Model0<MessageEntity>>()  )
                .subscribe( observer );
    }

    @Override
    public void getQuickReport(Integer quickOrderId, BaseObserver<Model0<RecordEntity>> observer) {
        sApiServer.getQuickReport( quickOrderId ).compose( new DefaultTransformer0<BaseModel0<Model0<RecordEntity>>,Model0<RecordEntity>>() )
                .subscribe( observer );
    }

    @Override
    public void cancelOrder(Integer classify, Integer orderId, BaseObserver<Model0<RecordEntity>> observer) {
        sApiServer.cancelOrder( classify,orderId ).compose( new DefaultTransformer0<BaseModel0<Model0<RecordEntity>>,Model0<RecordEntity>>() )
                .subscribe( observer );
    }

    @Override
    public void cancelDate(Integer orderId,Integer classify, BaseObserver<Model0<RecordEntity>> observer) {
        sApiServer.cancelDate(orderId,classify ).compose( new DefaultTransformer0<BaseModel0<Model0<RecordEntity>>,Model0<RecordEntity>>() )
                .subscribe( observer );
    }


    @Override
    public void getMessage(long lastTime, BaseObserver<ListModel<List<MessageEntity>>> observer) {
        sApiServer.getMessage(lastTime).compose(new DefaultTransformer0<BaseModel0<ListModel<List<MessageEntity>>>, ListModel<List<MessageEntity>>>())
                .subscribe(observer);

    }

    @Override
    public void getMsgDetail(Integer newsId, Integer newsType, BaseObserver<Model0<MessageEntity>> observer) {
        sApiServer.getMsgDetail(newsId, newsType).compose(new DefaultTransformer0<BaseModel0<Model0<MessageEntity>>, Model0<MessageEntity>>())
                .subscribe(observer);
    }


    @Override
    public void submit(Integer workId, String desc, String equipmentId, BaseObserver<Model0<SubmitEntity>> observer) {
        sApiServer.submit(workId, desc, equipmentId).compose(new DefaultTransformer0<BaseModel0<Model0<SubmitEntity>>, Model0<SubmitEntity>>())
                .subscribe(observer);
    }

    @Override
    public void submitParamater(DeviceEntity deviceEntity, BaseObserver<Model0<DeviceEntity>> observer) {
        sApiServer.submitParamater(deviceEntity.getClassify(), deviceEntity.getEquipmentType(), deviceEntity.getHertz(), deviceEntity.getChg_DATA(), deviceEntity.getP_S_DL(), deviceEntity.getL_P_VC(), deviceEntity.getL_P_VC_VALUE(), deviceEntity.getL_P_BASS(), deviceEntity.getL_P_BASS_VALUE(), deviceEntity.getL_P_MID(), deviceEntity.getL_P_MID_VALUE(), deviceEntity.getL_P_TRB(), deviceEntity.getL_P_TRB_VALUE(), deviceEntity.getL_P_NRLVL_VALUE(), deviceEntity.getL_P_NRLVL(), deviceEntity.getL_P_AFSLVL_VALUE(), deviceEntity.getL_P_AFSLVL(), deviceEntity.getL_P_EQ(), deviceEntity.getL_SWITCH_P_CH(), deviceEntity.getL_P_CHMPO(), deviceEntity.getL_P_CHSW(), deviceEntity.getL_P_CHSG(), deviceEntity.getL_P_CHNG(), deviceEntity.getL_P_CHLG(), deviceEntity.getL_SWITCH_P_CHEXP(), deviceEntity.getL_P_CHEXP(), deviceEntity.getL_P_CHETH(), deviceEntity.getL_P_CHER(), deviceEntity.getR_P_VC(), deviceEntity.getR_P_VC_VALUE(), deviceEntity.getR_P_BASS(), deviceEntity.getR_P_BASS_VALUE(), deviceEntity.getR_P_MID(), deviceEntity.getR_P_MID_VALUE(), deviceEntity.getR_P_TRB(), deviceEntity.getR_P_TRB_VALUE(), deviceEntity.getR_P_NRLVL_VALUE(), deviceEntity.getR_P_NRLVL(), deviceEntity.getR_P_AFSLVL_VALUE(), deviceEntity.getR_P_AFSLVL(), deviceEntity.getR_P_EQ(), deviceEntity.getR_SWITCH_P_CH(), deviceEntity.getR_P_CHMPO(), deviceEntity.getR_P_CHSG(), deviceEntity.getR_P_CHNG(), deviceEntity.getR_P_CHLG(), deviceEntity.getR_P_CHSW(), deviceEntity.getR_SWITCH_P_CHEXP(), deviceEntity.getR_P_CHEXP(), deviceEntity.getR_P_CHETH(), deviceEntity.getR_P_CHER()).compose(new DefaultTransformer0())
                .subscribe(observer);
    }


    @Override
    public void getProfessorList(String name, Integer classify, BaseObserver<Model0<List<Professor>>> observer) {
        sApiServer.getProfessorList(name, classify).compose(new DefaultTransformer0<BaseModel0<Model0<List<Professor>>>, Model0<List<Professor>>>())
                .subscribe(observer);
    }




}
