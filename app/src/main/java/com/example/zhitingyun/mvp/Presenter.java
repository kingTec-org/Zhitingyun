package com.example.zhitingyun.mvp;


import com.example.zhitingyun.http.DefaultSubscriber;
import com.example.zhitingyun.model.Confirm;
import com.example.zhitingyun.model.EquipmentParamater;
import com.example.zhitingyun.model.HomePage;
import com.example.zhitingyun.model.News;
import com.example.zhitingyun.model.NewsDetail;
import com.example.zhitingyun.model.Order;
import com.example.zhitingyun.model.OrderDetail;
import com.example.zhitingyun.model.ProfessorInfo;
import com.example.zhitingyun.model.Quick;
import com.example.zhitingyun.model.SchedualItem;
import com.github.mikephil.charting.renderer.BarChartRenderer;

import java.security.cert.CertPathValidatorException;
import java.util.List;
import java.util.Map;

import coder.mylibrary.base.BasePresenter;
import coder.mylibrary.base.BaseView;

/**
 * Created by dasiy on 2017/10/12.
 */

public class Presenter extends BasePresenter<BaseView> {
    BaseView baseView;
    MainData mainData;

    public Presenter(BaseView baseView) {
        this.baseView = baseView;
        mainData = MainData.getInstance();
    }

    public void login(Map map) {
        mainData.login(map, new DefaultSubscriber<ProfessorInfo>() {
            @Override
            public void onNext(ProfessorInfo professorInfo) {
                ((ViewsContainer.LoginView) baseView).login(professorInfo);


            }
        });
    }

    public void verifyCodeSend(Map map) {


        mainData.verifyCodeSend(map, new DefaultSubscriber() {
            @Override
            public void onNext(Object object) {
                ((ViewsContainer.ForgetPasswordView) baseView).verifyCodeSend();

            }
        });
    }

    public void resetPassword(Map map) {


        mainData.resetPassword(map, new DefaultSubscriber() {
            @Override
            public void onNext(Object object) {
                ((ViewsContainer.ResetPasswordView) baseView).resetPassword();

            }
        });
    }

    public void getInfo(Map map, final int flag) {


        mainData.getInfo(map, new DefaultSubscriber<ProfessorInfo>() {
            @Override
            public void onNext(ProfessorInfo professorInfo) {
                switch (flag) {
                    case 0:
                        ((ViewsContainer.UserInfoView) baseView).getInfo(professorInfo);

                        break;
                    case 1:
                        ((ViewsContainer.PersonInfoView) baseView).getInfo(professorInfo);

                        break;
                    case 2:
                        ((ViewsContainer.AccountManageView) baseView).getInfo(professorInfo);

                        break;
                }

            }
        });
    }

    public void uploadFile(String imagePath) {
        mainData.uploadFile(imagePath, new DefaultSubscriber<String>() {
            @Override
            public void onNext(String string) {
//                ((ViewsContainer.PersonInfoView) baseView).uploadImg(string);
            }
        });
    }

    public void addSchedule(Map map) {
        mainData.addSchedule(map, new DefaultSubscriber() {
            @Override
            public void onNext(Object object) {
                ((ViewsContainer.MySchedualView) baseView).addSchedule();

            }
        });
    }

    public void getScheduleList(Map map) {
        mainData.getScheduleList(map, new DefaultSubscriber<List<SchedualItem>>() {
            @Override
            public void onNext(List<SchedualItem> list) {
                ((ViewsContainer.MySchedualView) baseView).getScheduleList(list);

            }
        });
    }

    public void deleteSchedule(Map map) {
        mainData.deleteSchedule(map, new DefaultSubscriber() {
            @Override
            public void onNext(Object object) {
                ((ViewsContainer.MySchedualView) baseView).deleteSchedule();

            }
        });
    }

    public void repeatSchedule(Map map) {
        mainData.repeatSchedule(map, new DefaultSubscriber() {
            @Override
            public void onNext(Object object) {
                ((ViewsContainer.MySchedualView) baseView).repeatSchedule();

            }
        });
    }

    public void getOrderList(Map map) {
        mainData.getOrderList(map, new DefaultSubscriber<List<Confirm>>() {
            @Override
            public void onNext(List<Confirm> list) {
                ((ViewsContainer.ConfirmView) baseView).getOrderList(list);


            }
        });
    }

    public void getQuickOrderList(Map map) {
        mainData.getQuickOrderList(map, new DefaultSubscriber<List<Confirm>>() {
            @Override
            public void onNext(List<Confirm> list) {
                ((ViewsContainer.ConfirmView) baseView).getOrderList(list);

            }
        });
    }

    public void getOrder(Map map, final int flag) {
        mainData.getOrder(map, new DefaultSubscriber<OrderDetail>() {
            @Override
            public void onNext(OrderDetail orderDetail) {
                switch (flag) {
                    case 0:
                        ((ViewsContainer.OrderDetailView) baseView).getOrder(orderDetail);

                        break;
                    case 1:
                        ((ViewsContainer.OrderHistoryView) baseView).getOrder(orderDetail);

                        break;
                    case 2:
                        ((ViewsContainer.WriteReportView) baseView).getOrder(orderDetail);

                        break;
                }

            }
        });
    }

    public void getQuickOrder(Map map) {
        mainData.getQuickOrder(map, new DefaultSubscriber<OrderDetail>() {
            @Override
            public void onNext(OrderDetail orderDetail) {
                ((ViewsContainer.OrderHistoryView) baseView).getOrder(orderDetail);

            }
        });
    }

    public void refuseOrder(Map map) {
        mainData.refuseOrder(map, new DefaultSubscriber() {
            @Override
            public void onNext(Object o) {
                ((ViewsContainer.OrderRefuseView) baseView).refuseOrder();

            }
        });
    }

    public void receiveOrder(Map map) {
        mainData.receiveOrder(map, new DefaultSubscriber<OrderDetail>() {
            @Override
            public void onNext(OrderDetail orderDetail) {
                ((ViewsContainer.OrderDetailView) baseView).receiveOrder(orderDetail);

            }
        });
    }

    public void recordCure(Map map) {
        mainData.recordCure(map, new DefaultSubscriber<OrderDetail>() {
            @Override
            public void onNext(OrderDetail orderDetail) {
                ((ViewsContainer.OrderDetailView) baseView).recordCure(orderDetail);

            }
        });
    }

    public void startCure(Map map) {
        mainData.startCure(map, new DefaultSubscriber<OrderDetail>() {
            @Override
            public void onNext(OrderDetail orderDetail) {
                ((ViewsContainer.OrderDetailView) baseView).startCure(orderDetail);

            }
        });
    }

    public void getRequestList(Map map) {
        mainData.getRequestList(map, new DefaultSubscriber<List<Quick>>() {
            @Override
            public void onNext(List<Quick> list) {
                ((ViewsContainer.QuickView) baseView).getRequestList(list);

            }
        });
    }

    public void receiveRequest(Map map) {
        mainData.receiveRequest(map, new DefaultSubscriber<OrderDetail>() {
            @Override
            public void onNext(OrderDetail orderDetail) {
                ((ViewsContainer.QuickView) baseView).receiveRequest(orderDetail);

            }
        });
    }

    public void logout(Map map) {
        mainData.logout(map, new DefaultSubscriber() {
            @Override
            public void onNext(Object o) {
                ((ViewsContainer.AccountManageView) baseView).logout();

            }
        });
    }

    public void finishCure(Map map, final int flag) {
        mainData.finishCure(map, new DefaultSubscriber() {
            @Override
            public void onNext(Object o) {
                switch (flag) {
                    case 0:
                        ((ViewsContainer.OrderDetailView) baseView).finishCure();

                        break;
                    case 1:
                        ((ViewsContainer.MainView) baseView).finishCure();
                        break;
                }

            }
        });
    }

    public void getParamaterList(Map map, final int flag) {
        mainData.getParamaterList(map, new DefaultSubscriber<List<EquipmentParamater>>() {
            @Override
            public void onNext(List<EquipmentParamater> list) {
                switch (flag) {
                    case 0:
                        ((ViewsContainer.OrderTreattingView) baseView).getParamaterList(list);

                        break;
                    case 1:
                        ((ViewsContainer.OrderDetailView) baseView).getParamaterList(list);

                        break;
                }
            }
        });
    }

    public void submitParamater(Map map) {
        mainData.submitParamater(map, new DefaultSubscriber<EquipmentParamater>() {
            @Override
            public void onNext(EquipmentParamater equipmentParamater) {
                ((ViewsContainer.WriteReportView) baseView).submitParamater(equipmentParamater);
            }
        });
    }

    public void submitQuickOrder(Map map) {
        mainData.submitQuickOrder(map, new DefaultSubscriber() {
            @Override
            public void onNext(Object object) {
                ((ViewsContainer.WriteReportView) baseView).cureReport();
            }
        });
    }

    public void cureReport(Map map) {
        mainData.cureReport(map, new DefaultSubscriber() {
            @Override
            public void onNext(Object object) {
                ((ViewsContainer.WriteReportView) baseView).cureReport();
            }
        });
    }

    public void cancelOrder(Map map) {
        mainData.cancelOrder(map, new DefaultSubscriber() {
            @Override
            public void onNext(Object object) {
                ((ViewsContainer.OrderDetailView) baseView).cancelOrder();
            }
        });
    }

    public void cancelHaveNotBegunOrder(Map map) {
        mainData.cancelHaveNotBegunOrder(map, new DefaultSubscriber() {
            @Override
            public void onNext(Object object) {
                ((ViewsContainer.OrderDetailView) baseView).cancelOrder();
            }
        });
    }

    public void getRecentlyOrder(Map map) {
        mainData.getRecentlyOrder(map, new DefaultSubscriber<OrderDetail>() {
            @Override
            public void onNext(OrderDetail orderDetail) {
                ((ViewsContainer.OrderDetailView) baseView).getOrder(orderDetail);
            }
        });
    }

    public void homePage(Map map) {
        mainData.homePage(map, new DefaultSubscriber<HomePage>() {
            @Override
            public void onNext(HomePage homePage) {
                ((ViewsContainer.TodoView) baseView).homePage(homePage);
            }
        });
    }

    public void getNewsList(Map map) {
        mainData.getNewsList(map, new DefaultSubscriber<List<News>>() {
            @Override
            public void onNext(List<News> list) {
                ((ViewsContainer.NewsListView) baseView).getNewsList(list);
            }
        });
    }

    public void readAll(Map map) {
        mainData.readAll(map, new DefaultSubscriber() {
            @Override
            public void onNext(Object object) {
                ((ViewsContainer.NewsListView) baseView).readAll();
            }
        });
    }

    public void haveNewMsg(Map map) {
        mainData.haveNewMsg(map, new DefaultSubscriber<Integer>() {
            @Override
            public void onNext(Integer integer) {
                ((ViewsContainer.WorkspaceView) baseView).haveNewMsg(integer);
            }
        });
    }

    public void sendVerifyCode(Map map) {
        mainData.sendVerifyCode(map, new DefaultSubscriber() {
            @Override
            public void onNext(Object object) {
                ((ViewsContainer.ChangePhoneView) baseView).sendVerifyCode();
            }
        });
    }

    public void changePhone(Map map) {
        mainData.changePhone(map, new DefaultSubscriber() {
            @Override
            public void onNext(Object object) {
                ((ViewsContainer.ChangePhoneView) baseView).changePhone();
            }
        });
    }

    public void changePassword(Map map) {
        mainData.changePassword(map, new DefaultSubscriber() {
            @Override
            public void onNext(Object object) {
                ((ViewsContainer.ChangePasswordView) baseView).changePassword();
            }
        });
    }

    public void addFeedback(Map map) {
        mainData.addFeedback(map, new DefaultSubscriber() {
            @Override
            public void onNext(Object object) {
                ((ViewsContainer.FeedbackView) baseView).addFeedback();
            }
        });
    }

    public void readNews(Map map, final int flag) {
        mainData.readNews(map, new DefaultSubscriber<NewsDetail>() {
            @Override
            public void onNext(NewsDetail newsDetail) {
                switch (flag) {
                    case 0:
                        ((ViewsContainer.NewsDetailView) baseView).readNews(newsDetail);

                        break;
                    case 1:
                        ((ViewsContainer.NewsListView) baseView).readNews(newsDetail);
                        break;
                }
            }
        });
    }

    public void verifyCode(Map map) {
        mainData.verifyCode(map, new DefaultSubscriber() {
            @Override
            public void onNext(Object object) {
                ((ViewsContainer.ForgetPasswordView) baseView).verifyCode();
            }
        });
    }
}
